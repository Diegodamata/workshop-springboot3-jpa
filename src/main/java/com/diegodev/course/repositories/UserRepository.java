package com.diegodev.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegodev.course.entities.User;

//como essa interface tambem precisa ser injetada eu precisaria anotar @Repository, 
//porem ela herda de JpaRepository que ja esta registrado como componente do spring
public interface UserRepository extends JpaRepository<User, Long>{
}


//essa interface estende da interface jpaRepository, que é responsavel por acessar o banco de dados
