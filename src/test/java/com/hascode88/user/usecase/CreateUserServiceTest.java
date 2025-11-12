package com.hascode88.user.usecase;

import com.hascode88.user.dto.UserData;
import com.hascode88.user.entity.UserEntity;
import com.hascode88.user.entity.UserException;
import com.hascode88.user.model.User;
import com.hascode88.user.model.UserFactory;
import com.hascode88.user.repository.UserRepository;
import com.hascode88.user.usercase.CreateUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateUserServiceTest {

    @Mock
    UserRepository repository;

    @InjectMocks
    CreateUserService service;

    @Test
    public void createUser() throws UserException {

        UserEntity user = UserEntity.create("Carlos Silva", "carlos@email.com", LocalDateTime.now());

        User model = new UserFactory().create(user, 1L);
        when(repository.save(any(User.class))).thenReturn(model);

        UserData data =  service.create(user);

        Assertions.assertNotNull(data.getId());
        Assertions.assertEquals(user.getName(), data.getName());
        Assertions.assertEquals(user.getEmail(), data.getEmail());
        Assertions.assertEquals(user.getCreateAt(), data.getCreateAt());
    }
}
