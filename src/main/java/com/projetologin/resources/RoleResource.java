package com.projetologin.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@PostMapping
	public ResponseEntity<RoleDTO> insert(@RequestBody RoleDTO dto){
		RoleDTO newDTO = roleService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(newDTO);		
	}
	@PutMapping(value="/{id}")
	public ResponseEntity<RoleDTO>update(@PathVariable Long id ,@RequestBody RoleDTO dto){
		RoleDTO newDTO = roleService.update(id, dto);
		return ResponseEntity.ok().body(newDTO);
	}
}
