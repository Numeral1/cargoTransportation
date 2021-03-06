package com.innowise.cargo_transportation.core.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClientListResponse {
    private List<ClientResponse> content;
    private long totalElements;


}
