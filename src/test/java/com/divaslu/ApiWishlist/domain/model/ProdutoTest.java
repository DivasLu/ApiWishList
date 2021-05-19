package com.divaslu.ApiWishlist.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import com.divaslu.ApiWishlist.model.Produto;

public class ProdutoTest {
	
	   @Test
	    public void criandoInstancia(){
	        Produto produto = new ProdutoBuilder().defaultValues();
	        assertThat(produto).isNotNull();
	                   
	   }
	   
	    @Test
	    public void comparandoAtributos(){
	        Produto produto = new ProdutoBuilder().defaultValues();
	        assertThat(produto.getNome()).isEqualTo(DefaultValues.NOME);
	        assertThat(produto.getDescricao()).isEqualTo(DefaultValues.DESCRICAO);
	        assertThat(produto.getCor()).isEqualTo(DefaultValues.COR);
	        assertThat(produto.getValor()).isEqualTo(DefaultValues.VALOR);
	    }
}
