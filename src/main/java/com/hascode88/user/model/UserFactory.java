package com.hascode88.user.model;

import com.hascode88.user.entity.UserEntity;

public class UserFactory {

    public User create(UserEntity user) {
        return new User(user.getName(), user.getEmail(), user.getCreateAt());
    }

    public User create(UserEntity user, Long id) {
        return new User(id, user.getName(), user.getEmail(), user.getCreateAt());
    }
}
