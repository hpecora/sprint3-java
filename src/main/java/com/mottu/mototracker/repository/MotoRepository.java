// src/main/java/com/mottu/mototracker/repository/MotoRepository.java
package com.mottu.mototracker.repository;

import com.mottu.mototracker.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MotoRepository extends JpaRepository<Moto, Long> {



    // Validação de placa única (case-insensitive)
    boolean existsByPlacaIgnoreCase(String placa);



    // Contadores por status (para dashboard) - case-insensitive
    long countByStatusIgnoreCase(String status);



    // Pátio: todas que NÃO estão com um status (ex.: != "ATIVA"), ignorando maiúsc/minúsc
    List<Moto> findByStatusNotIgnoreCase(String status);



    // Listar por status específico (ex.: "ATIVA"), ignorando maiúsc/minúsc
    List<Moto> findByStatusIgnoreCase(String status);

    long countByStatus(String ativa);
}
