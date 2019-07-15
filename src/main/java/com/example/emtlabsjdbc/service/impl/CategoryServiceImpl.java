package com.example.emtlabsjdbc.service.impl;

import com.example.emtlabsjdbc.model.Category;
import com.example.emtlabsjdbc.repository.CategoryRepository;
import com.example.emtlabsjdbc.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }
}
