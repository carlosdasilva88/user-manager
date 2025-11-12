package com.hascode88.user.usecase;

import com.hascode88.user.dto.UserData;
import com.hascode88.user.entity.UserEntity;
import com.hascode88.user.entity.UserException;
import com.hascode88.user.model.User;
import com.hascode88.user.model.UserFactory;
import com.hascode88.user.repository.UserRepository;
import com.hascode88.user.usercase.FindUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindUserServiceTest {

    @Mock
    UserRepository repository;

    @InjectMocks
    FindUserService service;

    @Test
    public void findUserById() throws UserException {

        UserEntity user = UserEntity.create("Carlos Silva", "carlos@email.com", LocalDateTime.now());
        User model = new UserFactory().create(user, 1L);

        when(repository.findById(1L)).thenReturn(Optional.of(model));
        UserData data =  service.findByid(1L);

        Assertions.assertNotNull(data.getId());
        Assertions.assertEquals(user.getName(), data.getName());
        Assertions.assertEquals(user.getEmail(), data.getEmail());
        Assertions.assertEquals(user.getCreateAt(), data.getCreateAt());
    }

    @Test
    public void findAll() throws UserException {

        User user1 = new UserFactory().create(UserEntity.create("Carlos Silva", "carlos@email.com", LocalDateTime.now()), 1L);
        User user2 = new UserFactory().create(UserEntity.create("Pedro Pedrocal", "PedroPedroca@email.com", LocalDateTime.now()), 1L);

        when(repository.findAll()).thenReturn(List.of(user1, user2));
        List<UserData> result =  service.findAll();

        UserData data1 = result.getFirst();
        Assertions.assertNotNull(data1.getId());
        Assertions.assertEquals(user1.getName(), data1.getName());
        Assertions.assertEquals(user1.getEmail(), data1.getEmail());
        Assertions.assertEquals(user1.getCreateAt(), data1.getCreateAt());

        UserData data2 = result.get(1);
        Assertions.assertNotNull(data2.getId());
        Assertions.assertEquals(user2.getName(), data2.getName());
        Assertions.assertEquals(user2.getEmail(), data2.getEmail());
        Assertions.assertEquals(user2.getCreateAt(), data2.getCreateAt());
    }
}
