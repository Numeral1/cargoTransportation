package com.innowise.cargo_transportation.core.dto.response;

import com.innowise.cargo_transportation.core.entity.StorageEntity;
import lombok.Data;

@Data
public class StorageResponse {
    private Long id;
    private String name;
    private String address;

    public StorageResponse(StorageEntity storage){
        id = storage.getId();
        name = storage.getName();
        address = storage.getAddress();
    }
}
