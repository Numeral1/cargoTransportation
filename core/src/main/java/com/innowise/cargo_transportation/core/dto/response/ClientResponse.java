package com.innowise.cargo_transportation.core.dto.response;

import com.innowise.cargo_transportation.core.entity.ClientEntity;
import com.innowise.cargo_transportation.core.entity.Status;
import lombok.Data;

@Data
public class ClientResponse {
    private Long id;
    private String name;
    private Status status;

    public ClientResponse(ClientEntity clientEntity){
        id = clientEntity.getId();
        name = clientEntity.getName();
        status = clientEntity.getStatus();
    }

}
