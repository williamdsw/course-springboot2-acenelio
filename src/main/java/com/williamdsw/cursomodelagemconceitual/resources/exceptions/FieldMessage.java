package com.williamdsw.cursomodelagemconceitual.resources.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable
{
	// ------------------------------------------------------------------------------------//
	// CAMPOS
	
	private static final long serialVersionUID = 1L;
	private String fieldName;
	private String message;
	
	// ------------------------------------------------------------------------------------//
	// CONSTRUTORES
	
	public FieldMessage () {}
	public FieldMessage (String fieldName, String message)
	{
		super ();
		this.fieldName = fieldName;
		this.message = message;
	}

	// ------------------------------------------------------------------------------------//
	// GETTERS / SETTERS
	
	public String getFieldName ()
	{ return fieldName; }

	public void setFieldName (String fieldName)
	{ this.fieldName = fieldName; }

	public String getMessage ()
	{ return message; }

	public void setMessage (String message)
	{ this.message = message; }
}