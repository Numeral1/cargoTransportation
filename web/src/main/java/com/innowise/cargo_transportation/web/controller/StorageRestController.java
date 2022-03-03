package com.innowise.cargo_transportation.web.controller;

import com.innowise.cargo_transportation.core.dto.request.StorageParamRequest;
import com.innowise.cargo_transportation.core.dto.request.StorageRequest;
import com.innowise.cargo_transportation.core.dto.response.StorageListResponse;
import com.innowise.cargo_transportation.core.dto.response.StorageResponse;
import com.innowise.cargo_transportation.core.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/storages")
public class StorageRestController {
    private final StorageService storageService;

    @PostMapping
    public ResponseEntity<URI> create(@RequestBody @Valid StorageRequest storageRequest){
        Long storageId = storageService.createStorage(storageRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(storageId).toUri();
        return ResponseEntity.created(location)
                .body(location);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody StorageRequest storageRequest){
        storageService.updateStorage(id, storageRequest);
    }

    @GetMapping("/{id}")
    public StorageResponse findById(@PathVariable("id") Long id){
        return storageService.findStorageById(id);
    }

    @GetMapping
    public StorageListResponse findByFilter(StorageParamRequest storageParamRequest){
        return storageService.findList(storageParamRequest);
    }

    @DeleteMapping
    public void deleteById(@RequestBody List<Long> idList){
        storageService.deleteStoragesByIdList(idList);
    }
}
