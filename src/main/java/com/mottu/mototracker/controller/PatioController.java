package com.mottu.mototracker.controller;

import com.mottu.mototracker.service.MotoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatioController {

    private final MotoService motoService;

    public PatioController(MotoService motoService) {
        this.motoService = motoService;
    }

    // Página do pátio
    @GetMapping("/patio")
    public String mostrarPatio(Model model) {
        model.addAttribute("motos", motoService.listarEmPatio());
        return "patio/index"; // templates/patio/index.html
    }

    // REMOVIDOS:
    // @PostMapping("/motos/{id}/patio")
    // @PostMapping("/motos/{id}/ativar")
}
