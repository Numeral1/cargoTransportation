package com.innowise.cargo_transportation.core.service;

import com.innowise.cargo_transportation.core.dto.request.ProductOwnerParamsRequest;
import com.innowise.cargo_transportation.core.dto.request.ProductOwnerRequest;
import com.innowise.cargo_transportation.core.dto.response.ProductOwnerListResponse;
import com.innowise.cargo_transportation.core.dto.response.ProductOwnerResponse;

import java.util.List;

public interface ProductOwnerService {
    Long createProductOwner(ProductOwnerRequest request);

    void updateProductOwner(Long id, ProductOwnerRequest request);

    void deleteById(List<Long> deleteList);

    ProductOwnerResponse getProductOwnerById(Long id);

    ProductOwnerListResponse findList(ProductOwnerParamsRequest request);
}
