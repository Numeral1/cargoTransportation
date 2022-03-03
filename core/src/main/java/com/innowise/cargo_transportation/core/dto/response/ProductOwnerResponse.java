package com.innowise.cargo_transportation.core.dto.response;

import com.innowise.cargo_transportation.core.entity.ProductOwnerEntity;
import lombok.Data;

@Data
public class ProductOwnerResponse {
    private Long id;
    private String name;
    private String address;

    public ProductOwnerResponse(ProductOwnerEntity entity){
        id = entity.getId();
        name = entity.getName();
        address = entity.getAddress();
    }
}
