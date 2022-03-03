package com.innowise.cargo_transportation.core.dto.request;

import lombok.Data;

@Data
public class ProductOwnerParamsRequest {
    private int pageNumber;
    private int pageSize;
    private String name;
}
