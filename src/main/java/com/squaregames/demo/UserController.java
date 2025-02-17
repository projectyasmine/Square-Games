package com.squaregames.demo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
