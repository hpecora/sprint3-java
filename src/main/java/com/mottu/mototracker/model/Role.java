package com.mottu.mototracker.model;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "ROLES")
@Getter @Setter @NoArgsConstructor
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name; // ex: ROLE_ADMIN, ROLE_USER
}
