package com.jordan.cursomc.services;

import com.jordan.cursomc.domain.Cidade;
import com.jordan.cursomc.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> findByEstado (Integer estadoId) {
        return cidadeRepository.findCidades(estadoId);
    }
}
