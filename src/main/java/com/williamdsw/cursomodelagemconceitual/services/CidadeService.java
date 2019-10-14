package com.williamdsw.cursomodelagemconceitual.services;

import com.williamdsw.cursomodelagemconceitual.domain.Cidade;
import com.williamdsw.cursomodelagemconceitual.repositories.CidadeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author William
 */
@Service
public class CidadeService
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    @Autowired
    private CidadeRepository repository;
    
    // ------------------------------------------------------------------------------------//
    // FUNCOES AUXILIARES
    
    public List<Cidade> findByEstado (Integer estadoID)
    {
        return repository.findByEstadoIdOrderByNomeAsc (estadoID);
    }
}