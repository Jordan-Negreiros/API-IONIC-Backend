package com.jordan.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jordan.cursomc.domain.Categoria;
import com.jordan.cursomc.exceptions.ObjectNotFoundException;
import com.jordan.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insertCategoria(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria updateCategoria(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
}
