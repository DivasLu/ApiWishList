package com.divaslu.ApiWishlist.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.divaslu.ApiWishlist.BaseTestCliente;
import com.divaslu.ApiWishlist.domain.model.ClienteBuilder;
import com.divaslu.ApiWishlist.model.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

@SpringBootTest 
@AutoConfigureMockMvc //need this in Spring Boot test
public class ClienteControllerTest extends BaseTestCliente{

	   @Getter
	   @Setter
	   @Autowired

	   private ClienteController clienteController;
		   
	    
	   @Test
		public void getClienteList() throws Exception {
			mockMvc.perform(MockMvcRequestBuilders.get("/clientes")
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(MockMvcResultMatchers.status().isOk());
		}
	   
	   @Test
		public void postCliente() throws Exception {
			Cliente cliente = new ClienteBuilder().defaultValuescliente();
			mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(asJsonString(cliente)))
					.andExpect(MockMvcResultMatchers.status().isCreated());
			}
	   
	   @Test
	    public void updateCliente() throws Exception {
	        Cliente cliente = new ClienteBuilder().defaultValuescliente();
	        Cliente retorno = clienteRepository.save(cliente);
	        retorno.setNomeCompleto("Maria");
	        mockMvc.perform(MockMvcRequestBuilders.put("/clientes")
	                .contentType(MediaType.APPLICATION_JSON_VALUE)
	                .content(asJsonString(retorno))
	                .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andExpect(MockMvcResultMatchers.status().isOk());
	    }

	@Test
	    public void deleteCliente() throws Exception {
	        Cliente cliente = new ClienteBuilder().defaultValuescliente();
	        Cliente retorno = clienteRepository.save(cliente);
	        mockMvc.perform(MockMvcRequestBuilders.delete("/clientes/{id}", retorno.getId())
	                .contentType(MediaType.APPLICATION_JSON_VALUE).content(String.valueOf(retorno.getId())))
	                .andExpect(MockMvcResultMatchers.status().isOk());
	    }
	
	@Test
    public void getCliente() throws Exception {
        Cliente cliente = new ClienteBuilder().defaultValuescliente();
        Cliente retorno = clienteRepository.save(cliente);
        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/{id}", retorno.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(String.valueOf(retorno.getId())))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

	  
	   public static String asJsonString(final Object obj) {
	        try {
	            final ObjectMapper mapper = new ObjectMapper();
	            final String jsonContent = mapper.writeValueAsString(obj);
	            return jsonContent;
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }  
}


	