package com.michelzarpelon.cursomcmz.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.michelzarpelon.cursomcmz.services.execeptions.DataIntegrityException;
import com.michelzarpelon.cursomcmz.services.execeptions.ObjectNotFoundException;
/*classe manipuladora de Excessao, trabalhando como um filtro*/
@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class) /*tratador de excessao do tipo de excessao ObjectNotFoundException*/
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException objNotFoundExeception, HttpServletRequest request){
		
		StandardError erro = new StandardError(HttpStatus.NOT_FOUND.value(), objNotFoundExeception.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	
	@ExceptionHandler(DataIntegrityException.class) /*tratador de excessao do tipo de excessao DataIntegrityException*/
	public ResponseEntity<StandardError> dataIntegrety(DataIntegrityException dataIntegrityException, HttpServletRequest request){
		
		StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(), dataIntegrityException.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
}
