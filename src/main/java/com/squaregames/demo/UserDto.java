package com.squaregames.demo;

public class UserDto {
    public final String id;
    public final String email;

    public UserDto(int id, String email) {
        this.id = String.valueOf(id);
        this.email = email;
    }

}
