package com.diegodev.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diegodev.course.entities.Product;
import com.diegodev.course.services.ProductService;

//essa classe vai ser os recursos da minha aplicação 

//anotaçoes

@RestController //transforma essa classe em um controlador REST. os metodos dentro dessa classe irão lidar com requisições Http, e retornar dados em json
@RequestMapping(value = "/products") //define a url base que esse controlador ira gerenciar, todas as requisições /users são tratadas por essa classe
public class ProductResource {
	
	//Classe ProductResource ou controlador responsavel pela requisições http insformados pelo usuario
	//o controlador pega essa requisição, e retorna os dados para o usuario em formato json
	
	//esse controlador recebe a requisição, acessa o serviço e retorna para o usuario tal requisição se é GET, POST, PUT, DELETE...
	@Autowired //injetando uma instancia automaticamente
	private ProductService service;

	@GetMapping //esse metodo esta definido para lidar com requisições HTTP GET, graças a essa anotação, quando fazer uma requisição GET ex(localhost:8080/users) é esse metodo que sera chamado
	public ResponseEntity<List<Product>> findAll(){
		
		List<Product> list = service.findAll();
		
		//ResponseEntity é uma classe que representa toda a resposta http, incluindo o status e o corpo da resposta
		return ResponseEntity.ok().body(list); //retorner para o requisição, o ResponseEntity.ok() verifica se esta tudo ok, se estiver ok chamara o metodo body(u) 'corpo', e mostrara na tela os valores de user em formato json

	}
	
	@GetMapping(value = "/{id}") //vai indicar que minha requisição vai aceitar um id dentro da minha url
	public ResponseEntity<Product> findById(@PathVariable Long id){ //para que esse metodo receba um id na minha url eu preciso colocar a anotação @PathVariable
		
		Product user = service.findById(id);
		
		return ResponseEntity.ok().body(user);
	}
}
