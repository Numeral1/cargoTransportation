package com.innowise.cargo_transportation.core.dto.response;

import com.innowise.cargo_transportation.core.entity.UserRole;
import com.innowise.cargo_transportation.core.entity.UserEntity;
import com.innowise.cargo_transportation.core.entity.UserRoleEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

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
    private Set<UserRole> userRoles;


    public UserResponse(UserEntity userEntity) {
        id = userEntity.getId();
        name = userEntity.getName();
        surname = userEntity.getSurname();
        patronymic = (userEntity.getPatronymic());
        bornDate = userEntity.getBornDate();
        clientId = userEntity.getClient().getId();
        email = userEntity.getEmail();
        town = userEntity.getTown();
        street = userEntity.getStreet();
        house = userEntity.getHouse();
        flat = userEntity.getFlat();
        login = userEntity.getLogin();
        password = userEntity.getPassword();
        passportNum = userEntity.getPassportNum();
        issuedBy = userEntity.getIssuedBy();
        userRoles = userEntity.getUserRole().stream()
                .map(UserRoleEntity::getRole)
                .collect(Collectors.toSet());
    }


}
