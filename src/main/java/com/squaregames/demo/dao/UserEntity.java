package com.squaregames.demo.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class UserEntity {
    @Id
    public String id;

    @NotNull
    public String name;

    @NotNull
    @Email
    public String email;

    @NotNull
    public String password;

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = String.valueOf(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
