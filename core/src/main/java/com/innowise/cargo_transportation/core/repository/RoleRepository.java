package com.innowise.cargo_transportation.core.repository;

import com.innowise.cargo_transportation.core.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<UserRoleEntity, Long> {
}
