package com.michelzarpelon.cursomcmz.services.execeptions;


public class ObjectNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	
	public ObjectNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public ObjectNotFoundException(String mensagem, Throwable causa) {
		super(mensagem,causa);
	}
	
	
}