package com.example.emtlabsjdbc.controller;

import com.example.emtlabsjdbc.model.Category;
import com.example.emtlabsjdbc.service.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    void saveCategory(@RequestBody Category category, HttpServletResponse response){
        if(category.getName() == null || category.getName().trim().length() == 0)
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        this.categoryService.save(category);
    }
}
