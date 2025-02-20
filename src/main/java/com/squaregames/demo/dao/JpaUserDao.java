package com.squaregames.demo.dao;

import com.squaregames.demo.service.User;
import jakarta.transaction.Transactional;
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
        user.setId(UUID.fromString(userEntity.id));
        user.setName(userEntity.name);
        user.setEmail(userEntity.email);
        user.setPassword(userEntity.password);
        return user;
    }

    private UserEntity toUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId() != null ? user.getId() : UUID.randomUUID());
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
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
    public User findById(@NotNull UUID userId) {
        Optional<UserEntity> userEntityOptional = userEntityRepository.findById(userId.toString());
        return userEntityOptional.map(this::toUser).orElse(null);
    }

    @Override
    @Transactional
    @NotNull
    public User upsert(@NotNull User user) {
        UserEntity userEntity = toUserEntity(user);
        userEntityRepository.save(userEntity);
        return toUser(userEntity);
    }

    @Override
    public void delete(@NotNull UUID userId) {
        userEntityRepository.deleteById(userId.toString());
    }
}
