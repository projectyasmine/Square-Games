package com.squaregames.demo;

public class UserDto {
    public final String id;
    public final String email;

    public UserDto(int updatedUserId, String id, String email) {
        this.id = id;
        this.email = email;
    }

}
