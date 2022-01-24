package com.innowise.cargo_transportation.core.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserListResponse {

    private List<UserResponse> content;
    private long totalElement;
}
