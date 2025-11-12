package com.hascode88.user.usercase;

import com.hascode88.user.dto.PageResponse;
import com.hascode88.user.dto.UserData;
import com.hascode88.user.model.User;
import com.hascode88.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public PageResponse<UserData> findAll(Pageable pageable) {
        Page<User> userPage = repository.findAll(pageable);
        
        List<UserData> userDataList = userPage.getContent().stream()
                .map(user -> new UserData(user.getId(), user.getName(), user.getEmail(), user.getCreateAt()))
                .collect(Collectors.toList());
        
        return new PageResponse<>(
                userDataList,
                userPage.getTotalElements(),
                userPage.getTotalPages(),
                userPage.getNumber(),
                userPage.getSize(),
                userPage.hasNext(),
                userPage.hasPrevious()
        );
    }
}
