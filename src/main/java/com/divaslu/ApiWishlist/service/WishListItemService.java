package com.divaslu.ApiWishlist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divaslu.ApiWishlist.model.WishListItem;
import com.divaslu.ApiWishlist.repository.WishListItemRepository;

@Service
public class WishListItemService {
	@Autowired
	private WishListItemRepository repository;

	public WishListItem saveWish(WishListItem wish) {
		if (ValidaWish(wish)) {
			return repository.save(wish);
		}
		return null;
	}

	public List<WishListItem> getWishsByClient(long idCliente) {
		return repository.findWishListItemByIdCliente(idCliente);

	}

	public WishListItem getWishsByidProdutoAndidCliente(long idProduto, long idCliente) {
		return repository.findWishListItemByIdProdutoAndIdCliente(idProduto, idCliente);

	}

	public List<WishListItem> getWishsByProduto(long idProduto) {
		return repository.findWishListItemByIdProduto(idProduto);
	}

	public WishListItem getWishById(long id) {
		return repository.findById(id).orElse(null);
	}

	public String deleteWishListItem(long id) {
		repository.deleteById(id);
		return "WishListItem removido: " + id;
	}

	private boolean ValidaWish(WishListItem wish) {
		List<WishListItem> wishDoCliente = getWishsByClient(wish.getIdCliente());
		int quantidadeDeWishs = wishDoCliente != null ? wishDoCliente.size() : 0;
		return quantidadeDeWishs < 20;
	}
}