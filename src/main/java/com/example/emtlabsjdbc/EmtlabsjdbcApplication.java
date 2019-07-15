package com.example.emtlabsjdbc;

import com.example.emtlabsjdbc.model.Category;
import com.example.emtlabsjdbc.model.Manufacturer;
import com.example.emtlabsjdbc.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class EmtlabsjdbcApplication implements CommandLineRunner {


	private static Logger logger = LoggerFactory.getLogger(EmtlabsjdbcApplication.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(EmtlabsjdbcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Creating database tables...");

		jdbcTemplate.execute("DROP TABLE product IF EXISTS");
		jdbcTemplate.execute("DROP TABLE manufacturer IF EXISTS");
		jdbcTemplate.execute("DROP TABLE category IF EXISTS");


		jdbcTemplate.execute("CREATE TABLE category(" +
				"id SERIAL, name VARCHAR(255))");

		jdbcTemplate.execute("CREATE TABLE manufacturer(" +
				"id SERIAL, name VARCHAR(255))");

		jdbcTemplate.execute("CREATE TABLE product(" +
				"id SERIAL, name VARCHAR(255), description VARCHAR(255), image_url VARCHAR(255), " +
				"category_id BIGINT UNSIGNED REFERENCES category(id) ON DELETE SET NULL ON UPDATE CASCADE, " +
				"manufacturer_id BIGINT UNSIGNED REFERENCES manufacturer(id) ON DELETE SET NULL ON UPDATE CASCADE" +
				")");

		List<Category> categories = new ArrayList<>();
		categories.add(new Category("Obleka"));
		categories.add(new Category("za po doma"));
		categories.add(new Category("hrana"));

		String insertCategoriesQuery = "INSERT INTO category(name) values (?)";
		jdbcTemplate.batchUpdate(insertCategoriesQuery, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
				preparedStatement.setString(1, categories.get(i).getName());
			}

			@Override
			public int getBatchSize() {
				return categories.size();
			}
		});

		List<Manufacturer> manufacturers = new ArrayList<>();
		manufacturers.add(new Manufacturer("gorenje"));
		manufacturers.add(new Manufacturer("netcetera"));
		String insertIntoManufacturerQuery = "INSERT INTO manufacturer(name) values (?)";
		jdbcTemplate.batchUpdate(insertIntoManufacturerQuery, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
				preparedStatement.setString(1, manufacturers.get(i).getName());
			}

			@Override
			public int getBatchSize() {
				return manufacturers.size();
			}
		});

		List<Product> productList = new ArrayList<>();
		productList.add(new Product("Patiki", "platneno/kozen predmet koj se stava na nozete", "https://www.church-footwear.com/content/dam/churchs_products/E/EEG/EEG018/9AFMF0DQ6/EEG018_9AFM_F0DQ6_F_000000_SLS.png/_jcr_content/renditions/cq5dam.web.white.2560x2560.jpeg"));
		productList.add(new Product("Maica", "Predmet od pamuk koj go stiti torzoto od nadvoresni vlijanija", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Blue_Tshirt.jpg/330px-Blue_Tshirt.jpg"));

		String insertQuery = "INSERT INTO product(name, description, image_url) values (?, ?, ?)";
		jdbcTemplate.batchUpdate(insertQuery, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
				Product product = productList.get(i);
				preparedStatement.setString(1, product.getName());
				preparedStatement.setString(2, product.getDescription());
				preparedStatement.setString(3, product.getImageUrl());
			}

			@Override
			public int getBatchSize() {
				return productList.size();
			}
		});
	}
}
