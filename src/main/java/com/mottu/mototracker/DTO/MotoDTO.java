package com.mottu.mototracker.DTO;

import com.mottu.mototracker.model.Localizacao;
import com.mottu.mototracker.model.Moto;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class MotoDTO {
    private Long id;
    private String placa;
    private String modelo;   // <— ADICIONADO
    private String status;

    // info opcional p/ lista/detalhe
    private Double latitude;
    private Double longitude;
    private LocalDateTime dataHora;

    public static MotoDTO from(Moto moto, Localizacao ultima) {
        return MotoDTO.builder()
                .id(moto.getId())
                .placa(moto.getPlaca())
                .modelo(moto.getModelo())     // <— vai bater com ${m.modelo}
                .status(moto.getStatus())
                .latitude(ultima != null ? ultima.getLatitude() : null)
                .longitude(ultima != null ? ultima.getLongitude() : null)
                .dataHora(ultima != null ? ultima.getDataHora() : null)
                .build();
    }
}
