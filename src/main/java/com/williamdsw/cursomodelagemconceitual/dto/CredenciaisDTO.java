package com.williamdsw.cursomodelagemconceitual.dto;

import java.io.Serializable;

/**
 * @author William
 */
public class CredenciaisDTO implements Serializable
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    private static final long serialVersionUID = 1L;
    private String email;
    private String senha;
    
    // ------------------------------------------------------------------------------------//
    // CONSTRUTORES
    
    public CredenciaisDTO () {}
    public CredenciaisDTO (String email, String senha)
    {
        this.email = email;
        this.senha = senha;
    }
    
    // ------------------------------------------------------------------------------------//
    // GETTERS / SETTERS

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getSenha ()
    {
        return senha;
    }

    public void setSenha (String senha)
    {
        this.senha = senha;
    }
}