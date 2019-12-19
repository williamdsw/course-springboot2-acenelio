package com.williamdsw.cursomodelagemconceitual.resources;

import com.williamdsw.cursomodelagemconceitual.domain.Cidade;
import com.williamdsw.cursomodelagemconceitual.domain.Estado;
import com.williamdsw.cursomodelagemconceitual.dto.CidadeDTO;
import com.williamdsw.cursomodelagemconceitual.dto.EstadoDTO;
import com.williamdsw.cursomodelagemconceitual.services.CidadeService;
import com.williamdsw.cursomodelagemconceitual.services.EstadoService;

import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author William
 */
@RestController
@RequestMapping ("/estados")
public class EstadoResource
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    @Autowired
    private EstadoService service;
    
    @Autowired
    private CidadeService cidadeService;
    
    // ------------------------------------------------------------------------------------//
    // FUNCOES AUXILIARES
    
    @ApiOperation (value = "Lista todas Cidades")
    @RequestMapping (method = RequestMethod.GET)
    public ResponseEntity<List<EstadoDTO>> findAllByOrderByNome ()
    {
        List<Estado> estados = service.findAllByOrderByNome ();
        List<EstadoDTO> estadosDTO = estados.stream ().map (estado -> new EstadoDTO (estado)).collect (Collectors.toList ());
        return ResponseEntity.ok ().body (estadosDTO);
    }
    
    @ApiOperation (value = "Encontra Cidades pelo ID do Estado")
    @RequestMapping (value = "/{estadoID}/cidades", method = RequestMethod.GET)
    public ResponseEntity<List<CidadeDTO>> findCidades (@PathVariable Integer estadoID)
    {
        List<Cidade> cidades = cidadeService.findByEstado (estadoID);
        List<CidadeDTO> cidadesDTO = cidades.stream ().map (cidade -> new CidadeDTO (cidade)).collect (Collectors.toList ());
        return ResponseEntity.ok ().body (cidadesDTO);
    }
}
