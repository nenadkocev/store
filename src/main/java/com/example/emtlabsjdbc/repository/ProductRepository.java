package com.example.emtlabsjdbc.repository;

import com.example.emtlabsjdbc.model.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> findAll(){
        String sql = "SELECT * FROM product";
        List<Product> products = jdbcTemplate.query(sql, this::toProduct);
        return products;
    }

    public Optional<Product> getProductById(Long id){
        String sql = "SELECT * FROM product WHERE id = ?";
        Product product = null;
        try {
            product = jdbcTemplate.queryForObject(sql,
                    ((resultSet, i) -> new Product(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getString("image_url"))), id);
        }
        catch (EmptyResultDataAccessException exception){
            return Optional.empty();
        }
        return Optional.of(product);
    }

    public Product insertProduct(Product product){
        Long manufacturerId = product.getManufacturer() != null ? product.getManufacturer().getId() : null;
        Long categoryId = product.getCategory() != null ? product.getCategory().getId() : null;
        jdbcTemplate.update("INSERT INTO product(name, description, image_url, manufacturer_id, category_id) values (?, ?, ?, ?, ?)",
                product.getName(), product.getDescription(), product.getImageUrl(), product.getManufacturer().getId(), manufacturerId, categoryId);
        return product;
    }

    private Product toProduct(ResultSet resultSet, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setDescription(resultSet.getString("description"));
        product.setImageUrl(resultSet.getString("image_url"));
        product.setName(resultSet.getString("name"));
        return product;
    }
}
