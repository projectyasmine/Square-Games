package com.squaregames.demo;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public interface UserDao {
        @NotNull
        List<User> findAll();
        Optional<User> findById(@NotNull int userId);
        @NotNull User upsert(@NotNull User user);
        void delete(@NotNull int userId);
}
