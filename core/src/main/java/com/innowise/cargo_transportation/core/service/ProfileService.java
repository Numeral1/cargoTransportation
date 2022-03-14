package com.innowise.cargo_transportation.core.service;

import com.innowise.cargo_transportation.core.dto.request.ProfilePasswordRequest;
import com.innowise.cargo_transportation.core.dto.request.ProfileRequest;
import com.innowise.cargo_transportation.core.dto.response.ProfileResponse;
import com.innowise.cargo_transportation.core.entity.UserEntity;

public interface ProfileService {
    ProfileResponse getProfile(UserEntity userEntity);

    void updateProfile(ProfileRequest request, UserEntity userEntity);

    void updatePassword(ProfilePasswordRequest password, UserEntity userEntity);
}
