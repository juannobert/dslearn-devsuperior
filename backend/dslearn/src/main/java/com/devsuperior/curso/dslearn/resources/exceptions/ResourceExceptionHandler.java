package com.devsuperior.curso.dslearn.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.curso.dslearn.services.exceptions.DataBaseException;
import com.devsuperior.curso.dslearn.services.exceptions.ForbiddenException;
import com.devsuperior.curso.dslearn.services.exceptions.ResourceNotFoundException;
import com.devsuperior.curso.dslearn.services.exceptions.UnauthorizedException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e,HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		var err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Resource not Found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
		
	}
	
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> dataBaseViolation(DataBaseException e,HttpServletRequest request) {
		var err = new StandardError();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Resource not Found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
		
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e,HttpServletRequest request) {
		var err = new ValidationError();
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Resource not Found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		for(FieldError field : e.getBindingResult().getFieldErrors()) {
			err.add(field.getField(), field.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(err);
		
	}
	
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<OAuthCustomError> forbidden(ForbiddenException e,HttpServletRequest request) {
		var err = new OAuthCustomError("Forbidden",e.getMessage());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
		
	}
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<OAuthCustomError> unauthorized(UnauthorizedException e,HttpServletRequest request) {
		var err = new OAuthCustomError("Unauthorized",e.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
		
	}
	
	
}
