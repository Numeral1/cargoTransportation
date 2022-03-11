package com.innowise.cargo_transportation.core.dto.request;

import com.innowise.cargo_transportation.core.entity.ClientStatus;
import lombok.Data;

@Data
public class ClientParamsRequest {
    private Long id;
    private String name;
    private ClientStatus clientStatus;
}
