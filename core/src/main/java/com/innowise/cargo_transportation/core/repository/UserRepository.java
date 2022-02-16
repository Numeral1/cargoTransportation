package com.innowise.cargo_transportation.core.repository;

import com.innowise.cargo_transportation.core.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CommonRepository<UserEntity, Long> {

    UserEntity findByLogin (String login);
    UserEntity findByRefreshToken (String token);
    UserEntity findByPassportNum(String passportNum);

}
