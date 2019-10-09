package com.williamdsw.cursomodelagemconceitual.services.exceptions;

/**
 * @author William
 */
public class AuthorizationException extends RuntimeException
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    private static final long serialVersionUID = 1L;
    
    // ------------------------------------------------------------------------------------//
    // CONSTRUTORES
    
    public AuthorizationException (String message)
    {
        super (message);
    }
    
    public AuthorizationException (String message, Throwable cause)
    {
        super (message, cause);
    }
}