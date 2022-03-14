package com.innowise.cargo_transportation.core.service;

import com.innowise.cargo_transportation.core.dto.request.ProfilePasswordRequest;
import com.innowise.cargo_transportation.core.dto.request.ProfileRequest;
import com.innowise.cargo_transportation.core.dto.response.ProfileResponse;
import com.innowise.cargo_transportation.core.entity.UserEntity;
import com.innowise.cargo_transportation.core.exception.PasswordDoesntMatchException;
import com.innowise.cargo_transportation.core.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Data
@RequiredArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService{
    private final UserRepository repository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ProfileResponse getProfile(UserEntity userEntity) {
        return new ProfileResponse(userEntity);
    }

    @Override
    public void updateProfile(ProfileRequest profile, UserEntity userEntity) {
        profile.updateEntity(userEntity);
        repository.save(userEntity);
    }

    @Override
    public void updatePassword(ProfilePasswordRequest request, UserEntity userEntity) {
        String newPassword = request.getNewPassword();
        String oldPassword = request.getOldPassword();
        String encodedNewPassword = bCryptPasswordEncoder.encode(newPassword);
        if (!bCryptPasswordEncoder.matches(oldPassword, userEntity.getPassword())){
            throw new PasswordDoesntMatchException("The password doesn't match");
        }
        userEntity.setPassword(encodedNewPassword);
        repository.save(userEntity);
    }
}
