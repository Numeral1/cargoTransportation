package com.innowise.cargo_transportation.core.dto.request;

import lombok.Data;

@Data
public class StorageParamRequest {
    private int pageNumber;
    private int pageSize;
    private String name;
}
