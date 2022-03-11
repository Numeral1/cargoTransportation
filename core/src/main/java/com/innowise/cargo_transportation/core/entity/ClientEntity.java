package com.innowise.cargo_transportation.core.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@NoArgsConstructor
@Data
@Table(name = "client")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ClientStatus status;
    @Enumerated(EnumType.STRING)
    @Column(name = "approval_status")
    private ClientApprovalStatus clientApprovalStatus;

    public ClientEntity(Long clientId) {
        id = clientId;
    }
}
