package com.divaslu.ApiWishlist.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.divaslu.ApiWishlist.BaseTest;
import com.divaslu.ApiWishlist.domain.model.WishListBuilder;
import com.divaslu.ApiWishlist.model.WishListItem;
import com.divaslu.ApiWishlist.repository.WishListItemRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

@SpringBootTest 
@AutoConfigureMockMvc //need this in Spring Boot test
public class WishListControllerTest extends BaseTest{

	   @Getter
	   @Setter

	   @Autowired
	   private WishListController wishListController;
		   
	   @Autowired 
	   private WishListItemRepository wishListItemRepository;
	   
	   
	   @Test
		public void postWishListItem() throws Exception {
			WishListItem wishListItem = new WishListBuilder().defaultValuesWishListItem();
			mockMvc.perform(MockMvcRequestBuilders.post("/wishlist/addWishListItem")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(asJsonString(wishListItem)))
					.andExpect(MockMvcResultMatchers.status().isCreated());
			}
	   
	  /* @Test
	    public void getWishListItemById() throws Exception {
			WishListItem wishListItem = new WishListBuilder().defaultValuesWishListItem();
	        WishListItem retorno = WishListItemRepository.save(wishListItem);
	        mockMvc.perform(MockMvcRequestBuilders.get("/wishlist//WishListItemById/{id}", retorno.getId())
	                .contentType(MediaType.APPLICATION_JSON_VALUE).content(String.valueOf(retorno.getId())))
	                .andExpect(MockMvcResultMatchers.status().isOk());
	    }*/
		
	   @Test
	    public void getWishListItemByCliente() throws Exception {
			WishListItem wishListItem = new WishListBuilder().defaultValuesWishListItem();
	        WishListItem retorno = wishListItemRepository.save(wishListItem);
	        mockMvc.perform(MockMvcRequestBuilders.get("/wishlist//WishListItemByCliente/{id}", retorno.getId())
	                .contentType(MediaType.APPLICATION_JSON_VALUE).content(String.valueOf(retorno.getId())))
	                .andExpect(MockMvcResultMatchers.status().isOk());
	    }
	   
	   @Test
	    public void getWishListItemByProduto() throws Exception {
			WishListItem wishListItem = new WishListBuilder().defaultValuesWishListItem();
	        WishListItem retorno = wishListItemRepository.save(wishListItem);
	        mockMvc.perform(MockMvcRequestBuilders.get("/wishlist//WishListItemByProduto/{id}", retorno.getId())
	                .contentType(MediaType.APPLICATION_JSON_VALUE).content(String.valueOf(retorno.getId())))
	                .andExpect(MockMvcResultMatchers.status().isOk());
	    }
	   
	   @Test
	    public void deteWishListItem() throws Exception {
			WishListItem wishListItem = new WishListBuilder().defaultValuesWishListItem();
	        WishListItem retorno = wishListItemRepository.save(wishListItem);
	        mockMvc.perform(MockMvcRequestBuilders.delete("/wishlist//delete/{id}", retorno.getId())
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