package com.pin.eshop.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pin.eshop.model.Product;

public class ProductRepository extends MongoRepository<Product, String> {
    public List<Product> findByTitle(String title);
}