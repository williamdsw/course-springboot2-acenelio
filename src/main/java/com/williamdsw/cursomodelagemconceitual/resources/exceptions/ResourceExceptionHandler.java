package com.williamdsw.cursomodelagemconceitual.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.williamdsw.cursomodelagemconceitual.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler
{
	// ------------------------------------------------------------------------------------//
	// FUNCOES AUXILIARES
	
	// @ExceptionHandler = Indica que e um tratador de excessoes de uma classe especifica
	@ExceptionHandler (ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound (ObjectNotFoundException exception, HttpServletRequest request)
	{
		StandardError error = new StandardError (HttpStatus.NOT_FOUND.value (), exception.getMessage (), System.currentTimeMillis ());
		return ResponseEntity.status (HttpStatus.NOT_FOUND).body (error);
	}
}