package com.innowise.cargo_transportation.web.security.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AuthenticationRequest {

    @Size(min = 5, max = 15, message = "Login should contain from 5 to 15 chars")
    @NotNull(message = "Login should be not null")
    String login;

    @Size(min = 5, max = 25, message = "Password should contain from 5 to 15 chars")
    @NotNull(message = "Password should be not null")
    String password;
}
