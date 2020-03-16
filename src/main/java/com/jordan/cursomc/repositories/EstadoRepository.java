package com.jordan.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jordan.cursomc.domain.Estado;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

    @Transactional(readOnly=true)
    public List<Estado> findAllByOrderByNome();

}
