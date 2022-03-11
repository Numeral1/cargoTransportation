package com.innowise.cargo_transportation.core.service;

import com.innowise.cargo_transportation.core.dto.request.ClientParamsRequest;
import com.innowise.cargo_transportation.core.dto.request.ClientRequest;
import com.innowise.cargo_transportation.core.dto.response.ClientListResponse;
import com.innowise.cargo_transportation.core.dto.response.ClientResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {
    Long createClient(ClientRequest clientRequest);

     ClientResponse findClientById(Long id);

    void updateClient(Long id, ClientRequest clientRequest);

    ClientListResponse findList(ClientParamsRequest params, Pageable pageable);

    void deleteClientsByIdList(List<Long> idList);

    void activateClientById(Long id);
}
