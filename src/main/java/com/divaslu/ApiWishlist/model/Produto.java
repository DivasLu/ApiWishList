package com.divaslu.ApiWishlist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Data
@Entity
@Table(name = "tb_produto")

public class Produto {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@NotEmpty
	private String nome;
	private String descricao;
	private String cor;
	@NotEmpty
	private double valor;
	
	
	
	public Produto(String nome, String descricao, String cor, Double valor) {
		this.nome = nome;
		this.descricao = descricao;
		this.cor = cor;
		this.valor = valor;
	}

	public Produto () {}

		
	
		
}


	
