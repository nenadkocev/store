package com.example.emtlabsjdbc.repository;

import com.example.emtlabsjdbc.model.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public CategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Category save(Category category){
        jdbcTemplate.update("INSERT INTO category(name) values (?)", category.getName());
        return category;
    }
}
