package com.diegodev.course.resources.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.diegodev.course.services.exception.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

//classe responsavel por tratar as exception e retornar os erros

@ControllerAdvice //Essa anotação indica que a classe será responsável por capturar exceções que 
//ocorram durante o processamento de requisições em qualquer controlador da aplicação.
//Ela permite aplicar tratamento global de exceções, tornando o código mais organizado e centralizado.
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class) //essa anotação diz que esse metodo sera automaticamente chamado quando
	//uma exception do tipo ResourceNotFoundException for lançada
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){ //HttpServletRequest request: Esse parâmetro contém detalhes sobre a requisição HTTP que causou a exceção, 
																													//como a URL da requisição e outras informações pertinentes.
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND; //definindo o codigo http para retorn no caso o codigo 404, q significa que não foi encontrado
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());//request.getRequestURI(): A URI que foi requisitada no momento em que o erro ocorreu, para facilitar o rastreamento.
		return ResponseEntity.status(status).body(err);
	}
}
