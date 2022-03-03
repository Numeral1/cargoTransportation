package com.innowise.cargo_transportation.core.service;

import com.innowise.cargo_transportation.core.dto.request.ProductOwnerParamsRequest;
import com.innowise.cargo_transportation.core.dto.request.ProductOwnerRequest;
import com.innowise.cargo_transportation.core.dto.response.ProductOwnerListResponse;
import com.innowise.cargo_transportation.core.dto.response.ProductOwnerResponse;
import com.innowise.cargo_transportation.core.entity.ProductOwnerEntity;
import com.innowise.cargo_transportation.core.entity.QProductOwnerEntity;
import com.innowise.cargo_transportation.core.repository.ProductOwnerRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Data
@Service
public class ProductOwnerServiceImpl implements ProductOwnerService{
    private final ProductOwnerRepository repository;

    @Override
    public Long createProductOwner(ProductOwnerRequest request) {
        ProductOwnerEntity entity = request.toEntity();
        repository.save(entity);
        return entity.getId();
    }

    @Override
    public void updateProductOwner(Long id, ProductOwnerRequest request) {
        ProductOwnerEntity entity = request.toEntity();
        entity.setId(id);
        repository.save(entity);
    }

    @Override
    public ProductOwnerResponse getProductOwnerById(Long id) {
        ProductOwnerEntity entity = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Storage with id:" + id + "does not exists"));
        return new ProductOwnerResponse(entity);
    }

    @Override
    public ProductOwnerListResponse findList(ProductOwnerParamsRequest request) {
        Pageable pageable = PageRequest.of(request.getPageSize(), request.getPageNumber());
        BooleanBuilder booleanBuilder = buildWhere(request);
        Page<ProductOwnerEntity> page = repository.findAll(booleanBuilder, pageable);
        return new ProductOwnerListResponse(page.map(ProductOwnerResponse::new).getContent(), page.getTotalElements());
    }

    private BooleanBuilder buildWhere(ProductOwnerParamsRequest paramsRequest){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(paramsRequest.getName() != null){
            booleanBuilder.and(QProductOwnerEntity.productOwnerEntity.name.like("%" + paramsRequest.getName() + "%"));
        }
        return booleanBuilder;
    }

    @Override
    public void deleteById(List<Long> deleteList) {
        repository.deleteAllById(deleteList);
    }
}
