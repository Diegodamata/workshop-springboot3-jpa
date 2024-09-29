package com.diegodev.course.services.exception;

//exception responsavel por tratar dos erros para os meus serviços 
public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Object id) {
		super("Resource not found. Id: " + id);
	}
}
