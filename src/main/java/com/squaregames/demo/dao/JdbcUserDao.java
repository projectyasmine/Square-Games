package com.squaregames.demo.dao;

import com.squaregames.demo.service.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class JdbcUserDao implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    @NotNull
    public List<User> findAll() {
        String query = "SELECT * FROM users";
        return jdbcTemplate.query(query, (resultSet, rowNum) -> {
            User user = new User();
            user.setId(UUID.fromString(resultSet.getString("id")));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            return user;
        });
    }

    @Override
    public User findById(@NotNull UUID userId) {
        String query = "SELECT * FROM users WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", userId.toString());
        List<User> users = jdbcTemplate.query(query, namedParameters, (resultSet, rowNum) -> {
            User user = new User();
            user.setId(UUID.fromString(resultSet.getString("id")));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            return user;
        });
        return users.stream().findFirst().orElse(null);
    }

    @Override
    @NotNull
    public User upsert(@NotNull User user) {
        String query = "INSERT INTO users (id, name, email, password) VALUES (:id, :name, :email, :password) " +
                "ON DUPLICATE KEY UPDATE name = :name, email = :email, password = :password";
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
        jdbcTemplate.update(query, namedParameters);
        return user;
    }

    @Override
    public void delete(@NotNull UUID userId) {
        String query = "DELETE FROM users WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", userId.toString());
        jdbcTemplate.update(query, namedParameters);
    }
}
