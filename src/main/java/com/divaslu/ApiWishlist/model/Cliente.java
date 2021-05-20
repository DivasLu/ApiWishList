package com.divaslu.ApiWishlist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity

@Table(name = "tb_cliente")

public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // usando o mysql "Identity fará o auto incremento.
	private Long id;
	private String nomeCompleto;
	private String email;
	private String telefone;

	public Cliente(String nomeCompleto, String email, String telefone) {
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.telefone = telefone;

	}

	public Cliente() {
	}

	
}
