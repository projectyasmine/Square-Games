package com.squaregames.demo.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserCreationParams {

    public final @NotNull @Email String email;
    public final @NotEmpty String password;
    public final @NotEmpty String name;

    public UserCreationParams(@NotNull @Email String email, @NotEmpty String password, @NotNull @NotEmpty String name) {
        this.email = email;
        this.name= name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
