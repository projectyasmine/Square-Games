package com.squaregames.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class UserEntity {
    @Id
    public UUID id;

    @NotNull
    @Email
    public String email;

    @NotNull
    public String password;
}
