package com.example.API_Product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.API_Product.model.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
}

