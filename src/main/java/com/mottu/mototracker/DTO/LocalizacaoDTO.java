// src/main/java/com/mottu/mototracker/DTO/LocalizacaoDTO.java
package com.mottu.mototracker.DTO;

import com.mottu.mototracker.model.Localizacao;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LocalizacaoDTO {
    private Long id;
    private Long motoId;
    private Double latitude;
    private Double longitude;
    private LocalDateTime dataHora;

    public static LocalizacaoDTO from(Localizacao l) {
        LocalizacaoDTO dto = new LocalizacaoDTO();
        dto.setId(l.getId());
        dto.setMotoId(l.getMoto().getId());
        dto.setLatitude(l.getLatitude());
        dto.setLongitude(l.getLongitude());
        dto.setDataHora(l.getDataHora());
        return dto;
    }
}
