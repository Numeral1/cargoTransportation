package com.innowise.cargo_transportation.core.service;

import com.innowise.cargo_transportation.core.dto.request.ClientParamsRequest;
import com.innowise.cargo_transportation.core.dto.request.ClientRequest;
import com.innowise.cargo_transportation.core.dto.response.ClientListResponse;
import com.innowise.cargo_transportation.core.dto.response.ClientResponse;
import com.innowise.cargo_transportation.core.entity.ApprovalStatus;
import com.innowise.cargo_transportation.core.entity.ClientEntity;
import com.innowise.cargo_transportation.core.entity.QClientEntity;
import com.innowise.cargo_transportation.core.repository.ClientRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Data
@Service
public class ClientServiceImpl implements ClientService{
    private final ClientRepository clientRepository;

    @Transactional
    @Override
    public Long createClient(ClientRequest clientRequest) {
        ClientEntity clientEntity = ClientRequest.fromClientRequest(clientRequest);
        clientEntity.setApprovalStatus(ApprovalStatus.DISABLE);
        clientRepository.save(clientEntity);
        return clientEntity.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public ClientResponse findClientById(Long id) {
        ClientEntity entity= clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Client with id:" + id + "does not exists"));
        return new ClientResponse(entity);
    }

    @Transactional
    @Override
    public void updateClient(Long id, ClientRequest clientRequest) {
        ClientEntity client = ClientRequest.fromClientRequest(clientRequest);
        client.setId(id);
        clientRepository.save(client);
    }

    @Transactional(readOnly = true)
    @Override
    public ClientListResponse findList(ClientParamsRequest params) {
        Pageable pageable = PageRequest.of(params.getPageNumber(), params.getPageSize());
        BooleanBuilder booleanBuilder = buildWhere(params);

        Page<ClientEntity> page = clientRepository.findAll(booleanBuilder, pageable);
        return new ClientListResponse(page.map(ClientResponse::new).getContent(), page.getTotalElements());
    }

    private BooleanBuilder buildWhere(ClientParamsRequest params){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (params.getName() != null){
            booleanBuilder.and(QClientEntity.clientEntity.name.like("%" + params.getName() + "%"));
        }
        if (params.getStatus() != null){
            booleanBuilder.and(QClientEntity.clientEntity.status.eq(params.getStatus()));
        }
        return booleanBuilder;
    }

    @Transactional
    @Override
    public void deleteClientsByIdList(List<Long> idList) {
        clientRepository.deleteAllById(idList);
    }

    @Transactional
    @Override
    public void activateClientById(Long id) {
        ClientEntity entity= clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Client with id:" + id + "does not exists"));
        entity.setApprovalStatus(ApprovalStatus.ENABLE);
        clientRepository.save(entity);
    }
}
