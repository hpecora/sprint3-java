package com.mottu.mototracker.controller;

import com.mottu.mototracker.DTO.MotoPoint;
import com.mottu.mototracker.service.MotoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MapaController {

    private final MotoService motoService;

    public MapaController(MotoService motoService) {
        this.motoService = motoService;
    }

    @GetMapping("/mapa")
    public String mapa(Model model) {
        List<MotoPoint> motos = motoService.listarParaMapa(); // <- camelCase
        model.addAttribute("motos", motos);
        return "mapa/index"; // precisa existir em templates/mapa/index.html
    }
}
