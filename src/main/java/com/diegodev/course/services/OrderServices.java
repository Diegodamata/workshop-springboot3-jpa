package com.diegodev.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegodev.course.entities.Order;
import com.diegodev.course.repositories.OrderRepository;

//@Component //registrando como componente, e ele agora vai poder ser injetado automaticamento em outras classes atraves do Autowired
@Service //faz a mesma coisa que o component porem eu informo que é um serviço, como ja estou na minha classe de serviço vou usala
public class OrderServices {

	@Autowired
	private OrderRepository repository;
	
	public List<Order> findAll() {
		
		return repository.findAll();
	}
	
	public Order findById(Long id) {
		//optional lida com valores que podem ou não estar presentes e vitando o uso de null, e evitando o risco de exeções
		Optional<Order> obj = repository.findById(id);
		
		return obj.get(); //operação get ira retornar o obj user que esta dentro do optional
	}
}
