package com.ecommerce.productservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ecommerce.productservice.entity.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
	List<Product> findByNameContaining(String query);
}

