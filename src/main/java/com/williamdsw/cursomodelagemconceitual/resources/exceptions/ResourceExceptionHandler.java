package com.williamdsw.cursomodelagemconceitual.resources.exceptions;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.williamdsw.cursomodelagemconceitual.services.exceptions.AuthorizationException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.williamdsw.cursomodelagemconceitual.services.exceptions.DataIntegrityException;
import com.williamdsw.cursomodelagemconceitual.services.exceptions.FileException;
import com.williamdsw.cursomodelagemconceitual.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler
{
    // ------------------------------------------------------------------------------------//
    // FUNCOES AUXILIARES

    // @ExceptionHandler = Indica que e um tratador de excessoes de uma classe especifica
    // Objeto nao encontrado
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound (ObjectNotFoundException exception, HttpServletRequest request)
    {
        StandardError error = new StandardError (System.currentTimeMillis (), HttpStatus.NOT_FOUND.value (), "Não Encontrado", exception.getMessage (), request.getRequestURI ());
        return ResponseEntity.status (HttpStatus.NOT_FOUND).body (error);
    }

    // Erro na integridade dos dados
    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity (DataIntegrityException exception, HttpServletRequest request)
    {
        StandardError error = new StandardError (System.currentTimeMillis (), HttpStatus.BAD_REQUEST.value (), "Integridade de Dados", exception.getMessage (), request.getRequestURI ());
        return ResponseEntity.status (HttpStatus.BAD_REQUEST).body (error);
    }

    // Erro de validacao
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validation (MethodArgumentNotValidException exception, HttpServletRequest request)
    {
        ValidationError error = new ValidationError (System.currentTimeMillis (), HttpStatus.UNPROCESSABLE_ENTITY.value (), "Erro de Validação", exception.getMessage (), request.getRequestURI ());

        exception.getBindingResult ().getFieldErrors ().forEach (fieldError ->
        {
            error.addError (fieldError.getField (), fieldError.getDefaultMessage ());
        });

        return ResponseEntity.status (HttpStatus.BAD_REQUEST).body (error);
    }
    
    // Erro de autorizacao
    @ExceptionHandler (AuthorizationException.class)
    public ResponseEntity<StandardError> authorization (AuthorizationException exception, HttpServletRequest request)
    {
        StandardError error = new StandardError (System.currentTimeMillis (), HttpStatus.FORBIDDEN.value (), "Acesso Negado", exception.getMessage (), request.getRequestURI ());
        return ResponseEntity.status (HttpStatus.FORBIDDEN).body (error);
    }
    
    // Erro na criacao de arquivo
    @ExceptionHandler (FileException.class)
    public ResponseEntity<StandardError> file (FileException exception, HttpServletRequest request)
    {
        StandardError error = new StandardError (System.currentTimeMillis (), HttpStatus.BAD_REQUEST.value (), "Erro de Arquivo", exception.getMessage (), request.getRequestURI ());
        return ResponseEntity.status (HttpStatus.BAD_REQUEST).body (error);
    }
    
    // Erro a se conectar com servico da Amazon
    @ExceptionHandler (AmazonServiceException.class)
    public ResponseEntity<StandardError> amazonService (AmazonServiceException exception, HttpServletRequest request)
    {
        HttpStatus status = HttpStatus.valueOf (exception.getErrorCode ());
        StandardError error = new StandardError (System.currentTimeMillis (), status.value (), "Erro Amazon Service", exception.getMessage (), request.getRequestURI ());
        return ResponseEntity.status (status).body (error);
    }
    
    // Erro ao utilizar o cliente da Amazon
    @ExceptionHandler (AmazonClientException.class)
    public ResponseEntity<StandardError> amazonClient (AmazonClientException exception, HttpServletRequest request)
    {
        StandardError error = new StandardError (System.currentTimeMillis (), HttpStatus.BAD_REQUEST.value (), "Erro Amazon Client", exception.getMessage (), request.getRequestURI ());
        return ResponseEntity.status (HttpStatus.BAD_REQUEST).body (error);
    }
    
    // Erro ao utilizar o S3 da Amazon
    @ExceptionHandler (AmazonS3Exception.class)
    public ResponseEntity<StandardError> amazonS3 (AmazonS3Exception exception, HttpServletRequest request)
    {
        StandardError error = new StandardError (System.currentTimeMillis (), HttpStatus.BAD_REQUEST.value (), "Erro Amazon S3", exception.getMessage (), request.getRequestURI ());
        return ResponseEntity.status (HttpStatus.BAD_REQUEST).body (error);
    }
}