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

    public UserResponse(UserEntity userEntity) {
        id = userEntity.getId();
        name = userEntity.getName();
        surname = userEntity.getSurname();
        patronymic = (userEntity.getPatronymic());
        bornDate = userEntity.getBornDate();
        clientId = userEntity.getClientId();
        email = userEntity.getEmail();
        town = userEntity.getTown();
        street = userEntity.getStreet();
        house = userEntity.getHouse();
        flat = userEntity.getFlat();
        login = userEntity.getLogin();
        password = userEntity.getPassword();
        passportNum = userEntity.getPassportNum();
        issuedBy = userEntity.getIssuedBy();
    }


}
