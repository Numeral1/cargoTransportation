package com.innowise.cargo_transportation.core.dto.response;

import com.innowise.cargo_transportation.core.entity.UserEntity;
import lombok.Data;

import java.time.LocalDate;
@Data
public class UserResponse {
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private Long clientId;
    private LocalDate bornDate;
    private String email;
    private String town;
    private String street;
    private String house;
    private String flat;
    private String login;
    private String password;
    private String passportNum;
    private String issuedBy;

    public UserResponse toUserResponse(UserEntity userEntity){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userEntity.getId());
        userResponse.setName(userEntity.getName());
        userResponse.setSurname(userEntity.getSurname());
        userResponse.setClientId(userEntity.getClientId());
        userResponse.setBornDate(userEntity.getBornDate());
        userResponse.setEmail(userEntity.getEmail());
        userResponse.setTown(userEntity.getTown());
        userResponse.setStreet(userEntity.getStreet());
        userResponse.setHouse(userEntity.getHouse());
        userResponse.setFlat(userEntity.getFlat());
        userResponse.setLogin(userEntity.getLogin());
        userResponse.setPassword(userEntity.getPassword());
        userResponse.setPassportNum(userEntity.getPassportNum());
        userResponse.setIssuedBy(userEntity.getIssuedBy());
        return userResponse;
    }
}
