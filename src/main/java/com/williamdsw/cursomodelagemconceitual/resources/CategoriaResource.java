package com.williamdsw.cursomodelagemconceitual.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.williamdsw.cursomodelagemconceitual.domain.Categoria;
import com.williamdsw.cursomodelagemconceitual.services.CategoriaService;

// Controlador REST que responde pelo endpoint "/categorias"
@RestController
@RequestMapping (value = "/categorias")
public class CategoriaResource 
{
	// ------------------------------------------------------------------------------------//
	// CAMPOS
	
	@Autowired
	private CategoriaService service;
	
	// ------------------------------------------------------------------------------------//
	// FUNCOES AUXILIARES
	
	// 1) RequestMethod.GET = Indica busca de dados
	// 2) @PathVariable = Indica que o valor sera recebido da URL
	// 3) ResponseEntity<?> = Encapsula varias informacoes de uma resposta HTTP
	@RequestMapping (value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarPorID (@PathVariable Integer id)
	{
		Categoria categoria = service.buscarPorID (id);
		return ResponseEntity.ok ().body (categoria);
	}
	
	// 1) RequestMethod.POST = Indica insercao / alteracao de dados
	// 2) @RequestBody = Indica que o valor JSON sera convertido automaticamente pra objeto
	// 3) Criando URI de resposta necessaria
	// 4) ResponseEntity.created (uri).build () = Cria URI de resposta '201' necessaria
	@RequestMapping (method = RequestMethod.POST)
	public ResponseEntity<Void> inserir (@RequestBody Categoria categoria)
	{
		categoria = service.inserir (categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest ().path ("/{id}").buildAndExpand (categoria.getId ()).toUri ();
		return ResponseEntity.created (uri).build ();
	}
}