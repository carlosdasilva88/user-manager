package com.hascode88.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createAt;

    // Construtor sem argumentos necessário para JPA
    public User() {
    }

    public User(String name, String email, LocalDateTime createAt) {
        this.name = name;
        this.email = email;
        this.createAt = createAt;
    }

    public User(Long id, String name, String email, LocalDateTime createAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createAt = createAt;
    }

    public Long getId() {
        return id;
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
