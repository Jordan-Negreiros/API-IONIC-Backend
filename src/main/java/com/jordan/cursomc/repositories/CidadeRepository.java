package com.jordan.cursomc.repositories;

import com.jordan.cursomc.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CidadeRepository  extends JpaRepository<Cidade, Integer>{

    @Transactional
    @Query("select obj from Cidade obj where obj.estado.id = :estadoId ORDER BY obj.nome")
    public List<Cidade> findCidades(@Param("estadoId") Integer estado_id);
}
