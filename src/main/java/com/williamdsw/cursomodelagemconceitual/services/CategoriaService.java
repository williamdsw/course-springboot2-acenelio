package com.williamdsw.cursomodelagemconceitual.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.williamdsw.cursomodelagemconceitual.domain.Categoria;
import com.williamdsw.cursomodelagemconceitual.dto.CategoriaDTO;
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
    
    // Busca todos
    public List<Categoria> findAll ()
    {
        return repository.findAll ();
    }

    // Busca por ID
    public Categoria findByID (Integer id)
    {
        Optional<Categoria> categoria = repository.findById (id);
        return categoria.orElseThrow (() -> new ObjectNotFoundException (" Objeto não encontrado! " + " Id: " + id + " Tipo: " + Categoria.class.getName ()));
    }

    // Busca com paginacao
    public Page<Categoria> findPage (Integer pageNumber, Integer linesPerPage, String orderBy, String direction)
    {
        PageRequest pageRequest = PageRequest.of (pageNumber, linesPerPage, Direction.valueOf (direction), orderBy);
        return repository.findAll (pageRequest);
    }

    // Insere
    public Categoria insert (Categoria categoria)
    {
        categoria.setId (null);
        return repository.save (categoria);
    }

    // Atualiza
    public Categoria update (Categoria categoria)
    {
        Categoria novaCategoria = findByID (categoria.getId ());
        updateData (novaCategoria, categoria);
        return repository.save (novaCategoria);
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
            throw new DataIntegrityException ("Não é possível excluir uma categoria que possui produtos!");
        }
    }

    public Categoria fromDTO (CategoriaDTO dto)
    {
        return new Categoria (dto.getId (), dto.getNome ());
    }

    // Atualiza dados
    private void updateData (Categoria novaCategoria, Categoria categoria)
    {
        novaCategoria.setNome (categoria.getNome ());
    }
}