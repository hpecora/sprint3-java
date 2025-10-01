package com.mottu.mototracker.controller.api;

import com.mottu.mototracker.DTO.LocalizacaoDTO;
import com.mottu.mototracker.DTO.MotoDTO;
import com.mottu.mototracker.service.LocalizacaoService;
import com.mottu.mototracker.service.MotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/motos")
@RequiredArgsConstructor
public class MotoRestController {

    private final MotoService motoService;
    private final LocalizacaoService localizacaoService;

    // GET /api/motos?status=ATIVA
    @GetMapping
    public ResponseEntity<List<MotoDTO>> listar(@RequestParam(required = false) String status) {
        List<MotoDTO> motos = (status == null || status.isBlank())
                ? motoService.findAllDtos()
                : motoService.findByStatusDtos(status);
        return ResponseEntity.ok(motos);
    }

    // GET /api/motos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<MotoDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(motoService.findDtoById(id));
    }

    // GET /api/motos/{id}/historico
    @GetMapping("/{id}/historico")
    public ResponseEntity<List<LocalizacaoDTO>> historico(@PathVariable Long id) {
        return ResponseEntity.ok(localizacaoService.historicoLocalizacoes(id));
    }
}
