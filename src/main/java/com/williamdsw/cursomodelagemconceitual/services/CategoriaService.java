package com.williamdsw.cursomodelagemconceitual.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.williamdsw.cursomodelagemconceitual.domain.Categoria;
import com.williamdsw.cursomodelagemconceitual.repositories.CategoriaRepository;
import com.williamdsw.cursomodelagemconceitual.services.exceptions.ObjectNotFoundException;

// Indica servico
@Service
public class CategoriaService
{
	// ------------------------------------------------------------------------------------//
	// CAMPOS
	
	// Indica que a dependencia sera automaticamente instanciada
	@Autowired
	private CategoriaRepository repository;
	
	// ------------------------------------------------------------------------------------//
	// FUNCOES AUXILIARES
	
	public Categoria buscarPorID (Integer id)
	{
		Optional<Categoria> categoria = repository.findById (id);
		return categoria.orElseThrow (() -> new ObjectNotFoundException (" Objeto não encontrado! " + " Id: " + id + " Tipo: " + Categoria.class.getName ()));
	}
	
	public Categoria inserir (Categoria categoria)
	{
		categoria.setId (null);
		return repository.save (categoria);
	}
}
 