package com.mottu.mototracker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitude;
    private Double longitude;
    private LocalDateTime dataHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moto_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","localizacoes"})
    private Moto moto;
}
