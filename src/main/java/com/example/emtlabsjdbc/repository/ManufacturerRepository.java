package com.example.emtlabsjdbc.repository;

import com.example.emtlabsjdbc.model.Manufacturer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ManufacturerRepository {

    private final JdbcTemplate jdbcTemplate;

    public ManufacturerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Manufacturer save(Manufacturer manufacturer){
        jdbcTemplate.update("INSERT INTO manufacturer(name) VALUES(?)", manufacturer.getName());
        return manufacturer;
    }
}
