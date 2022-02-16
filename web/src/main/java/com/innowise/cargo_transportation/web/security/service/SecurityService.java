package com.innowise.cargo_transportation.web.security.service;

public interface SecurityService {
    String authenticate(String login, String password);
    String renewalRefreshToken (Long id, String refreshToken);
    void logOut (Long id);

}
