package com.innowise.cargo_transportation.web.security.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LogOutRequest {

    @NotNull(message = "User id should be not null")
    Long userId;
}
