package com.squaregames.demo;

import jakarta.validation.Valid;

import java.util.ArrayList;

public interface UserService {

    User getUserById(int userId);

    ArrayList<User> getAllUsers();

    User updateUser(int userId, User user);

    void deleteUser(int userId);

    UserDto createUser(@Valid UserCreationParams params);

    UserDto getUser(String userId);
}

