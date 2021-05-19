package com.divaslu.ApiWishlist.repository;

import com.divaslu.ApiWishlist.model.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListItemRepository extends JpaRepository<WishListItem, Long> {
    List<WishListItem> findWishListItemByIdCliente(long idCliente);
    List<WishListItem> findWishListItemByIdProduto(long idProduto);
    WishListItem findWishListItemByIdProdutoAndIdCliente(long idProduto, long idCliente);
}
