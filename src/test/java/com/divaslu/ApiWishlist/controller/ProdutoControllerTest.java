package com.divaslu.ApiWishlist.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.divaslu.ApiWishlist.BaseTest;
import com.divaslu.ApiWishlist.domain.model.ProdutoBuilder;
import com.divaslu.ApiWishlist.model.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

@SpringBootTest 
@AutoConfigureMockMvc //need this in Spring Boot test
public class ProdutoControllerTest extends BaseTest{

	   @Getter
	   @Setter
	   @Autowired

	   private ProdutoController produtoController;
		   
	    
	   @Test
		public void getProdutoList() throws Exception {
			mockMvc.perform(MockMvcRequestBuilders.get("/produtos")
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(MockMvcResultMatchers.status().isOk());
		}
	   
	   @Test
		public void postProduto() throws Exception {
			Produto produto = new ProdutoBuilder().defaultValues();
			mockMvc.perform(MockMvcRequestBuilders.post("/produtos")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(asJsonString(produto)))
					.andExpect(MockMvcResultMatchers.status().isCreated());
			}
	   
	   @Test
	    public void updateProduto() throws Exception {
	        Produto produto = new ProdutoBuilder().defaultValues();
	        Produto retorno = produtoRepository.save(produto);
	        retorno.setNome("fogão");
	        mockMvc.perform(MockMvcRequestBuilders.put("/produtos")
	                .contentType(MediaType.APPLICATION_JSON_VALUE)
	                .content(asJsonString(retorno))
	                .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andExpect(MockMvcResultMatchers.status().isOk());
	    }

	@Test
	    public void deleteProduto() throws Exception {
	        Produto produto = new ProdutoBuilder().defaultValues();
	        Produto retorno = produtoRepository.save(produto);
	        mockMvc.perform(MockMvcRequestBuilders.delete("/produtos/{id}", retorno.getId())
	                .contentType(MediaType.APPLICATION_JSON_VALUE).content(String.valueOf(retorno.getId())))
	                .andExpect(MockMvcResultMatchers.status().isOk());
	    }
	
	@Test
    public void getProduto() throws Exception {
        Produto produto = new ProdutoBuilder().defaultValues();
        Produto retorno = produtoRepository.save(produto);
        mockMvc.perform(MockMvcRequestBuilders.get("/produtos/{id}", retorno.getId())
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

