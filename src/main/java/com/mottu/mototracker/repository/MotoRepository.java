package com.mottu.mototracker.repository;

import com.mottu.mototracker.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoRepository extends JpaRepository<Moto, Long> {
}
