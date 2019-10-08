package com.williamdsw.cursomodelagemconceitual.resources;

import com.williamdsw.cursomodelagemconceitual.domain.Produto;
import com.williamdsw.cursomodelagemconceitual.dto.ProdutoDTO;
import com.williamdsw.cursomodelagemconceitual.resources.utils.UrlUtils;
import com.williamdsw.cursomodelagemconceitual.services.ProdutoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author William
 */
@RestController
@RequestMapping ("/produtos")
public class ProdutoResource
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS

    @Autowired
    private ProdutoService service;

    // ------------------------------------------------------------------------------------//
    // FUNCOES AUXILIARES
    
    @RequestMapping (path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> findByID (@PathVariable Integer id)
    {
        Produto produto = service.findByID (id);
        return ResponseEntity.ok ().body (produto);
    }
    
    @RequestMapping (method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> search (
            @RequestParam (value = "nome", defaultValue = "") String nome,
            @RequestParam (value = "categorias", defaultValue = "") String categorias,
            @RequestParam (value = "page", defaultValue = "0") Integer pageNumber,
            @RequestParam (value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
            @RequestParam (value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam (value = "direction", defaultValue = "ASC") String direction)
    {
        String nomeDecoded = UrlUtils.decodeParam (nome);
        List<Integer> categoriasIDs = UrlUtils.decodeIntegerList (categorias);
        Page<Produto> produtos = service.search (nomeDecoded, categoriasIDs, pageNumber, linesPerPage, orderBy, direction);
        Page<ProdutoDTO> produtosDto = produtos.map (produto -> new ProdutoDTO (produto));
        return ResponseEntity.ok ().body (produtosDto);
    }
}
