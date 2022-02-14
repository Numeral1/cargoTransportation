package com.innowise.cargo_transportation.core.repository;

import com.innowise.cargo_transportation.core.entity.ClientEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CommonRepository<ClientEntity, Long> {
}
