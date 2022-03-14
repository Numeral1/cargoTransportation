package com.innowise.cargo_transportation.core.dto.request;

import lombok.Data;

@Data
public class ProfilePasswordRequest {
    private String oldPassword;
    private String newPassword;

}
