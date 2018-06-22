package com.michelzarpelon.cursomcmz.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.michelzarpelon.cursomcmz.services.execeptions.DataIntegrityException;
import com.michelzarpelon.cursomcmz.services.execeptions.ObjectNotFoundException;
/*classe manipuladora de Excessao, trabalhando como um filtro*/
@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class) /*tratador de excessao do tipo de excessao ObjectNotFoundException*/
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		StandardError erro = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	
	@ExceptionHandler(DataIntegrityException.class) /*tratador de excessao do tipo de excessao DataIntegrityException*/
	public ResponseEntity<StandardError> dataIntegrety(DataIntegrityException e, HttpServletRequest request){
		StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class) /*tratador de excessao do tipo de excessao MethodArgumentNotValidException*/
	public ResponseEntity<StandardError> dataIntegrety(MethodArgumentNotValidException e, HttpServletRequest request){
		
		ValidationError erro = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de Validação", System.currentTimeMillis());
		for(FieldError x : e.getBindingResult().getFieldErrors()) {
			erro.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	
	
}
