package com.projetologin.dto;

import java.io.Serializable;

import com.projetologin.entites.Pessoa;

public class PessoaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	
	public PessoaDTO() {}

	
	public PessoaDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public PessoaDTO(Pessoa entity) {
		id = entity.getId();
		name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
