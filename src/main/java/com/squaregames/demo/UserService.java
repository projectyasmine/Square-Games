package com.squaregames.demo;

import jakarta.validation.Valid;

import java.util.List;

public interface UserService {

    User getUserById(int userId);

    List<User> getAllUsers();

    User updateUser(int userId, User user);

    void deleteUser(int userId);

    UserDto createUser(@Valid UserCreationParams params);

    UserDto getUser(String userId);
}

