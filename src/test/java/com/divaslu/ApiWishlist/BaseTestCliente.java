package com.divaslu.ApiWishlist;

	import org.junit.jupiter.api.BeforeEach;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.test.web.servlet.MockMvc;

import com.divaslu.ApiWishlist.domain.model.ClienteBuilder;
import com.divaslu.ApiWishlist.model.Cliente;
	import com.divaslu.ApiWishlist.repository.ClienteRepository;

	public class BaseTestCliente {
			
		@BeforeEach
	    public void setup(){
	        Cliente cliente = new ClienteBuilder().defaultValuescliente();
	        clienteRepository.save(cliente);
	    }
		
	    @Autowired
	    protected MockMvc mockMvc;

	    @Autowired
	    protected ClienteRepository clienteRepository;
	}

