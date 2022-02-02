package com.innowise.cargo_transportation.core.dto.request;

import com.innowise.cargo_transportation.core.entity.Role;
import com.innowise.cargo_transportation.core.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    private Long id;

    @Size(max = 20, message = "Name must contain less than 20 characters")
    private String name;

    @NotNull(message = "Surname should not be null")
    @Size(max = 20, message = "Surname must contain less than 20 characters")
    private String surname;

    @Size(max = 20, message = "Patronymic must contain less than 20 characters")
    private String patronymic;

    private Long clientId;

    @Past(message = "Date must be in the past")
    private LocalDate bornDate;

    @NotNull(message = "Email should not be null")
    @Email(message = "Invalid email")
    @Size(max = 50, message = "Email must contain less than 50 characters")
    private String email;

    @Size(max = 20, message = "Town must contain less than 20 characters")
    private String town;

    @Size(max = 20, message = "Street must contain less than 20 characters")
    private String street;

    @Size(max = 5, message = "House must contain less than 5 characters")
    private String house;

    @Size(max = 5, message = "Flat must contain less than 5 characters")
    private String flat;

    @NotNull(message = "Login should not be null")
    @Size(min = 5, max = 15, message = " Login must contain at least 5 and no more than 15 characters")
    private String login;

    @NotNull(message = "Password should not be null")
    @Size(min = 5, max = 72, message = "Password must contain at least 5 and no more than 72 characters")
    private String password;

    @Size(max =30, message = "Passport number must contain less than 30 characters")
    private String passportNum;

    @Size(max = 50, message = "Issued by must contain less than 50 characters")
    private String issuedBy;

    private Set<Role> userRoles;

    public static UserEntity fromUserRequest(UserRequest userRequest, String encodedPassword){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userRequest.getId());
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
        userEntity.setPassword(encodedPassword);
        userEntity.setPassportNum(userRequest.getPassportNum());
        userEntity.setIssuedBy(userRequest.getIssuedBy());

        return userEntity;
    }
}
