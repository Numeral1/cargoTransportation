package com.innowise.cargo_transportation.web.security.jwt;

import com.innowise.cargo_transportation.core.entity.UserEntity;
import com.innowise.cargo_transportation.core.repository.UserRepository;
import com.innowise.cargo_transportation.web.security.user_security_dto.UserDetailsImpl;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Data
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByLogin(username);
        if (user == null) {
            throw new EntityNotFoundException("User with login: " + username + " not found");
        }
        return new UserDetailsImpl(user);
    }
}
