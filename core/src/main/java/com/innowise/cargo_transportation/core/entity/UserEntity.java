package com.innowise.cargo_transportation.core.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "\"user\"")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "passport_num")
    private String passportNum;

    @Column(name = "refresh_token")
    private String refreshToken;

    private String issuedBy;

//    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserRoleEntity> userRole;

}
