package com.hascode88.user.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class UserEntityTest {

    @Test
    @DisplayName("Deve criar um usuário")
    public void mustCreateAUser() throws UserException {
        String name = "Carlos Silva";
        String email = "carlos@email.com";
        LocalDateTime createAt = LocalDateTime.now();

        UserEntity user = UserEntity.create(name, email, createAt);

        Assertions.assertEquals(name, user.getName());
        Assertions.assertEquals(email, user.getEmail());
        Assertions.assertEquals(createAt, user.getCreateAt());
    }

    @Test
    @DisplayName("Deve Atualizar o usuário")
    public void mustUpdateAUser() throws UserException {
        String name = "Carlos Silva";
        String email = "carlos@email.com";
        LocalDateTime createAt = LocalDateTime.now();

        UserEntity user = UserEntity.create(name, email, createAt);

        String updatedEmail = "csilva@email.com.br";
        user.update(name, updatedEmail);

        Assertions.assertEquals(name, user.getName());
        Assertions.assertEquals(updatedEmail, user.getEmail());
        Assertions.assertEquals(createAt, user.getCreateAt());
    }

    @Test
    @DisplayName("Nome deve ser obrigatório")
    public void nameMustByMandatory() {
        String name = "";
        String email = "carlos@email.com";
        LocalDateTime createAt = LocalDateTime.now();

        String messageError = "Nome deve ser obrigatório";
        UserException exception = Assertions.assertThrows(UserException.class, ()-> {
            UserEntity.create(name, email, createAt);
        });

        Assertions.assertEquals(messageError, exception.getMessage());
    }

    @Test
    @DisplayName("Email deve ser obrigatório")
    public void emailMustByMandatory() {
        String name = "Carlos Silva";
        String email = "";
        LocalDateTime createAt = LocalDateTime.now();

        String messageError = "Email deve ser obrigatório";
        UserException exception = Assertions.assertThrows(UserException.class, ()-> {
            UserEntity.create(name, email, createAt);
        });

        Assertions.assertEquals(messageError, exception.getMessage());
    }
}
