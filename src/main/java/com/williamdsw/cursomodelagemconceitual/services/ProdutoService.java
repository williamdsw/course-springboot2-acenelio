package com.williamdsw.cursomodelagemconceitual.services;

import com.williamdsw.cursomodelagemconceitual.domain.Categoria;
import com.williamdsw.cursomodelagemconceitual.domain.Produto;
import com.williamdsw.cursomodelagemconceitual.repositories.CategoriaRepository;
import com.williamdsw.cursomodelagemconceitual.repositories.ProdutoRepository;
import com.williamdsw.cursomodelagemconceitual.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

/**
 * @author William
 */
@Service
public class ProdutoService
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    @Autowired
    private ProdutoRepository repository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    // ------------------------------------------------------------------------------------//
    // FUNCOES AUXILIARES
    
    public Produto findByID (Integer id)
    {
        Optional<Produto> produto = repository.findById (id);
        return produto.orElseThrow (() -> new ObjectNotFoundException (" Objeto não encontrado! " + " Id: " + id + " Tipo: " + Produto.class.getName ()));
    }
    
    // 1) findAllById = Permite obter dados procurando com uma lista de ID informada
    public Page<Produto> search (String nome, List<Integer> categoriaIDs, Integer pageNumber, Integer linesPerPage, String orderBy, String direction)
    {
        PageRequest pageRequest = PageRequest.of (pageNumber, linesPerPage, Direction.valueOf (direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById (categoriaIDs);
        return repository.search (nome, categorias, pageRequest);
    }
}