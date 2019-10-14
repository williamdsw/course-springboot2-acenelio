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
import com.williamdsw.cursomodelagemconceitual.domain.Cliente;
import com.williamdsw.cursomodelagemconceitual.dto.ClienteDTO;
import com.williamdsw.cursomodelagemconceitual.dto.ClienteNewDTO;
import com.williamdsw.cursomodelagemconceitual.services.ClienteService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;

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
    
    @PreAuthorize ("hasAnyRole('ADMIN')")
    @RequestMapping (method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll ()
    {
        List<Cliente> categorias = service.findAll ();
        List<ClienteDTO> categoriasDTO = categorias.stream ().map (categoria -> new ClienteDTO (categoria)).collect (Collectors.toList ());
        return ResponseEntity.ok ().body (categoriasDTO);
    }

    @PreAuthorize ("hasAnyRole('ADMIN')")
    @RequestMapping (value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage (
            @RequestParam(value = "page", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction)
    {
        Page<Cliente> categorias = service.findPage (pageNumber, linesPerPage, orderBy, direction);
        Page<ClienteDTO> categoriasDTO = categorias.map (categoria -> new ClienteDTO (categoria));
        return ResponseEntity.ok ().body (categoriasDTO);
    }

    @RequestMapping (value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> findByID (@PathVariable Integer id)
    {
        Cliente cliente = service.findByID (id);
        return ResponseEntity.ok ().body (cliente);
    }
    
    @RequestMapping (value = "/email", method = RequestMethod.GET)
    public ResponseEntity<Cliente> findByEmail (@RequestParam (value = "value") String email)
    {
        Cliente cliente = service.findByEmail (email);
        return ResponseEntity.ok ().body (cliente);
    }

    @RequestMapping (method = RequestMethod.POST)
    public ResponseEntity<Void> insert (@Valid @RequestBody ClienteNewDTO clienteNewDTO)
    {
        Cliente cliente = service.fromDTO (clienteNewDTO);
        cliente = service.insert (cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest ().path ("/{id}").buildAndExpand (cliente.getId ()).toUri ();
        return ResponseEntity.created (uri).build ();
    }

    @RequestMapping (value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update (@Valid @RequestBody ClienteDTO categoriaDTO, @PathVariable Integer id)
    {
        Cliente categoria = service.fromDTO (categoriaDTO);
        categoria.setId (id);
        service.update (categoria);
        return ResponseEntity.noContent ().build ();
    }

    // 1) RequestMethod.DELETE = Indica exclusao de dados
    @PreAuthorize ("hasAnyRole('ADMIN')")
    @RequestMapping (value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteByID (@PathVariable Integer id)
    {
        service.deleteByID (id);
        return ResponseEntity.noContent ().build ();
    }
    
    @RequestMapping (value = "/picture", method = RequestMethod.POST)
    public ResponseEntity<Void> uploadProfilePicture (@RequestParam (name = "file") MultipartFile multipartFile)
    {
        URI uri = service.uploadProfilePicture (multipartFile);
        return ResponseEntity.created (uri).build ();
    }
}