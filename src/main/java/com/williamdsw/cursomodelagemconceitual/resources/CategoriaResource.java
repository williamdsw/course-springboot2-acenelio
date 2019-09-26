package com.williamdsw.cursomodelagemconceitual.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.williamdsw.cursomodelagemconceitual.domain.Categoria;
import com.williamdsw.cursomodelagemconceitual.services.CategoriaService;

// Controlador REST que responde pelo endpoint "/categorias"
@RestController
@RequestMapping (value = "/categorias")
public class CategoriaResource 
{
	@Autowired
	private CategoriaService service;
	
	// RequestMethod.GET = Indica busca de dados
	// @PathVariable = Indica que o valor sera recebido da URL
	// ResponseEntity<?> = Encapsula varias informacoes de uma resposta HTTP
	@RequestMapping (value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarPorID (@PathVariable Integer id)
	{
		Categoria categoria = service.buscarPorID (id);
		return ResponseEntity.ok ().body (categoria);
	}
}