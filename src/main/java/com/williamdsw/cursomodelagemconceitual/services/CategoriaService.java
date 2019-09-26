package com.williamdsw.cursomodelagemconceitual.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.williamdsw.cursomodelagemconceitual.domain.Categoria;
import com.williamdsw.cursomodelagemconceitual.repositories.CategoriaRepository;

// Indica servico
@Service
public class CategoriaService
{
	// Indica que a dependencia sera automaticamente instanciada
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria buscarPorID (Integer id)
	{
		Optional<Categoria> categoria = repository.findById (id);
		return categoria.orElse (null);
	}
}
