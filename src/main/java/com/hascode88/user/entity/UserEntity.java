package com.hascode88.user.entity;

import java.time.LocalDateTime;

public class UserEntity {

    private String name;
    private String email;
    private LocalDateTime createAt;

    public UserEntity() {
    }

    private UserEntity(String name, String email, LocalDateTime createAt) {
        this.name = name;
        this.email = email;
        this.createAt = createAt;
    }

    public static UserEntity create(String name, String email, LocalDateTime createAt) throws UserException {
        validateUser(name, email);
        return new UserEntity(name, email, createAt);
    }

    private static void validateUser(String name, String email) throws UserException {
        if (name.isBlank()) {
            throw new UserException("Nome deve ser obrigatório");
        }

        if (email.isBlank()) {
            throw new UserException("Email deve ser obrigatório");
        }
    }

    public void update(String name, String updatedEmail) {
        this.name = name;
        this.email = updatedEmail;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }
}
