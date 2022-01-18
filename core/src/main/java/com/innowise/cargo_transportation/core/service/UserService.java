package com.innowise.cargo_transportation.core.service;

import com.innowise.cargo_transportation.core.dto.request.UserRequest;
import com.innowise.cargo_transportation.core.dto.response.UserResponse;
import com.innowise.cargo_transportation.core.entity.UserEntity;
import com.innowise.cargo_transportation.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long createUser(UserRequest userRequest){
        UserEntity entity = UserRequest.fromUserRequest(userRequest);
        userRepository.save(entity);
        return null;
    }

    public UserResponse findUserById(Long id){
    UserResponse userResponse = new UserResponse();
        Optional<UserEntity> byId = userRepository.findById(id);
        UserEntity user = new UserEntity();
        if (byId.isPresent()) {
            user = byId.get();
        }
        return userResponse.toUserResponse(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public void updateUser(Long id, UserRequest userRequest){
        UserEntity entity = UserRequest.fromUserRequest(userRequest);
        entity.setId(id);
        userRepository.save(entity);
    }
}
