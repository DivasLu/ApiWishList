package com.divaslu.ApiWishlist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name  = "tb_wishListItens")
public class WishListItem {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    
    private long idCliente;
    private long idProduto;
    
    
    public long getId(){
        return this.id;
    }
    public long getIdCliente(){
        return this.idCliente;
    }
    public long getIdProduto(){
        return this.idProduto;
    }
    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }
    public void setIdProduto(int idProduto){
        this.idProduto = idProduto;
    }
}