package com.projetologin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projetologin.dto.PessoaDTO;
import com.projetologin.entites.Pessoa;
import com.projetologin.repositorys.PessoaRepository;
import com.projetologin.service.exceptions.DatabaseException;
import com.projetologin.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	@Transactional(readOnly = true)
	public Page<PessoaDTO> findAllPage(PageRequest pageRequest) {
		Page<Pessoa> list = repository.findAll(pageRequest);
		return list.map(x -> new PessoaDTO(x));
	}

	@Transactional(readOnly = true)
	public PessoaDTO findById(Long id) {
		Optional<Pessoa> obj = repository.findById(id);
		Pessoa entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new PessoaDTO(entity);
	}

	@Transactional
	public PessoaDTO insert(PessoaDTO dto) {

		Pessoa entity = new Pessoa();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new PessoaDTO(entity);

	}

	@Transactional
	public PessoaDTO update(Long id, PessoaDTO dto) {
		try {
			Pessoa entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new PessoaDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}

	}

	@Transactional
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	public void copyDtoToEntity(PessoaDTO dto, Pessoa entity) {
		entity.setName(dto.getName());
	}
}
