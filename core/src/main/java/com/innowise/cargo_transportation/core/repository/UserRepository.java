package com.innowise.cargo_transportation.core.repository;

import com.innowise.cargo_transportation.core.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends CommonRepository<UserEntity, Long> {

    UserEntity findByLogin (String login);
    UserEntity findByRefreshToken (String token);
    UserEntity findByPassportNum(String passportNum);

}
