package com.diegodev.course.services.exception;

//classe responsavel por tratar a exception de violação de integridade 
//quando eu tento exclui alguem do meu banco e esse alguem esta associado a outra entidade
//EX: se eu excluir o id numero 1 vai dar erro pois ele tem um pedido associado a ele
public class DatabaseException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DatabaseException(String msg) {
		super(msg);
	}

}
