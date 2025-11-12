package com.hascode88.user.usercase;

import com.hascode88.user.entity.UserException;
import com.hascode88.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserService {

    UserRepository repository;

    public DeleteUserService(UserRepository repository) {
        this.repository = repository;
    }

    public void delete(Long id) throws UserException {
        if (!repository.existsById(id)) {
            throw new UserException("Usuário não encontrado");
        }
        repository.deleteById(id);
    }


}
