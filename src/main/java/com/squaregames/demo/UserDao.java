package com.squaregames.demo;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface UserDao {
        @NotNull
        List<User> findAll();
        User findById(@NotNull int userId);
        @NotNull User upsert(@NotNull User user);
        void delete(@NotNull int userId);
}
