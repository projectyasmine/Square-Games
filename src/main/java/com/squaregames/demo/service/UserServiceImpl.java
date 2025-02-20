package com.squaregames.demo.service;

import com.squaregames.demo.controller.UserCreationParams;
import com.squaregames.demo.controller.UserDto;
import com.squaregames.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Qualifier("jpaUserDao")
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(UUID userId) {
        return userDao.findById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User updateUser(UUID userId, User user) {
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
    public void deleteUser(UUID userId) {
        userDao.delete(userId);
    }

    @Override
    public UserDto createUser(UserCreationParams params) {
        User newUser = new User();
        newUser.setId(UUID.randomUUID());
        newUser.setName(params.name);
        newUser.setEmail(params.email);
        newUser.setPassword(params.password);
        userDao.upsert(newUser);
        return new UserDto(newUser.getId(), newUser.getEmail());
    }

    @Override
    public UserDto getUser(String userId) {
        UUID id = UUID.fromString(userId);
        User user = getUserById(id);
        if (user != null) {
            return new UserDto(user.getId(), user.getEmail());
        }
        return null;
    }
}
