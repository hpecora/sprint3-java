package com.mottu.mototracker.controller;

import com.mottu.mototracker.model.Moto;
import com.mottu.mototracker.service.MotoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/motos")
public class MotoController {

    private final MotoService service;

    public MotoController(MotoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Moto> listar() {
        return service.listar();
    }

    @PostMapping
    public Moto cadastrar(@RequestBody @Valid Moto moto) {
        return service.salvar(moto);
    }

    @PutMapping("/{id}")
    public Moto atualizar(@PathVariable Long id, @RequestBody @Valid Moto moto) {
        Moto motoExistente = service.buscarPorId(id);
        moto.setId(motoExistente.getId());
        return service.salvar(moto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
