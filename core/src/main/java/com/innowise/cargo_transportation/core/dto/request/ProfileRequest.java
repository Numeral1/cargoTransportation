package com.innowise.cargo_transportation.core.dto.request;

import com.innowise.cargo_transportation.core.entity.UserEntity;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileRequest {
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate bornDate;
    private String town;
    private String street;
    private String house;
    private String flat;

    public void updateEntity(UserEntity user){
        user.setName(name);
        user.setSurname(surname);
        user.setPatronymic(patronymic);
        user.setBornDate(bornDate);
        user.setTown(town);
        user.setStreet(street);
        user.setHouse(house);
        user.setFlat(flat);
    }
}
