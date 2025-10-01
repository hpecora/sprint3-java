package com.mottu.mototracker.controller;

import com.mottu.mototracker.DTO.MotoPoint;
import com.mottu.mototracker.service.MotoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MotoApiController {

    private final MotoService motoService;

    public MotoApiController(MotoService motoService) {
        this.motoService = motoService;
    }

    // Mude o path para algo exclusivo (ex.: /api/motos/mapa)
    @GetMapping("/api/motos/mapa")
    public List<MotoPoint> listarPontosDoMapa() {
        return motoService.listarParaMapa();
    }
}
