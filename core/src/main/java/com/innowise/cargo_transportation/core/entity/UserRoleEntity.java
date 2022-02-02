package com.innowise.cargo_transportation.core.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Target;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_role")
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @Target(Role.class)
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public UserRoleEntity(Role role) {
        this.role = role;
    }

}
