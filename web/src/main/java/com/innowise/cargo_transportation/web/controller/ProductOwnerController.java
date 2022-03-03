package com.innowise.cargo_transportation.web.controller;

import com.innowise.cargo_transportation.core.dto.request.ProductOwnerParamsRequest;
import com.innowise.cargo_transportation.core.dto.request.ProductOwnerRequest;
import com.innowise.cargo_transportation.core.dto.response.ProductOwnerListResponse;
import com.innowise.cargo_transportation.core.dto.response.ProductOwnerResponse;
import com.innowise.cargo_transportation.core.service.ProductOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product-owners")
public class ProductOwnerController {
    private final ProductOwnerService service;

    @PostMapping
    public ResponseEntity<URI> create(@RequestBody @Valid ProductOwnerRequest request){
        Long id = service.createProductOwner(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(location)
                .body(location);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") long id, @RequestBody ProductOwnerRequest request){
        service.updateProductOwner(id, request);
    }

    @GetMapping("/{id}")
    public ProductOwnerResponse getById(@PathVariable("id") long id){
        return service.getProductOwnerById(id);
    }

    @GetMapping
    public ProductOwnerListResponse getByFilter(ProductOwnerParamsRequest request){
        return service.findList(request);
    }

    @DeleteMapping
    public void delete(@RequestBody List<Long> deleteList){
        service.deleteById(deleteList);
    }


}
