package com.divaslu.ApiWishlist.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.divaslu.ApiWishlist.model.Produto;
import com.divaslu.ApiWishlist.service.ProdutoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("API REST Wishlist") // nome da api no swagger
@CrossOrigin(origins = "*") // quem pode acessar essa documentação
@RestController // é quem define as rotas ou url
@RequestMapping("/produtos")

public class ProdutoController {

	@Autowired // é a ligação entre os objetos
	public ProdutoService service;

	@PostMapping("/addProduto")
	@ApiOperation(value = "Salva um produto")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Cria um Produto"),
			@ApiResponse(code = 400, message = "Falha nos dados enviados"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção."), })

	public ResponseEntity<Produto> post(@RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.saveProduto(produto));
	}

	@GetMapping
	@ApiOperation(value = "Retorna uma lista de Produtos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma lista de Produtos"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção."), })
	public ResponseEntity<List<Produto>> getAll() {
		return ResponseEntity.ok(service.getProdutos());

	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um único produto")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um único Produto"),
			@ApiResponse(code = 404, message = "Produto não localizado"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção."), })

	public Produto getById(@PathVariable Long id) {
		return service.getProdutoById(id);

	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta um produto")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Produto excluido com sucesso."),
			@ApiResponse(code = 400, message = "Falha nos dados enviados"),
			@ApiResponse(code = 404, message = "Produto não localizado"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção."), })

	public ResponseEntity<String> deletarProduto(@PathVariable long id) {
		try {
			service.deleteById(id);
			return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Erro ao deletar produto" + e, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping
	@ApiOperation(value = "Altera um produto")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Produto alterado com sucesso."),
			@ApiResponse(code = 400, message = "Falha nos dados enviados"),
			@ApiResponse(code = 404, message = "Produto não localizado"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção."), })

	public void put(@RequestBody Produto produto) {
		service.updateProduto(produto);
	}

}
