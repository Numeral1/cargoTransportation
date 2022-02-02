package com.innowise.cargo_transportation.web.controller;

import com.innowise.cargo_transportation.web.security.service.SecurityService;
import com.innowise.cargo_transportation.web.security.request.AuthenticationRequest;
import com.innowise.cargo_transportation.web.security.request.LogOutRequest;
import com.innowise.cargo_transportation.web.security.request.RenewalTokenRequest;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Data
public class SecurityController {

    private final SecurityService securityService;

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public String signIn(@RequestBody @Valid AuthenticationRequest authRequest) {
        return securityService.authenticate(authRequest.getLogin(), authRequest.getPassword());
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public String refresh(@RequestBody @Valid RenewalTokenRequest request) {
        return securityService.renewalRefreshToken(request.getUserId(), request.getToken());
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@RequestBody @Valid LogOutRequest logOutRequest) {
        securityService.logOut(logOutRequest.getUserId());
    }
}
