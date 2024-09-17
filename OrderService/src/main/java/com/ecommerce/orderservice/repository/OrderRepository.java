package com.ecommerce.orderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.orderservice.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders,Long> {

	List<Orders> findByUserId(Long userId);

}
