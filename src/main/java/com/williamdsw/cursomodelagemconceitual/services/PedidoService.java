package com.williamdsw.cursomodelagemconceitual.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.williamdsw.cursomodelagemconceitual.domain.Categoria;
import com.williamdsw.cursomodelagemconceitual.domain.Pedido;
import com.williamdsw.cursomodelagemconceitual.repositories.PedidoRepository;
import com.williamdsw.cursomodelagemconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS

    @Autowired
    private PedidoRepository repository;

    // ------------------------------------------------------------------------------------//
    // FUNCOES AUXILIARES
    
    public Pedido findByID (Integer id)
    {
        Optional<Pedido> pedido = repository.findById (id);
        return pedido.orElseThrow (() -> new ObjectNotFoundException (" Objeto não encontrado! " + " Id: " + id + " Tipo: " + Categoria.class.getName ()));
    }
}