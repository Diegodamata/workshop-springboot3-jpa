package com.diegodev.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegodev.course.entities.Order;

//como essa interface tambem precisa ser injetada eu precisaria anotar @Repository, porem ela herda de JpaRepository que ja esta registrado como componente do spring
public interface OrderRepository extends JpaRepository<Order, Long>{

}
