package com.hascode88.user.usercase;

import com.hascode88.user.dto.UserData;
import com.hascode88.user.model.User;
import com.hascode88.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FindUserService {

    UserRepository repository;

    public FindUserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserData findByid(Long id) {
         Optional<User> user = repository.findById(id);
         return new UserData(user.get().getId(), user.get().getName(), user.get().getEmail(), user.get().getCreateAt());
    }

    public List<UserData> findAll() {
        List<User> user = repository.findAll();
        List<UserData> users = new ArrayList<UserData>();
        repository.findAll().forEach(userResponse ->  {
            users.add(new UserData(userResponse.getId(), userResponse.getName(), userResponse.getEmail(), userResponse.getCreateAt()));
        });
        return users;
    }
}
