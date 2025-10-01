// src/main/java/com/mottu/mototracker/DTO/MotoPoint.java
package com.mottu.mototracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MotoPoint {
    private Long id;
    private String placa;
    private String modelo;

    /**
     * status normalizado para o front: "ativa" | "manut" | "off"
     */
    private String status;

    // nomes que o JS espera:
    private Double lat;
    private Double lng;

    private LocalDateTime dataHora;

    /** Nome amigável para o popup (Jackson expõe como "nome") */
    public String getNome() {
        String p = placa != null ? placa : "";
        String m = (modelo != null && !modelo.isBlank()) ? " · " + modelo : "";
        return (p + m).trim();
    }
}
