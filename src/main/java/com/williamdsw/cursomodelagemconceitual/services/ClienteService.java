package com.williamdsw.cursomodelagemconceitual.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.williamdsw.cursomodelagemconceitual.domain.Cliente;
import com.williamdsw.cursomodelagemconceitual.repositories.ClienteRepository;
import com.williamdsw.cursomodelagemconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService
{
	// ------------------------------------------------------------------------------------//
	// CAMPOS
	
	@Autowired
	private ClienteRepository repository;
	
	// ------------------------------------------------------------------------------------//
	// FUNCOES AUXILIARES
	
	public Cliente buscarPorID (Integer id)
	{
		Optional<Cliente> cliente = repository.findById (id);
		return cliente.orElseThrow (() -> new ObjectNotFoundException (" Objeto não encontrado! " + " Id: " + id + " Tipo: " + Cliente.class.getName ()));
	}
}