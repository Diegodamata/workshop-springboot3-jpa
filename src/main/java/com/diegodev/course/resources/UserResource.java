package com.diegodev.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.diegodev.course.entities.User;
import com.diegodev.course.services.UserServices;

//essa classe vai ser os recursos da minha aplicação 

//anotaçoes

@RestController //transforma essa classe em um controlador REST. os metodos dentro dessa classe irão lidar com requisições Http, e retornar dados em json
@RequestMapping(value = "/users") //define a url base que esse controlador ira gerenciar, todas as requisições /users são tratadas por essa classe
public class UserResource {
	
	//Classe UserResource ou controlador responsavel pela requisições http insformados pelo usuario
	//o controlador pega essa requisição, e retorna os dados para o usuario em formato json
	
	//esse controlador recebe a requisição, acessa o serviço e retorna para o usuario tal requisição se é GET, POST, PUT, DELETE...
	@Autowired //injetando uma instancia automaticamente
	private UserServices service;

	@GetMapping //esse metodo esta definido para lidar com requisições HTTP GET, graças a essa anotação, quando fazer uma requisição GET ex(localhost:8080/users) é esse metodo que sera chamado
	public ResponseEntity<List<User>> findAll(){
		
		List<User> list = service.findAll();
		
		//ResponseEntity é uma classe que representa toda a resposta http, incluindo o status e o corpo da resposta
		return ResponseEntity.ok().body(list); //retorner para o requisição, o ResponseEntity.ok() verifica se esta tudo ok, se estiver ok chamara o metodo body(u) 'corpo', e mostrara na tela os valores de user em formato json

	}
	
	@GetMapping(value = "/{id}") //vai indicar que minha requisição vai aceitar um id dentro da minha url
	public ResponseEntity<User> findById(@PathVariable Long id){ //para que esse metodo receba um id na minha url eu preciso colocar a anotação @PathVariable
		
		User user = service.findById(id);
		
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping //anotação que ira receber um metodo post http, serve para criar um usuario
	public ResponseEntity<User> insert(@RequestBody User obj){ //para eu passar um objeto user, que esta no formato json, eu preciso desserializar
		//então eu informo a anotação @RequestBory, para desserializar trasformando em objeto novamente
		
		User user = service.insert(obj);
		
		//Quando eu retorno dessa forma a resposta http é a 200, falando que deu tudo certo
		//porem eu quero que mostre o 201, que significa algo que foi criado, no caso vai ser um cliente
		//return ResponseEntity.ok().body(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		//ServletUriComponentsBuilder é uma classe do Spring que serve para criar uma uri de forma dinamica com base nas informações da requisição HTTP atual
		
		//fromCurrentRequest() Obtém a URI da requisição atual (a URL base) a partir da qual o método está sendo chamado.
		
		//path("/{id}") Adiciona ao caminho atual um segmento adicional (/id), que será preenchido com o ID do recurso recém-criado.
		
		//buildAndExpand(obj.getId()) Substitui a variável de caminho {id} pelo ID real do recurso recém-criado.
		
		//toUri() Converte o objeto UriComponents em uma instância de URI, que será usada no cabeçalho Location.
		
		return ResponseEntity.created(uri).body(user);
		//created() ele recebe uma uri, é utilizado para retornar uma resposta HTTP com o código de status 201 Created, 
		//indicando que uma nova entidade foi criada com sucesso no servidor.
	}
}
