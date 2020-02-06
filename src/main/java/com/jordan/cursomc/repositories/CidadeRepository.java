package com.jordan.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jordan.cursomc.domain.Cidade;

public interface CidadeRepository  extends JpaRepository<Cidade, Integer>{

}
