package com.cih.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cih.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
