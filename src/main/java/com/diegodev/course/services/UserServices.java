package com.diegodev.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.diegodev.course.entities.User;
import com.diegodev.course.repositories.UserRepository;
import com.diegodev.course.services.exception.DatabaseException;
import com.diegodev.course.services.exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

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
		
		//em vez de chamar apenas o objeto get() para retornar o conteudo de Optional e se tiver vazio não retorna nada
		//orElseThrow é igual o metodo get porem ele trata uma exception se não conter nenhum conteudo no optional
		//recebe uma finção anonima chamando a minha exception personalizada, e passo o id que o usuario pediu para filtrar
		return obj.orElseThrow(() -> new ResourceNotFoundException(id)); 
	}
	
	
	//metodo para inserir um novo objeto do tipo User
	public User insert(User obj) {
		return repository.save(obj); //esse metodo por padrão ja retorna o objeto criado
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) { //exception de dados não encontrado ou vazio
			throw new ResourceNotFoundException(id); //chamo a minha exception personalizada
		} catch( DataIntegrityViolationException e) { //exception de violação de dados, quando eu tento excluir um usuario q tem outras entidades associado a ele
			throw new DatabaseException(e.getMessage());
		}
	}
	
	//para tratar exception de update apenas preciso verificar se o id do usuario existe ou não
	
	@Transactional //precisei colocar essa anotação pois estava dando um lazy loading, então informei que essa minha operação estara
	//dentro de uma transação dessa forma é garantido que todas as alterações sejam feitas em uma transação e tendo sucesso 
	//metodo para atualizar um usuario, preciso do id do usuario e o User responsavel por esse id
	public User update(Long id, User obj) {
		try {
		//o findById ele me tras do banco o usuario com base no id, ja o getReferenceById ele prepara o objeto para uma alteração
		User entity = repository.getReferenceById(id);
		
		updateData(entity, obj);
		
		return repository.save(entity);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	public void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
