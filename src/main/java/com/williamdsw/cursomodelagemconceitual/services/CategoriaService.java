package com.williamdsw.cursomodelagemconceitual.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.williamdsw.cursomodelagemconceitual.domain.Categoria;
import com.williamdsw.cursomodelagemconceitual.repositories.CategoriaRepository;
import com.williamdsw.cursomodelagemconceitual.services.exceptions.DataIntegrityException;
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
	
	public Categoria findByID (Integer id)
	{
		Optional<Categoria> categoria = repository.findById (id);
		return categoria.orElseThrow (() -> new ObjectNotFoundException (" Objeto não encontrado! " + " Id: " + id + " Tipo: " + Categoria.class.getName ()));
	}
	
	public Categoria insert (Categoria categoria)
	{
		categoria.setId (null);
		return repository.save (categoria);
	}
	
	public Categoria update (Categoria categoria)
	{
		findByID (categoria.getId ());
		return repository.save (categoria);
	}
	
	public void deleteByID (Integer id)
	{
		findByID (id);
		
		try
		{
			repository.deleteById (id);
		}
		catch (DataIntegrityViolationException exception)
		{
			throw new DataIntegrityException ("Não é possível excluir uma categoria que possui produtos!");
		}
	}
}
 