package com.innowise.cargo_transportation.core.service;

import com.innowise.cargo_transportation.core.dto.request.UserParamsRequest;
import com.innowise.cargo_transportation.core.dto.request.UserRequest;
import com.innowise.cargo_transportation.core.dto.response.UserListResponse;
import com.innowise.cargo_transportation.core.dto.response.UserResponse;

public interface UserService {
    Long createUser(UserRequest userRequest);
    UserResponse findUserById(Long id);
    void deleteUserById(Long id);
    void updateUser(Long id, UserRequest userRequest);
    UserListResponse findList(UserParamsRequest params);

    UserResponse getUserByLogin(String login);
    UserResponse getUserByRefreshToken(String token);
    void updateUserRefreshToken(Long id, String token);
}
