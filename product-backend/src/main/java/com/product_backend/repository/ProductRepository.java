package com.product_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product_backend.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
    
}
