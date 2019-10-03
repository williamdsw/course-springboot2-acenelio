package com.williamdsw.cursomodelagemconceitual.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS

    private static final long serialVersionUID = 1L;
    private List<FieldMessage> errors = new ArrayList<> ();

    // ------------------------------------------------------------------------------------//
    // CONSTRUTORES
    
    public ValidationError (Integer status, String message, Long timestamp)
    {
        super (status, message, timestamp);
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