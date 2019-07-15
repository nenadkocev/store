package com.example.emtlabsjdbc.service;

import com.example.emtlabsjdbc.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();

    Optional<Product> getProductById(Long id);

    void insertProduct(Product product);
}
