package com.diegodev.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegodev.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
