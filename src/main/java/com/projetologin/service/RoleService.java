package com.projetologin.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projetologin.dto.RoleDTO;
import com.projetologin.entites.Role;
import com.projetologin.repositorys.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Transactional(readOnly = true)
	public List <RoleDTO> findAll() {
		List<Role> list = roleRepository.findAll();
		return list.stream().map(x->new RoleDTO(x)).collect(Collectors.toList());
	}
}
