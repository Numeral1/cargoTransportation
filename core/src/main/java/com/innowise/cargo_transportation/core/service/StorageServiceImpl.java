package com.innowise.cargo_transportation.core.service;

import com.innowise.cargo_transportation.core.dto.request.StorageParamRequest;
import com.innowise.cargo_transportation.core.dto.request.StorageRequest;
import com.innowise.cargo_transportation.core.dto.response.StorageListResponse;
import com.innowise.cargo_transportation.core.dto.response.StorageResponse;
import com.innowise.cargo_transportation.core.entity.ClientEntity;
import com.innowise.cargo_transportation.core.entity.QClientEntity;
import com.innowise.cargo_transportation.core.entity.QStorageEntity;
import com.innowise.cargo_transportation.core.entity.StorageEntity;
import com.innowise.cargo_transportation.core.repository.ClientRepository;
import com.innowise.cargo_transportation.core.repository.StorageRepository;
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
public class StorageServiceImpl implements StorageService {
    private final StorageRepository storageRepository;
    private final ClientRepository clientRepository;


    @Override
    public Long createStorage(StorageRequest storageRequest) {
        StorageEntity entity = storageRequest.toEntity();
        storageRepository.save(entity);
        return entity.getId();
    }

    @Transactional
    @Override
    public void updateStorage(Long id, StorageRequest storageRequest) {
        StorageEntity storage = storageRequest.toEntity();
        storage.setId(id);
        storageRepository.save(storage);
    }

    @Transactional(readOnly = true)
    @Override
    public StorageResponse findStorageById(Long id) {
        StorageEntity entity = storageRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Storage with id:" + id + "does not exists"));
        return new StorageResponse(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public StorageListResponse findList(StorageParamRequest storageParams) {
        Pageable pageable = PageRequest.of(storageParams.getPageNumber(), storageParams.getPageSize());
        BooleanBuilder booleanBuilder = buildWhere(storageParams);
        Page<StorageEntity> page = storageRepository.findAll(booleanBuilder, pageable);
        return new StorageListResponse(page.map(StorageResponse::new).getContent(), page.getTotalElements());
    }

    private BooleanBuilder buildWhere(StorageParamRequest params) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (params.getName() != null) {
            booleanBuilder.and(QStorageEntity.storageEntity.name.like("%" + params.getName() + "%"));
        }
        return booleanBuilder;
    }

    @Transactional
    @Override
    public void deleteStoragesByIdList(List<Long> idList) {
        storageRepository.deleteAllById(idList);
    }
}
