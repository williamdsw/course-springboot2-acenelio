package com.williamdsw.cursomodelagemconceitual.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.williamdsw.cursomodelagemconceitual.domain.Cliente;
import com.williamdsw.cursomodelagemconceitual.services.ClienteService;

@RestController
@RequestMapping (value = "/clientes")
public class ClienteResource
{
	// ------------------------------------------------------------------------------------//
	// CAMPOS
	
	@Autowired
	private ClienteService service;
	
	// ------------------------------------------------------------------------------------//
	// FUNCOES AUXILIARES
	
	@RequestMapping (value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> findByID (@PathVariable Integer id)
	{
		Cliente cliente = service.findByID (id);
		return ResponseEntity.ok ().body (cliente);
	}
}
