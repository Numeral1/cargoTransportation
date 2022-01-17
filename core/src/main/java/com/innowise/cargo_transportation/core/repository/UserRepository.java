package com.innowise.cargo_transportation.core.repository;

import com.innowise.cargo_transportation.core.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
