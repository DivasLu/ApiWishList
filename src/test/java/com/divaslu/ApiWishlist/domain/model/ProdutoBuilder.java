package com.divaslu.ApiWishlist.domain.model;

import com.divaslu.ApiWishlist.model.Produto;

public class ProdutoBuilder {

	public Produto defaultValues() {
		return new Produto(DefaultValues.NOME, DefaultValues.DESCRICAO, DefaultValues.COR, DefaultValues.VALOR);
}
}
