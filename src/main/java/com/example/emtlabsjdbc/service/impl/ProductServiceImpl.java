package com.example.emtlabsjdbc.service.impl;

import com.example.emtlabsjdbc.model.Product;
import com.example.emtlabsjdbc.repository.ProductRepository;
import com.example.emtlabsjdbc.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.getProductById(id);
    }

    @Override
    public void insertProduct(Product product) {
        productRepository.insertProduct(product);
    }
}
