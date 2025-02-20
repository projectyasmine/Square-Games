package com.squaregames.demo.dao;

import com.squaregames.demo.service.User;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public interface UserDao {
        @NotNull
        List<User> findAll();
        User findById(UUID userId);
        @NotNull User upsert(@NotNull User user);
        void delete(UUID userId);
}
