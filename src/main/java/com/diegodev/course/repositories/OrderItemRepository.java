package com.diegodev.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegodev.course.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
}
