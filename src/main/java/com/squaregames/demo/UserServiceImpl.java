package com.squaregames.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao; // Injection de dépendances correcte

    private final AtomicInteger idCounter = new AtomicInteger();

    @Override
    public User getUserById(int userId) {
        return userDao.findById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User updateUser(int userId, User user) {
        User existingUser = getUserById(userId);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            return userDao.upsert(existingUser);
        }
        return null;
    }

    @Override
    public void deleteUser(int userId) {
        userDao.delete(userId);
    }

    @Override
    public UserDto createUser(UserCreationParams params) {
        User newUser = new User();
        newUser.setId(idCounter.incrementAndGet());
        newUser.setName(params.name);
        newUser.setEmail(params.email);
        newUser.setPassword(params.password);
        userDao.upsert(newUser);
        return new UserDto(newUser.getId(), newUser.getEmail());
    }

    @Override
    public UserDto getUser(String userId) {
        int id = Integer.parseInt(userId);
        User user = getUserById(id);
        if (user != null) {
            return new UserDto(user.getId(), user.getEmail());
        }
        return null;
    }
}
