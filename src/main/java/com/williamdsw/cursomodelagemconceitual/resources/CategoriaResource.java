package com.williamdsw.cursomodelagemconceitual.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.williamdsw.cursomodelagemconceitual.domain.Categoria;

// Controlador REST que responde pelo endpoint "/categorias"
@RestController
@RequestMapping (value = "/categorias")
public class CategoriaResource 
{
	// Indica que busca dados com "RequestMethod.GET"
	@RequestMapping (method = RequestMethod.GET)
	public List<Categoria> listar ()
	{
		// Dados
		Categoria informatica = new Categoria (1, "Informática");
		Categoria escritorio = new Categoria (2, "Escritório");
		
		List<Categoria> categorias = new ArrayList<>();
		categorias.add (informatica);
		categorias.add (escritorio);
		
		return categorias;
	}
}