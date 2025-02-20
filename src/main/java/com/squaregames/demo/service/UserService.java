package com.squaregames.demo.service;

import com.squaregames.demo.controller.UserCreationParams;
import com.squaregames.demo.controller.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User getUserById(UUID userId);
    List<User> getAllUsers();
    User updateUser(UUID userId, User user);
    void deleteUser(UUID userId); //
    UserDto createUser(UserCreationParams params);
    UserDto getUser(String userId);
}
