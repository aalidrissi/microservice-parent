package com.cih.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cih.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
