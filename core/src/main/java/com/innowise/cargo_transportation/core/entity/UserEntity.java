package com.innowise.cargo_transportation.core.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "\"user\"")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 20)
    private String name;

    @NotNull
    @Size(max = 20)
    private String surname;

    @Size(max = 20)
    private String patronymic;

    @Column(name = "client_id")
    private Long clientId;

    @Past
    private LocalDate bornDate;

    @NotNull
    @Email
    @Size(max = 50)
    private String email;

    @Size(max = 20)
    private String town;

    private String street;

    @Size(max = 20)
    private String house;

    @Size(max = 5)
    private String flat;

    @NotNull
    @Size(min = 5, max = 15)
    private String login;

    @NotNull
    @Size(min = 5, max = 72)
    private String password;

    @Size(max =30)
    @Column(name = "passport_num")
    private String passportNum;

    @Size(max = 50)
    private String issuedBy;

//    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
//    @Enumerated(EnumType.STRING)
//    private Set<Role> UserRole;

}
