package com.innowise.cargo_transportation.web.security.jwt;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String message, Throwable t) {
        super(message, t);
    }

    public JwtAuthenticationException(String message) {
        super(message);
    }
}
