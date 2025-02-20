package com.squaregames.demo.dao;

import com.squaregames.demo.service.User;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryUserDao implements UserDao {
    private final ConcurrentMap<UUID, User> userMap = new ConcurrentHashMap<>();

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public User findById(@NotNull UUID userId) {
        return userMap.get(userId);
    }

    @Override
    public User upsert(User user) {
        if (user.getId() == null) {
            user.setId(UUID.randomUUID());
        }
        userMap.put(user.getId(), user);
        return user;
    }

    @Override
    public void delete(UUID userId) {
        userMap.remove(userId);
    }
}
