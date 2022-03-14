package com.innowise.cargo_transportation.core.dto.response;

import com.innowise.cargo_transportation.core.entity.UserEntity;
import com.innowise.cargo_transportation.core.entity.UserRole;
import com.innowise.cargo_transportation.core.entity.UserRoleEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class ProfileResponse {
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate bornDate;
    private String town;
    private String street;
    private String house;
    private String flat;
    private String login;
    private String passportNum;
    private String issuedBy;
    private String email;
    private Set<UserRole> userRoles;

    public ProfileResponse(UserEntity user){
        id = user.getId();
        name = user.getName();
        surname = user.getSurname();
        patronymic = user.getPatronymic();
        bornDate = user.getBornDate();
        town = user.getTown();
        street = user.getStreet();
        flat = user.getFlat();
        passportNum = user.getPassportNum();
        issuedBy = user.getIssuedBy();
        login = user.getLogin();
        email = user.getEmail();
        userRoles = user.getUserRole().stream()
                .map(UserRoleEntity::getRole)
                .collect(Collectors.toSet());


    }
}
