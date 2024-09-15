package com.diegodev.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diegodev.course.entities.Order;
import com.diegodev.course.services.OrderServices;

//essa classe vai ser os recursos da minha aplicação 

//anotaçoes

@RestController //transforma essa classe em um controlador REST. os metodos dentro dessa classe irão lidar com requisições Http, e retornar dados em json
@RequestMapping(value = "/orders") //define a url base que esse controlador ira gerenciar, todas as requisições /users são tratadas por essa classe
public class OrderResource {
	
	@Autowired
	private OrderServices service;

	@GetMapping //esse metodo esta definido para lidar com requisições HTTP GET, graças a essa anotação, quando fazer uma requisição GET ex(localhost:8080/users) é esse metodo que sera chamado
	public ResponseEntity<List<Order>> findAll(){
		
		List<Order> list = service.findAll();
		
		//ResponseEntity é uma classe que representa toda a resposta http, incluindo o status e o corpo da resposta
		return ResponseEntity.ok().body(list); //retorner para o requisição, o ResponseEntity.ok() verifica se esta tudo ok, se estiver ok chamara o metodo body(u) 'corpo', e mostrara na tela os valores de user em formato json

	}
	
	@GetMapping(value = "/{id}") //vai indicar que minha requisição vai aceitar um id dentro da minha url
	public ResponseEntity<Order> findById(@PathVariable Long id){ //para que esse metodo receba um id na minha url eu preciso colocar a anotação @PathVariable
		
		Order order = service.findById(id);
		
		return ResponseEntity.ok().body(order);
	}
}
