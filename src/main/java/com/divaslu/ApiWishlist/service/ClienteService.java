package com.divaslu.ApiWishlist.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divaslu.ApiWishlist.model.Cliente;
import com.divaslu.ApiWishlist.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	public ClienteRepository repository;

	public Cliente saveCliente(Cliente cliente) {
		return repository.save(cliente);
	}

	public List<Cliente> getClientes() {
		return repository.findAll();
	}

	public Cliente getClienteById(Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não encontrado " + id));
	}

	public Cliente deleteById(Long id) {
		repository.deleteById(id);
		System.out.println("Cliente apagado com sucesso.");
		return null;
	}

		
	public Cliente updateCliente(Cliente cliente) {
		
		Cliente clienteAlterado = repository.getOne(cliente.getId());
				
		if (clienteAlterado != null) {

			clienteAlterado.setNomeCompleto(cliente.getNomeCompleto());
			clienteAlterado.setEmail(cliente.getEmail());
			clienteAlterado.setTelefone(cliente.getTelefone());
			repository.save(clienteAlterado);
		}
		return repository.save(clienteAlterado);

}
}
