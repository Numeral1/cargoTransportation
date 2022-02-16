package com.innowise.cargo_transportation.core.dto.request;

import com.innowise.cargo_transportation.core.entity.ClientEntity;
import com.innowise.cargo_transportation.core.entity.ClientStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ClientRequest {
    private Long id;

    @NotNull(message = "Client name should not be null")
    @Size(max = 30, message = "Client name must contain less than 30 characters")
    private String name;
    @NotNull(message = "Client clientStatus should not be null")
    private ClientStatus status;
    @NotNull(message = "AdminInfo should not be null")
    private UserRequest adminInfo;

    public static ClientEntity fromClientRequest(ClientRequest request) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setName(request.getName());
        clientEntity.setStatus(request.getStatus());

        return clientEntity;
    }


}
