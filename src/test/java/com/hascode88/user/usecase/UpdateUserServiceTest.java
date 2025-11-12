package com.hascode88.user.usecase;

import com.hascode88.user.dto.UserData;
import com.hascode88.user.entity.UserEntity;
import com.hascode88.user.entity.UserException;
import com.hascode88.user.model.User;
import com.hascode88.user.model.UserFactory;
import com.hascode88.user.repository.UserRepository;
import com.hascode88.user.usercase.FindUserService;
import com.hascode88.user.usercase.UpdateUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateUserServiceTest {

    @Mock
    UserRepository repository;

    @Mock
    FindUserService findUserService;

    @InjectMocks
    UpdateUserService service;

    @Test
    public void updateUser() throws UserException {

        Long id = 1L;
        LocalDateTime createdAt = LocalDateTime.now();

        UserData userDb = new UserData(id, "Carlos", "carlos@email.com", createdAt);
        when(findUserService.findByid(id)).thenReturn(userDb);

        String expectedName = "Henrique";
        String expectedEmail = "henrique@email.com";

        UserEntity user = UserEntity.create(expectedName, expectedEmail, createdAt);
        User model = new UserFactory().create(user, 1L);
        when(repository.save(any(User.class))).thenReturn(model);

        UserData data = service.update(expectedName, expectedEmail, id);

        Assertions.assertEquals(id, data.getId());
        Assertions.assertEquals(expectedName, data.getName());
        Assertions.assertEquals(expectedEmail, data.getEmail());

        verify(findUserService).findByid(id);
        verify(repository).save(any(User.class));
    }
}
