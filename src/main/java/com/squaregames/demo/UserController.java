package com.squaregames.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public UserDto createUser(@RequestBody UserCreationParams params) {
// TODO - actually create a new user
        return null;
    }

    @GetMapping("/users/{userId}")
    public UserDto getUser(@PathVariable String userId) {
// TODO - actually get and return user with id 'userId'
        return null;
    }
}
