package com.squaregames.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryUserDao implements UserDao {
    private final ConcurrentMap<Integer, User> userMap = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger();

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public Optional<User> findById(int userId) {
        return Optional.ofNullable(userMap.get(userId));
    }

    @Override
    public User upsert(User user) {
        if (user.getId() == 0) {
            user.setId(idCounter.incrementAndGet());
        }
        userMap.put(user.getId(), user);
        return user;
    }

    @Override
    public void delete(int userId) {
        userMap.remove(userId);
    }
}
