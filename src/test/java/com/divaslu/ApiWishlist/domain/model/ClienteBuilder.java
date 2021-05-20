package com.divaslu.ApiWishlist.domain.model;

import com.divaslu.ApiWishlist.model.Cliente;

public class ClienteBuilder {
	
	public Cliente defaultValuescliente() {
		return new Cliente(DefaultValuesCliente.NOMECOMPLETO, DefaultValuesCliente.EMAIL, DefaultValuesCliente.TELEFONE);
}
}
