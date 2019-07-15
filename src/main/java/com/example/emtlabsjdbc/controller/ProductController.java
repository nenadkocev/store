package com.example.emtlabsjdbc.controller;

import com.example.emtlabsjdbc.exceptions.ResourceNotFoundException;
import com.example.emtlabsjdbc.model.Product;
import com.example.emtlabsjdbc.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    List<Product> findAll(){
        List<Product> products = productService.findAll();
        return products;
    }

    @GetMapping("/{id}")
    Product getProductById(@PathVariable(name = "id") Long id){
        Optional<Product> product = productService.getProductById(id);
        if(!product.isPresent())
            throw new ResourceNotFoundException();
        return product.get();
    }

    @PostMapping("/add")
    void insertProduct(@RequestBody Product product){
        productService.insertProduct(product);
    }
}
