package com.innowise.cargo_transportation.core.dto.request;

import com.innowise.cargo_transportation.core.entity.UserEntity;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class UserRequest {

    private Long id;

//    @Size(max = 20)
    private String name;

//    @NotNull
//    @Size(max = 20)
    private String surname;

//    @Size(max = 20)
    private String patronymic;

    private Long clientId;

//    @Past
    private LocalDate bornDate;

//    @NotNull
//    @Email
//    @Size(max = 50)
    private String email;

//    @Size(max = 20)
    private String town;

    private String street;

//    @Size(max = 20)
    private String house;

//    @Size(max = 5)
    private String flat;

//    @NotNull
//    @Size(min = 5, max = 15)
    private String login;

//    @NotNull
//    @Size(min = 5, max = 72)
    private String password;

//    @Size(max =30)
    private String passportNum;

//    @Size(max = 50)
    private String issuedBy;

    public static UserEntity fromUserRequest(UserRequest userRequest){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userRequest.getName());
        userEntity.setSurname(userRequest.getSurname());
        userEntity.setPatronymic(userRequest.getPatronymic());
        userEntity.setClientId(userRequest.getClientId());
        userEntity.setBornDate(userRequest.getBornDate());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setTown(userRequest.getTown());
        userEntity.setStreet(userRequest.getStreet());
        userEntity.setHouse(userRequest.getHouse());
        userEntity.setFlat(userRequest.getFlat());
        userEntity.setLogin(userRequest.getLogin());
        userEntity.setPassword(userRequest.getPassword());
        userEntity.setPassportNum(userRequest.getPassportNum());
        userEntity.setIssuedBy(userRequest.getIssuedBy());
        return userEntity;
    }
}
