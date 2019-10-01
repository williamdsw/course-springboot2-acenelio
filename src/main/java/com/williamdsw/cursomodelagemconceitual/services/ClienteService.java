package com.williamdsw.cursomodelagemconceitual.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.williamdsw.cursomodelagemconceitual.domain.Cliente;
import com.williamdsw.cursomodelagemconceitual.dto.ClienteDTO;
import com.williamdsw.cursomodelagemconceitual.repositories.ClienteRepository;
import com.williamdsw.cursomodelagemconceitual.services.exceptions.DataIntegrityException;
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

	// Busca todos
	public List<Cliente> findAll ()
	{ 
		return repository.findAll (); 
	}

	// Busca por ID
	public Cliente findByID (Integer id)
	{
		Optional<Cliente> cliente = repository.findById (id);
		return cliente.orElseThrow ( () -> new ObjectNotFoundException (" Objeto não encontrado! " + " Id: " + id + " Tipo: " + Cliente.class.getName ()));
	}

	// Busca com paginacao
	public Page<Cliente> findPage (Integer pageNumber, Integer linesPerPage, String orderBy, String direction)
	{
		PageRequest pageRequest = PageRequest.of (pageNumber, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll (pageRequest);
	}

	// Insere
	public Cliente insert (Cliente cliente)
	{
		cliente.setId (null);
		return repository.save (cliente);
	}

	// Atualiza
	public Cliente update (Cliente cliente)
	{
		Cliente novoCliente = findByID (cliente.getId());
		updateData (novoCliente, cliente);
		return repository.save (novoCliente);
	}

	// Exclui por ID
	public void deleteByID (Integer id)
	{
		findByID (id);

		try
		{
			repository.deleteById (id);
		}
		catch (DataIntegrityViolationException exception)
		{
			throw new DataIntegrityException ("Não é possível excluir um Cliente!");
		}
	}

	// Converte dados
	public Cliente fromDTO (ClienteDTO dto)
	{
		return new Cliente (dto.getId (), dto.getNome (), dto.getEmail (), null, null);
	}
	
	// Atualiza dados
	private void updateData (Cliente novoCliente, Cliente cliente)
	{
		novoCliente.setNome (cliente.getNome ());
		novoCliente.setEmail (cliente.getEmail ());
	}
}