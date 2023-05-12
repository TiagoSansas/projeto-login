package com.projetologin.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetologin.dto.RoleDTO;
import com.projetologin.service.RoleService;

@RestController
@RequestMapping(value="/role")
public class RoleResource {
	@Autowired
	private RoleService roleService;
	
	@GetMapping
	public ResponseEntity<List<RoleDTO>> findAllRole(){
		List<RoleDTO> list = roleService.findAll();
		return ResponseEntity.ok().body(list);
	}
}
