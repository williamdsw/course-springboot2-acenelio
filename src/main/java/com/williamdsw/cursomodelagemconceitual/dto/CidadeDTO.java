package com.williamdsw.cursomodelagemconceitual.dto;

import com.williamdsw.cursomodelagemconceitual.domain.Cidade;
import java.io.Serializable;

/**
 * @author William
 */
public class CidadeDTO implements Serializable
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;
    
    // ------------------------------------------------------------------------------------//
    // CONSTRUTORES
    
    public CidadeDTO () {}
    public CidadeDTO (Cidade cidade)
    {
        this.id = cidade.getId ();
        this.nome = cidade.getNome ();
    }

    // ------------------------------------------------------------------------------------//
    // GETTERS / SETTERS
    
    public Integer getId ()
    {
        return id;
    }

    public void setId (Integer id)
    {
        this.id = id;
    }

    public String getNome ()
    {
        return nome;
    }

    public void setNome (String nome)
    {
        this.nome = nome;
    }
}