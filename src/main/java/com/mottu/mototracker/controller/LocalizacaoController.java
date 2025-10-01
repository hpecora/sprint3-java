// src/main/java/com/mottu/mototracker/controller/LocalizacaoController.java
package com.mottu.mototracker.controller;

import com.mottu.mototracker.service.LocalizacaoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/motos/{id}/localizacoes")
public class LocalizacaoController {

    private final LocalizacaoService service;

    public LocalizacaoController(LocalizacaoService service) {
        this.service = service;
    }

    // Ex.: POST /motos/5/localizacoes?lat=-23.56&lng=-46.64
    @PostMapping
    public String criar(@PathVariable Long id,
                        @RequestParam double lat,
                        @RequestParam double lng) {
        service.registrar(id, lat, lng);
        return "redirect:/mapa";
    }
}
