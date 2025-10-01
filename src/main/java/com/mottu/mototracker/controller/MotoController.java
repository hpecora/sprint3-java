// src/main/java/com/mottu/mototracker/controller/MotoController.java
package com.mottu.mototracker.controller;

import com.mottu.mototracker.DTO.MotoDTO;
import com.mottu.mototracker.service.MotoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/motos")
public class MotoController {

    private final MotoService motoService;

    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    // <<< ADICIONE ESTE HANDLER >>>
    @GetMapping
    public String list(Model model){
        model.addAttribute("motos", motoService.findAllDtos());
        return "motos/list"; // templates/motos/list.html
    }

    @GetMapping("/new")
    public String newForm(Model model){
        model.addAttribute("moto", new MotoDTO());
        return "motos/new";
    }

    @PostMapping
    public String create(@ModelAttribute("moto") MotoDTO dto){
        motoService.create(dto);
        return "redirect:/motos";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model){
        model.addAttribute("moto", motoService.findDtoById(id));
        return "motos/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("moto") MotoDTO dto){
        motoService.update(id, dto);
        return "redirect:/motos";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        motoService.delete(id);
        return "redirect:/motos";
    }

    @PostMapping("/{id}/patio")
    public String enviarParaPatio(@PathVariable Long id){
        motoService.enviarParaPatio(id);
        return "redirect:/motos";
    }

    @PostMapping("/{id}/ativar")
    public String ativar(@PathVariable Long id){
        motoService.ativar(id);
        return "redirect:/patio";
    }
}
