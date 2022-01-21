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


//    public UserResponse toUserResponse(UserEntity userEntity){
//        UserResponse userResponse = new UserResponse();
//        userResponse.setId(userEntity.getId());
//        userResponse.setName(userEntity.getName());
//        userResponse.setSurname(userEntity.getSurname());
//        userResponse.setPatronymic(userEntity.getPatronymic());
//        userResponse.setBornDate(userEntity.getBornDate());
//        userResponse.setClientId(userEntity.getClientId());
//        userResponse.setEmail(userEntity.getEmail());
//        userResponse.setTown(userEntity.getTown());
//        userResponse.setStreet(userEntity.getStreet());
//        userResponse.setHouse(userEntity.getHouse());
//        userResponse.setFlat(userEntity.getFlat());
//        userResponse.setLogin(userEntity.getLogin());
//        userResponse.setPassword(userEntity.getPassword());
//        userResponse.setPassportNum(userEntity.getPassportNum());
//        userResponse.setIssuedBy(userEntity.getIssuedBy());
//        return userResponse;
//    }
}
