package com.mottu.mototracker.controller;

import com.mottu.mototracker.service.MotoService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final MotoService motoService;

    public HomeController(MotoService motoService) {
        this.motoService = motoService;
    }

    @GetMapping("/")
    public String index(Authentication auth, Model model) {
        if (auth == null || !auth.isAuthenticated()) {
            return "redirect:/login";
        }
        model.addAttribute("qtdAtivas", motoService.countAtivas());
        model.addAttribute("qtdManutencao", motoService.countManutencao());
        model.addAttribute("qtdDesativadas", motoService.countDesativadas());
        return "index"; // sem a barra inicial
    }
}
