package com.hascode88.user.usercase;

import com.hascode88.user.dto.UserData;
import com.hascode88.user.entity.UserEntity;
import com.hascode88.user.entity.UserException;
import com.hascode88.user.model.User;
import com.hascode88.user.model.UserFactory;
import com.hascode88.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserService {

    UserRepository userRepository;
    FindUserService findUserService;

    public UpdateUserService(UserRepository userRepository, FindUserService findUserService) {
        this.userRepository = userRepository;
        this.findUserService = findUserService;
    }

    public UserData update(String name, String email, Long id) throws UserException {
        UserData userDb = findUserService.findByid(id);

        if (userDb == null) {
            throw new UserException("Usuário não encontrado");
        }

        User user = new UserFactory().create(UserEntity.create(name, email, userDb.getCreateAt()) , id);
        User response = userRepository.save(user);
        return new UserData(response.getId(),response.getName(),response.getEmail(),response.getCreateAt());
    }
}
