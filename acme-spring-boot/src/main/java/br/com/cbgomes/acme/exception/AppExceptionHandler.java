package br.com.cbgomes.acme.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.cbgomes.acme.client.model.response.ErrorMensage;
import br.com.cbgomes.acme.client.resource.exception.StandardError;

@ControllerAdvice
public class AppExceptionHandler  {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request){
		StandardError err = new StandardError();
		err.setTimestemp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("resouce not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<StandardError> validationEX(ValidationException e, HttpServletRequest request){
		StandardError err = new StandardError();
		err.setTimestemp(Instant.now());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setError("validation problem");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
}
