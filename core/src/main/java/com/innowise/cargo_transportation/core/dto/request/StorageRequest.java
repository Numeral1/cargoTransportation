package com.innowise.cargo_transportation.core.dto.request;

import com.innowise.cargo_transportation.core.entity.ClientEntity;
import com.innowise.cargo_transportation.core.entity.StorageEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class StorageRequest {
    private Long id;
    @NotNull(message = "Storage should not be null")
    @Size(max = 20, message = "Storage name must contain less than 20 characters")
    private String name;
    @NotNull(message = "Address should not be null")
    @Size(max = 40, message = "Address must contain less than 40 characters")
    private String address;
    @NotNull(message = "Client id should not be null")
    private Long clientId;


    public StorageEntity toEntity() {
        StorageEntity storageEntity = new StorageEntity();
        storageEntity.setName(name);
        storageEntity.setAddress(address);
        return storageEntity;
    }
}
