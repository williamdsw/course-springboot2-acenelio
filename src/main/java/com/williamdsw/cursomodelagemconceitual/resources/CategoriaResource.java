package com.williamdsw.cursomodelagemconceitual.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// Controlador REST que responde pelo endpoint "/categorias"
@RestController
@RequestMapping (value = "/categorias")
public class CategoriaResource 
{
	// Indica que busca dados com "RequestMethod.GET"
	@RequestMapping (method = RequestMethod.GET)
	public String listar ()
	{
		return "Rest está funcionando";
	}
}