package com.innowise.cargo_transportation.core.dto.request;

import com.innowise.cargo_transportation.core.entity.Status;
import lombok.Data;

@Data
public class ClientParamsRequest {
    private int pageNumber;
    private int pageSize = 3;
    private Long id;
    private String name;
    private Status status;
}
