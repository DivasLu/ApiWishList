package com.divaslu.ApiWishlist.controller;


import java.util.List;

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

import com.divaslu.ApiWishlist.model.Cliente;
import com.divaslu.ApiWishlist.service.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("API REST Wishlist") // nome da api no swagger
@CrossOrigin(origins = "*") // quem pode acessar essa documentação
@RestController // é quem define as rotas ou url
@RequestMapping("/clientes")

public class ClienteController {

	@Autowired // é a ligação entre os objetos
	public ClienteService service;

	@PostMapping
	@ApiOperation(value = "Salva um cliente")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Cria um Cliente"),
	@ApiResponse(code = 400, message = "Falha nos dados enviados"),
	@ApiResponse(code = 500, message = "Foi gerada uma exceção."), })

	public ResponseEntity<Cliente> post(@RequestBody Cliente cliente) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.saveCliente(cliente));

	}

	@GetMapping
	@ApiOperation(value = "Retorna uma lista de Clientes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma lista de Clientes"),
	@ApiResponse(code = 500, message = "Foi gerada uma exceção."), })
	public ResponseEntity<List<Cliente>> getAll() {
		return ResponseEntity.ok(service.getClientes());

	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um único cliente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um único Cliente"),
	@ApiResponse(code = 404, message = "Cliente não localizado"),
	@ApiResponse(code = 500, message = "Foi gerada uma exceção."), })
	public Cliente getById(@PathVariable Long id) {
		return service.getClienteById(id);

	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta um cliente")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Cliente excluido com sucesso."),
	@ApiResponse(code = 400, message = "Falha nos dados enviados"),
	@ApiResponse(code = 404, message = "Cliente não localizado"),
	@ApiResponse(code = 500, message = "Foi gerada uma exceção."), })

	public ResponseEntity<String> deletarCliente(@PathVariable long id) {
		try {
			service.deleteById(id);
			return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Erro ao deletar cliente" + e, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping
	@ApiOperation(value = "Altera um cliente")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Cliente alterado com sucesso."),
	@ApiResponse(code = 400, message = "Falha nos dados enviados"),
	@ApiResponse(code = 404, message = "Cliente não localizado"),
	@ApiResponse(code = 500, message = "Foi gerada uma exceção."), })

	
	public Cliente put(@RequestBody Cliente cliente) {
		return service.updateCliente(cliente);
	}

}
