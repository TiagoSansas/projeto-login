package com.projetologin.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetologin.entites.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>  {

}
