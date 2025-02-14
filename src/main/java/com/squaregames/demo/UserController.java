package com.squaregames.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserDto createUser(@RequestBody UserCreationParams params) {
        User user = new User();
        user.setName(params.getName());
        user.setEmail(params.getEmail());
        user.setPassword(params.getPassword());
        User createdUser = userService.createUser(user);
        return new UserDto(createdUser.getId(), createdUser.getName(), createdUser.getEmail());
    }

    @GetMapping("users/{userId}")
    public UserDto getUser(@PathVariable int userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return new UserDto(user.getId(), user.getName(), user.getEmail());
        }
        return null;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }

    @PutMapping("users/{userId}")
    public UserDto updateUser(@PathVariable int userId, @RequestBody UserCreationParams params) {
        User user = new User();
        user.setName(params.getName());
        user.setEmail(params.getEmail());
        user.setPassword(params.getPassword());
        User updatedUser = userService.updateUser(userId, user);
        return new UserDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail());
    }

    @DeleteMapping("users/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }
}
