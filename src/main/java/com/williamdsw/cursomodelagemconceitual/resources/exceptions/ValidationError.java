package com.williamdsw.cursomodelagemconceitual.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS

    private static final long serialVersionUID = 1L;
    private final List<FieldMessage> errors = new ArrayList<> ();

    // ------------------------------------------------------------------------------------//
    // CONSTRUTORES

    public ValidationError (Long timestamp, Integer status, String error, String message, String path)
    {
        super (timestamp, status, error, message, path);
    }
    
    // ------------------------------------------------------------------------------------//
    // GETTERS
    
    public List<FieldMessage> getErrors ()
    {
        return errors;
    }

    // ------------------------------------------------------------------------------------//
    // FUNCOES AUXILIARES
    
    public void addError (String fieldName, String message)
    {
        errors.add (new FieldMessage (fieldName, message));
    }
}