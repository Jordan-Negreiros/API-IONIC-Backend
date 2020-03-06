package com.jordan.cursomc.resource;

import com.jordan.cursomc.domain.Categoria;
import com.jordan.cursomc.domain.Produto;
import com.jordan.cursomc.dto.CategoriaDTO;
import com.jordan.cursomc.dto.ProdutoDTO;
import com.jordan.cursomc.resource.utils.URL;
import com.jordan.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		
		Produto obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "nome", defaultValue = "")String nome,
			@RequestParam(value = "categorias", defaultValue = "")String categorias,
			@RequestParam(value = "page", defaultValue = "0")Integer page,
			@RequestParam(value = "linePerPage", defaultValue = "24")Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome")String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC")String direction) {

		String nomeDecode = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> list = service.search(nomeDecode, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));

		return ResponseEntity.ok().body(listDTO);
	}
}
