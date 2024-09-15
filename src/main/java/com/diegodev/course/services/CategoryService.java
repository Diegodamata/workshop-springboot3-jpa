package com.diegodev.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegodev.course.entities.Category;
import com.diegodev.course.repositories.CategoryResository;

@Service
public class CategoryService {

	@Autowired
	private CategoryResository categoryRepository;
	
	public List<Category> findAll(){
		
		return categoryRepository.findAll();
	}
	
	public Category findById(Long id) {
		
		Optional<Category> obj = categoryRepository.findById(id);
		
		return obj.get();
	}
}
