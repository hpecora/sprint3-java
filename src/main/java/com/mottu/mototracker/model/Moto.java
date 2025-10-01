package com.mottu.mototracker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String placa;

    @NotBlank
    private String modelo;

    @NotBlank
    private String status;

    @OneToMany(mappedBy = "moto", cascade = CascadeType.ALL)
    private List<Localizacao> localizacoes;
}
