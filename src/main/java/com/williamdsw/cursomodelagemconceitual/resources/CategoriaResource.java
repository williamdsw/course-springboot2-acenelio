package com.williamdsw.cursomodelagemconceitual.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.williamdsw.cursomodelagemconceitual.domain.Categoria;
import com.williamdsw.cursomodelagemconceitual.dto.CategoriaDTO;
import com.williamdsw.cursomodelagemconceitual.services.CategoriaService;

// Controlador REST que responde pelo endpoint "/categorias"
@RestController
@RequestMapping (value = "/categorias")
public class CategoriaResource
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS

    @Autowired
    private CategoriaService service;

    // ------------------------------------------------------------------------------------//
    // FUNCOES AUXILIARES
    
    @RequestMapping (method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll ()
    {
        List<Categoria> categorias = service.findAll ();
        List<CategoriaDTO> categoriasDTO = categorias.stream ().map (categoria -> new CategoriaDTO (categoria)).collect (Collectors.toList ());
        return ResponseEntity.ok ().body (categoriasDTO);
    }

    // 1) Sera chamado utilizando "/page" na URI
    // 2) @RequestParam = Indica que e um parametro da requisicao
    // 3) Page = classe que encapsula informacoes sobre a paginacao.
    @RequestMapping (value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage (
            @RequestParam(value = "page", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction)
    {
        Page<Categoria> categorias = service.findPage (pageNumber, linesPerPage, orderBy, direction);
        Page<CategoriaDTO> categoriasDTO = categorias.map (categoria -> new CategoriaDTO (categoria));
        return ResponseEntity.ok ().body (categoriasDTO);
    }

    // 1) RequestMethod.GET = Indica busca de dados
    // 2) @PathVariable = Indica que o valor sera recebido da URL
    // 3) ResponseEntity<?> = Encapsula varias informacoes de uma resposta HTTP
    @RequestMapping (value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> findByID (@PathVariable Integer id)
    {
        Categoria categoria = service.findByID (id);
        return ResponseEntity.ok ().body (categoria);
    }

    // 1) RequestMethod.POST = Indica insercao de dados
    // 2) @RequestBody = Indica que o valor JSON sera convertido automaticamente pra objeto
    // 3) Criando URI de resposta necessaria
    // 4) ResponseEntity.created (uri).build () = Cria URI de resposta '201' necessaria
    // 5) @Valid = Indica que o parametro sera validado antes de todas operacoes
    @RequestMapping (method = RequestMethod.POST)
    public ResponseEntity<Void> insert (@Valid @RequestBody CategoriaDTO categoriaDTO)
    {
        Categoria categoria = service.fromDTO (categoriaDTO);
        categoria = service.insert (categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest ().path ("/{id}").buildAndExpand (categoria.getId ()).toUri ();
        return ResponseEntity.created (uri).build ();
    }

    // 1) RequestMethod.PUT = Indica alteracao de dados
    // 2) No Update precisa combinar @RequestBody e @PathVariable
    // 3) ResponseEntity.noContent ().build () = Indica mensagem de 204 de sucesso mas sem conteudo de retorno
    @RequestMapping (value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update (@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Integer id)
    {
        Categoria categoria = service.fromDTO (categoriaDTO);
        categoria.setId (id);
        categoria = service.update (categoria);
        return ResponseEntity.noContent ().build ();
    }

    // 1) RequestMethod.DELETE = Indica exclusao de dados
    @RequestMapping (value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteByID (@PathVariable Integer id)
    {
        service.deleteByID (id);
        return ResponseEntity.noContent ().build ();
    }
}