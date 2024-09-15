package com.diegodev.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegodev.course.entities.Category;

public interface CategoryResository extends JpaRepository<Category, Long>{
}
