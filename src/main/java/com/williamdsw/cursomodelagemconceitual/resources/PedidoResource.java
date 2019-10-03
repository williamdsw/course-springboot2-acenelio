package com.williamdsw.cursomodelagemconceitual.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.williamdsw.cursomodelagemconceitual.domain.Pedido;
import com.williamdsw.cursomodelagemconceitual.services.PedidoService;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping (value = "/pedidos")
public class PedidoResource
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS

    @Autowired
    private PedidoService service;

    // ------------------------------------------------------------------------------------//
    // FUNCOES AUXILIARES
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pedido> findByID (@PathVariable Integer id)
    {
        Pedido pedido = service.findByID (id);
        return ResponseEntity.ok ().body (pedido);
    }
    
    @RequestMapping (method = RequestMethod.POST)
    public ResponseEntity<Void> insert (@Valid @RequestBody Pedido pedido)
    {
        pedido = service.insert (pedido);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest ().path ("/{id}").buildAndExpand (pedido.getId ()).toUri ();
        return ResponseEntity.created (uri).build ();
    }
}