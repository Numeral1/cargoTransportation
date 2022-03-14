package com.innowise.cargo_transportation.web.controller;

import com.innowise.cargo_transportation.core.dto.request.ProfilePasswordRequest;
import com.innowise.cargo_transportation.core.dto.request.ProfileRequest;
import com.innowise.cargo_transportation.core.dto.response.ProfileResponse;
import com.innowise.cargo_transportation.core.service.ProfileService;
import com.innowise.cargo_transportation.web.security.user_security_dto.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/profile")
public class ProfileRestController {
    private final ProfileService service;

    @GetMapping
    public ProfileResponse profile(Authentication auth){
        UserDetailsImpl principal = (UserDetailsImpl) auth.getPrincipal();

        return service.getProfile(principal.getUserEntity());
    }

    @PutMapping
    public void changeProfile(@RequestBody ProfileRequest profile, Authentication auth){
        UserDetailsImpl principal = (UserDetailsImpl) auth.getPrincipal();

        service.updateProfile(profile, principal.getUserEntity());
    }

    @PutMapping("/change-password")
    public void changePassword(@RequestBody ProfilePasswordRequest password, Authentication auth){
        UserDetailsImpl principal = (UserDetailsImpl) auth.getPrincipal();
        service.updatePassword(password, principal.getUserEntity());
    }

}
