package com.williamdsw.cursomodelagemconceitual.services.exceptions;

/**
 *
 * @author William
 */
public class FileException extends RuntimeException
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    private static final long serialVersionUID = 1L;
    
    // ------------------------------------------------------------------------------------//
    // CONSTRUTORES
    
    public FileException (String message)
    {
        super (message);
    }
    
    public FileException (String message, Throwable cause)
    {
        super (message, cause);
    }
}
