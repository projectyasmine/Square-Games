package com.squaregames.demo.controller;

import java.util.UUID;

public class UserDto {
    public final String id;
    public final String email;

    public UserDto(UUID id, String email) {
        this.id = String.valueOf(id);
        this.email = email;
    }

}
