package com.hascode88.user.usercase;

import com.hascode88.user.dto.UserData;
import com.hascode88.user.entity.UserEntity;
import com.hascode88.user.model.User;
import com.hascode88.user.model.UserFactory;
import com.hascode88.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {

    UserRepository userRepository;

    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserData create(UserEntity user) {
        User model = userRepository.save(new UserFactory().create(user));
        return new UserData(model.getId(), model.getName(), model.getEmail(), model.getCreateAt());
    }
}
