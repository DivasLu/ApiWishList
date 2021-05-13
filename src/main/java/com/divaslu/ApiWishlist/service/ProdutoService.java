package com.divaslu.ApiWishlist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.divaslu.ApiWishlist.model.Produto;
import com.divaslu.ApiWishlist.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	public ProdutoRepository repository;
	
	public Produto saveProduto(Produto produto) {
        return repository.save(produto);
    }

	public List<Produto> getProdutos() {
        return repository.findAll();
    }

    public Optional<Produto> getProdutoById(Long id) {
        return repository.findById(id);
    }
    
    public Produto deleteById(Long id) {
    	repository.deleteById(id);
    	System.out.println("Produto apagado com sucesso.");
        return null;
    }

    public Produto updateProduto(Produto produto) {
		Produto produtoAlterado = repository.getOne(produto.getId());
		
           if(produtoAlterado != null){
        	   
        	   if(produtoAlterado.nome != null) produtoAlterado.setNome(produto.getNome());
               produtoAlterado.setDescricao(produto.getDescricao());
               produtoAlterado.setCor(produto.getCor());
               produtoAlterado.setValor(produto.getValor());
               
             }
           return repository.save(produtoAlterado);
   }	
}
