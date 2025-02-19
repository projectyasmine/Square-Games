package com.squaregames.demo;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class JpaUserDao implements UserDao {
    @Autowired
    private UserEntityRepository userEntityRepository;

    private User toUser(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.id.hashCode()); // Convert UUID to int
        user.setEmail(userEntity.email);
        user.setPassword(userEntity.password);
        return user;
    }

    private UserEntity toUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.id = UUID.randomUUID(); // Générer un UUID
        userEntity.email = user.getEmail();
        userEntity.password = user.getPassword();
        return userEntity;
    }

    @Override
    @NotNull
    public List<User> findAll() {
        return userEntityRepository.findAll().stream()
                .map(this::toUser)
                .collect(Collectors.toList());
    }

    @Override
    public User findById(@NotNull int userId) {
        Optional<UserEntity> userEntityOptional = userEntityRepository.findById(UUID.nameUUIDFromBytes(Integer.toString(userId).getBytes()));
        return userEntityOptional.map(this::toUser).orElse(null);
    }

    @Override
    @NotNull
    public User upsert(@NotNull User user) {
        UserEntity userEntity = toUserEntity(user);
        userEntityRepository.save(userEntity);
        return user;
    }

    @Override
    public void delete(@NotNull int userId) {
        userEntityRepository.deleteById(UUID.nameUUIDFromBytes(Integer.toString(userId).getBytes()));
    }
}
