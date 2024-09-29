package com.diegodev.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegodev.course.entities.User;
import com.diegodev.course.repositories.UserRepository;

//@Component //registrando como componente, e ele agora vai poder ser injetado automaticamento em outras classes atraves do Autowired
@Service //faz a mesma coisa que o component porem eu informo que é um serviço, como ja estou na minha classe de serviço vou usala
public class UserServices {
	
	//classe de serviço classe que é responsavel pela lógica de negócio
	//se essa classe é a responsavel pela logica, então é ela que precisa acessar o banco de dados 

	@Autowired //anotação que serve para injeção de dependencias, com essa anotação o spring boot criara um instancia automaticamente
	private UserRepository repository; //atributo do tipo UserRepository
	
	//metodo para acessar o banco e retornar todos os usuarios
	public List<User> findAll() {
		
		return repository.findAll();
	}
	
	//metodo responsavel por acessar o banco a retornar o usuario com base no Id
	public User findById(Long id) {
		
		//optional lida com valores que podem ou não estar presentes evitando o uso de null, e evitando o risco de exeções
		Optional<User> obj = repository.findById(id);
		
		
		return obj.get(); //operação get ira retornar o obj user que esta dentro do optional
	}
	
	
	//metodo para inserir um novo objeto do tipo User
	public User insert(User obj) {
		return repository.save(obj); //esse metodo por padrão ja retorna o objeto criado
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
