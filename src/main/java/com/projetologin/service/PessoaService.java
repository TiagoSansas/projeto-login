package com.projetologin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projetologin.dto.PessoaDTO;
import com.projetologin.entites.Pessoa;
import com.projetologin.repositorys.PessoaRepository;
import com.projetologin.service.exceptions.ResourceNotFoundException;



@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;
	
	@Transactional(readOnly=true)
	public Page<PessoaDTO> findAllPage(PageRequest pageRequest){
		Page<Pessoa> list = repository.findAll(pageRequest);
		return list.map(x->new PessoaDTO(x));
	}
	
	@Transactional(readOnly =true)
	public PessoaDTO findById(Long id) {
		Optional<Pessoa> obj = repository.findById(id);
		Pessoa entity = obj.orElseThrow(()->new ResourceNotFoundException("Entity not found"));
		return new PessoaDTO(entity);
	}
	
}