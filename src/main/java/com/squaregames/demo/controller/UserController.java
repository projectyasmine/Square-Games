package com.squaregames.demo.controller;

import com.squaregames.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public UserDto createUser(@Valid @RequestBody UserCreationParams params) {
        return userService.createUser(params);
    }

    @GetMapping("/users/{userId}")
    public UserDto getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }

}
