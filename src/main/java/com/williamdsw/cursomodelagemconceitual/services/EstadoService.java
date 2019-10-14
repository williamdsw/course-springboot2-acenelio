package com.williamdsw.cursomodelagemconceitual.services;

import com.williamdsw.cursomodelagemconceitual.domain.Estado;
import com.williamdsw.cursomodelagemconceitual.repositories.EstadoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author William
 */
@Service
public class EstadoService
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    @Autowired
    private EstadoRepository repository;
    
    // ------------------------------------------------------------------------------------//
    // FUNCOES AUXILIARES
    
    public List<Estado> findAllByOrderByNome ()
    {
        return repository.findAllByOrderByNome ();
    }
}