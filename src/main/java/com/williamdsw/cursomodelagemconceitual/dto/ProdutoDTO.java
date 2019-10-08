package com.williamdsw.cursomodelagemconceitual.dto;

import com.williamdsw.cursomodelagemconceitual.domain.Produto;
import java.io.Serializable;

/**
 * @author William
 */
public class ProdutoDTO implements Serializable
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;
    private double preco;

    // ------------------------------------------------------------------------------------//
    // CONSTRUTORES
    
    public ProdutoDTO () { }
    public ProdutoDTO (Produto produto)
    {
        super ();
        this.id = produto.getId ();
        this.nome = produto.getNome ();
        this.preco = produto.getPreco ();
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

    public double getPreco ()
    {
        return preco;
    }

    public void setPreco (double preco)
    {
        this.preco = preco;
    }
}