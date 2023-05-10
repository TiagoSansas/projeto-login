package com.projetologin.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projetologin.dto.PessoaDTO;
import com.projetologin.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {

	@Autowired
	private PessoaService service;

	@GetMapping
	public ResponseEntity<Page<PessoaDTO>> findAllPAge(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "5") Integer linesPerPage,
			@RequestParam(defaultValue = "ASC") String direction, @RequestParam(defaultValue = "name") String orderBy) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<PessoaDTO> list = service.findAllPage(pageRequest);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PessoaDTO> findById(@PathVariable Long id) {
		PessoaDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	public ResponseEntity<PessoaDTO> insert(@RequestBody PessoaDTO dto) {
		PessoaDTO newDTO = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(newDTO);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<PessoaDTO> update(@PathVariable Long id, @RequestBody PessoaDTO dto) {
		PessoaDTO newDTO = service.update(id, dto);
		return ResponseEntity.ok().body(newDTO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
