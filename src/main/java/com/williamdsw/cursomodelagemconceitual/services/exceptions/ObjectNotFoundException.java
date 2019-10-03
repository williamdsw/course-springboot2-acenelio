package com.williamdsw.cursomodelagemconceitual.services.exceptions;

public class ObjectNotFoundException extends RuntimeException
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS

    private static final long serialVersionUID = 1L;

    // ------------------------------------------------------------------------------------//
    // CONSTRUTORES
    
    public ObjectNotFoundException (String message)
    {
        super (message);
    }

    public ObjectNotFoundException (String message, Throwable cause)
    {
        super (message, cause);
    }
}