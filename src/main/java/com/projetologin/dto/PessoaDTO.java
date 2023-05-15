package com.projetologin.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.projetologin.entites.Pessoa;

public class PessoaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;

	Set<RoleDTO> roles = new HashSet<>();
	public PessoaDTO() {}

	
	public PessoaDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public PessoaDTO(Pessoa entity) {
		id = entity.getId();
		name = entity.getName();
		entity.getRoles().forEach(role->this.roles.add(new RoleDTO(role)));
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


	public Set<RoleDTO> getRoles() {
		return roles;
	}
	
	
	
	
	
}
