package com.innowise.cargo_transportation.core.dto.request;

import com.innowise.cargo_transportation.core.entity.ProductOwnerEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ProductOwnerRequest {
    private Long id;
    @NotNull(message = "Product owner name should not be null")
    @Size(max = 30, message = "Product owner name must contain less than 30 characters")
    private String name;
    @NotNull(message = "Product owner address should not be null")
    @Size(max = 40, message = "Product owner address must contain less than 30 characters")
    private String address;
    private Long clientId;

    public ProductOwnerEntity toEntity(){
        ProductOwnerEntity productOwner = new ProductOwnerEntity();
        productOwner.setName(name);
        productOwner.setAddress(address);
        productOwner.setClientId(clientId);
        return productOwner;
    }
}
