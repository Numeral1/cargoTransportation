package com.innowise.cargo_transportation.web.security.service;

import com.innowise.cargo_transportation.core.dto.response.UserResponse;

public interface SecurityService {
    String authenticate(String login, String password);
    String renewalRefreshToken (Long id, String refreshToken);
    void logOut (Long id);

}
