package com.squaregames.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    // Déclaration et initialisation de la liste des utilisateurs
    private final List<User> users = new ArrayList<>();

    // Déclaration et initialisation du compteur atomique pour les IDs
    private final AtomicInteger idCounter = new AtomicInteger();

    @Override
    public User getUserById(int userId) {
        return userDao.findById(userId).orElse(null);
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return new ArrayList<>(users);
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
