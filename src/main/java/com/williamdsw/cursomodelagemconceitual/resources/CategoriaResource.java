package com.williamdsw.cursomodelagemconceitual.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.williamdsw.cursomodelagemconceitual.domain.CategoriaDomain;

// Controlador REST que responde pelo endpoint "/categorias"
@RestController
@RequestMapping (value = "/categorias")
public class CategoriaResource 
{
	// Indica que busca dados com "RequestMethod.GET"
	@RequestMapping (method = RequestMethod.GET)
	public List<CategoriaDomain> listar ()
	{
		// Dados
		CategoriaDomain informatica = new CategoriaDomain (1, "Informática");
		CategoriaDomain escritorio = new CategoriaDomain (2, "Escritório");
		
		List<CategoriaDomain> categorias = new ArrayList<>();
		categorias.add (informatica);
		categorias.add (escritorio);
		
		return categorias;
	}
}