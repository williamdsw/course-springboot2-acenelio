package com.williamdsw.cursomodelagemconceitual.dto;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author William
 */
public class EmailDTO implements Serializable
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    private final static long serialVersionUID = 1L;
    
    @NotEmpty (message = "Preenchiemento Obrigatório")
    @Email (message = "Email inválido")
    private String email;

    // ------------------------------------------------------------------------------------//
    // CONSTRUTORES
    
    public EmailDTO () {}
    public EmailDTO (String email)
    {
        this.email = email;
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
}