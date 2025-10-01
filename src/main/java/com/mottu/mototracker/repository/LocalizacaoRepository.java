// src/main/java/com/mottu/mototracker/repository/LocalizacaoRepository.java
package com.mottu.mototracker.repository;

import com.mottu.mototracker.model.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {



    // Última localização de cada moto (subquery na própria tabela)
    @Query("""
           SELECT l FROM Localizacao l
           WHERE l.dataHora = (
               SELECT MAX(l2.dataHora)
               FROM Localizacao l2
               WHERE l2.moto.id = l.moto.id
           )
           """)
    List<Localizacao> findUltimasPorMoto();



    // Histórico completo de uma moto, mais recente primeiro
    List<Localizacao> findByMotoIdOrderByDataHoraDesc(Long motoId);



    // Apenas a última localização de uma moto
    Optional<Localizacao> findTopByMotoIdOrderByDataHoraDesc(Long motoId);
}
