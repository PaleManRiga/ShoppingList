package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Component
@Profile("local")
public class DefaultProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DefaultProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product insert(Product product) {
        String query = "insert into products (name, price, category, discount,description) values (" +
                "?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getName());
            ps.setBigDecimal(2, product.getPrice());
            ps.setInt(3, product.getCategory().ordinal());
            ps.setBigDecimal(4, product.getDiscount());
            ps.setString(5, product.getDescription());
            return ps;
        }, keyHolder);
        product.setId(keyHolder.getKey().longValue());
        return product;
    }

    @Override
    public Product findProductById(Long id) {
        String query = "select * from products where id=" + id;
        List<Product> products = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Product.class));
        return products.get(0);
    }

    @Override
    public void deleteProductById(Long id) {
        String query = "delete from products where id=" + id;
        jdbcTemplate.execute(query);

    }

    @Override
    public boolean isUnique(Product product) {
        String query = "select case when count(*)>0 then false else true end " +
                "from products p where p.name=" + "'" + product.getName() + "'";
        return jdbcTemplate.queryForObject(query, Boolean.class);
    }
}
