package com.mottu.mototracker.controller;

import com.mottu.mototracker.model.Localizacao;
import com.mottu.mototracker.service.LocalizacaoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/localizacoes")
public class LocalizacaoController {

    private final LocalizacaoService service;

    public LocalizacaoController(LocalizacaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Localizacao> listar() {
        return service.listar();
    }

    @PostMapping
    public Localizacao cadastrar(@RequestBody @Valid Localizacao localizacao) {
        return service.salvar(localizacao);
    }

    @PutMapping("/{id}")
    public Localizacao atualizar(@PathVariable Long id, @RequestBody @Valid Localizacao localizacao) {
        Localizacao localizacaoExistente = service.buscarPorId(id);
        localizacao.setId(localizacaoExistente.getId());
        return service.salvar(localizacao);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
