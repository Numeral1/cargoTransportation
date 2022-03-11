package com.innowise.cargo_transportation.core.service;

import com.innowise.cargo_transportation.core.dto.request.StorageParamRequest;
import com.innowise.cargo_transportation.core.dto.request.StorageRequest;
import com.innowise.cargo_transportation.core.dto.response.StorageListResponse;
import com.innowise.cargo_transportation.core.dto.response.StorageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StorageService {
    Long createStorage(StorageRequest storageRequest);

    void updateStorage(Long id, StorageRequest storageRequest);

    StorageResponse findStorageById(Long id);

    StorageListResponse findList(StorageParamRequest storageParamRequest, Pageable pageable);

    void deleteStoragesByIdList(List<Long> idList);
}
