package com.divaslu.ApiWishlist.controller;

import com.divaslu.ApiWishlist.model.Produto;
import com.divaslu.ApiWishlist.model.WishListItem;
import com.divaslu.ApiWishlist.service.WishListItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("API REST Wishlist") 
@CrossOrigin(origins = "*") 
@RestController // é quem define as rotas ou url
@RequestMapping("/wishlist")
public class WishListController {
    @Autowired
    private WishListItemService service;

    @PostMapping("/addWishListItem")
    @ApiOperation(value = "Adiciona um item a wishlist")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Item adicionado"),
            @ApiResponse(code = 400, message = "Falha nos dados enviados"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção."), })

    public ResponseEntity<?> addWishListItem(@RequestBody WishListItem wish) {
        WishListItem wishCriado = service.saveWish(wish);
        if(wishCriado != null){
            return  ResponseEntity.status(HttpStatus.CREATED).body(wishCriado);
        }
        else
        {
            return new ResponseEntity<>("Limite de Wish atingido", HttpStatus.FORBIDDEN);
        }

    }

    @GetMapping("/WishListItemById/{id}")
    @ApiOperation(value = "Obtém um item da wishlist pelo ID")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Item obtido"),
            @ApiResponse(code = 400, message = "Falha nos dados enviados"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção."), })
    public WishListItem findWishListItemById(@PathVariable long id) {
        return service.getWishById(id);
    }

    
    
    @GetMapping("/WishListItemByClienteAndProduto/{idCliente}/{idProduto}")
    @ApiOperation(value = "Se um item está na wishList de determinado cliente, ele é retornado")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Item obtido"),
            @ApiResponse(code = 400, message = "Falha nos dados enviados"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção."), })
    public ResponseEntity<WishListItem> findWishListItemById(@PathVariable long idCliente,@PathVariable long idProduto) {
        return ResponseEntity.ok( service.getWishsByidProdutoAndidCliente(idProduto,idCliente));
    }
    
    @GetMapping("/WishListItemByCliente/{id}")
    @ApiOperation(value = "Obtém uma lista de itens da wishlist de um cliente")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Itens obtidos"),
            @ApiResponse(code = 400, message = "Falha nos dados enviados"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção."), })
    public ResponseEntity<List<WishListItem>> findWishListItemByCliente(@PathVariable long id) {
        return ResponseEntity.ok(service.getWishsByClient(id));
    }
    
    @GetMapping("/WishListItemByProduto/{id}")
    @ApiOperation(value = "Obtém uma lista de itens da wishlist de um produto")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Itens obtidos"),
            @ApiResponse(code = 400, message = "Falha nos dados enviados"),
            @ApiResponse(code = 404, message = "Item wishlist não localizado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção."), })
    public ResponseEntity<List<WishListItem>> findWishListItemByProdut(@PathVariable long id) {
        return ResponseEntity.ok(service.getWishsByProduto(id));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Deleta um item da wishlist pelo ID")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Item deletado"),
            @ApiResponse(code = 400, message = "Falha nos dados enviados"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção."), })
    public ResponseEntity<String> deleteWishListItem(@PathVariable long id) {
        try {
            service.deleteWishListItem(id);
            return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Erro ao deletar wish"+ e, HttpStatus.BAD_REQUEST);
        }
    }
}
