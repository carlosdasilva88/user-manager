package com.hascode88.user.usecase;

import com.hascode88.user.entity.UserException;
import com.hascode88.user.repository.UserRepository;
import com.hascode88.user.usercase.DeleteUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteUserServiceTest {

    @Mock
    UserRepository repository;

    @InjectMocks
    DeleteUserService service;

    @Test
    void delete_deveDeletarUsuarioQuandoExiste() throws UserException {

        Long id = 1L;
        when(repository.existsById(id)).thenReturn(true);
        service.delete(id);

        verify(repository).existsById(id);
        verify(repository).deleteById(id);

    }
}
