package com.innowise.cargo_transportation.web.security.service;

import com.innowise.cargo_transportation.core.dto.response.UserResponse;
import com.innowise.cargo_transportation.core.service.UserService;
import com.innowise.cargo_transportation.web.security.jwt.JwtAuthenticationException;
import com.innowise.cargo_transportation.web.security.jwt.JwtProvider;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Data
@Service
public class SecurityServiceImpl implements SecurityService{
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final BCryptPasswordEncoder encoder;

    @Override
    @Transactional
    public String authenticate(String login, String password) {

        try {
            UserResponse user = userService.getUserByLogin(login);
            if (user == null) {
                throw new JwtAuthenticationException("Invalid username or password");
            }

            if (!encoder.matches(password, user.getPassword())) {
                throw new JwtAuthenticationException("Invalid username or password");
            }

            String accessToken = jwtProvider.createToken(login, user.getId(), user.getUserRoles());
            String refreshToken = jwtProvider.createRefreshToken(login, user.getId());
            userService.updateUserRefreshToken(user.getId(), refreshToken);
            return accessToken + " " + refreshToken;
        } catch (AuthenticationException e) {
            throw new JwtAuthenticationException("Invalid username or password");
        }
    }

    @Override
    @Transactional
    public String renewalRefreshToken(Long id, String refreshToken) {
        if (!jwtProvider.validateToken(refreshToken)) {
            return null;
        }
        if (!Objects.equals(jwtProvider.getUserId(refreshToken), id)) {
            throw new JwtAuthenticationException("Provided Id does not match token owner's id");
        }
        UserResponse user = userService.getUserByRefreshToken(refreshToken);
        if (user == null) {
            throw new JwtAuthenticationException("Provided refresh token is not valid");
        }
        refreshToken = jwtProvider.createRefreshToken(user.getLogin(), user.getId());
        userService.updateUserRefreshToken(user.getId(), refreshToken);
        String accessToken = jwtProvider.createToken(user.getLogin(), user.getId(), user.getUserRoles());
        return accessToken + " " + refreshToken;
    }

    @Override
    public void logOut(Long id) {
        userService.updateUserRefreshToken(id, null);
    }
}
