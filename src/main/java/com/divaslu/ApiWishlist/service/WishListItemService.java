package com.divaslu.ApiWishlist.service;

import java.util.List;
import java.util.Optional;

import com.divaslu.ApiWishlist.model.Cliente;
import com.divaslu.ApiWishlist.model.Produto;
import com.divaslu.ApiWishlist.repository.ClienteRepository;
import com.divaslu.ApiWishlist.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divaslu.ApiWishlist.model.WishListItem;
import com.divaslu.ApiWishlist.repository.WishListItemRepository;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.persistence.EntityNotFoundException;

@Service
public class WishListItemService {
    @Autowired
    private WishListItemRepository repository;
    @Autowired
    private ProdutoRepository repositoryProduto;
    @Autowired
    private ClienteRepository repositoryCliente;
	
	 public WishListItem saveWish(WishListItem wish) {
         List<WishListItem> wishDoCliente =  getWishsByClient(wish.getIdCliente());
         int quantidadeDeWishs = wishDoCliente != null? wishDoCliente.size(): 0;
         Optional<WishListItem> desejoJaExiste = OverridegetWishsByidProdutoAndidCliente(wish.getIdProduto(), wish.getIdCliente());
         Optional<Produto> produtoExiste = repositoryProduto.findById(wish.getIdProduto());
         Optional<Cliente> ClienteExiste = repositoryCliente.findById(wish.getIdCliente());

         if(desejoJaExiste.isPresent()){
             throw new NullPointerException("Esse item já se encontra na wishlist do Cliente");
         }
         if(quantidadeDeWishs > 19){
             throw new NullPointerException("Limite máximo de 20 Wishes Atingido");
         }
	     if(!produtoExiste.isPresent()){
             throw new EntityNotFoundException("Não encontrado nenhum produto com o ID"+ wish.getIdProduto());
         }
         if(!ClienteExiste.isPresent()){
             throw new EntityNotFoundException("Não encontrado nenhum Cliente com o ID"+ wish.getIdCliente());
         }
	     return repository.save(wish);
	 }

    public List<WishListItem> getWishsByClient(long idCliente) {
        Optional<Cliente> ClienteExiste = repositoryCliente.findById(idCliente);
        if(!ClienteExiste.isPresent()){
            throw new EntityNotFoundException("Não encontrado nenhum Cliente com o ID"+ idCliente);
        }
        return repository.findWishListItemByIdCliente(idCliente);
    }
    public WishListItem getWishsByidProdutoAndidCliente(long idProduto,long idCliente) {
        return repository.findWishListItemByIdProdutoAndIdCliente(idProduto,idCliente).orElseThrow(() -> new EntityNotFoundException("Não encontrado wish com o idproduto: " + idProduto
                +" na wishlist do cliente "+ idProduto ));

    }
    public List<WishListItem> getWishsByProduto(long idProduto) {
        Optional<Produto> produtoExiste = repositoryProduto.findById(idProduto);
        if(!produtoExiste.isPresent()){
            throw new EntityNotFoundException("Não encontrado nenhum produto com o ID"+ idProduto);
        }
        return repository.findWishListItemByIdProduto(idProduto);
    }

    public WishListItem getWishById(long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não encontrado " + id));
    }

    public String deleteWishListItem(long id) {
        repository.deleteById(id);
        return "WishListItem removido: " + id;
    }
    private Optional<WishListItem> OverridegetWishsByidProdutoAndidCliente(long idProduto,long idCliente) {
        return repository.findWishListItemByIdProdutoAndIdCliente(idProduto,idCliente);
    }
}