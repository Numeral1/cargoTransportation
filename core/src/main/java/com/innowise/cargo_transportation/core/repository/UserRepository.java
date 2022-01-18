package com.innowise.cargo_transportation.core.repository;

import com.innowise.cargo_transportation.core.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findById(Long id);

}
