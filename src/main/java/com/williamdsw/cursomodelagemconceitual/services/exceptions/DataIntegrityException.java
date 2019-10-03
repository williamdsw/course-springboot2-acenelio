package com.williamdsw.cursomodelagemconceitual.services.exceptions;

public class DataIntegrityException extends RuntimeException
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS

    private static final long serialVersionUID = 1L;

    // ------------------------------------------------------------------------------------//
    // CONSTRUTORES
    
    public DataIntegrityException (String message)
    {
        super (message);
    }

    public DataIntegrityException (String message, Throwable cause)
    {
        super (message, cause);
    }
}