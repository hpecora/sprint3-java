package com.mottu.mototracker.service;

import com.mottu.mototracker.DTO.LocalizacaoDTO;
import com.mottu.mototracker.model.Localizacao;
import com.mottu.mototracker.repository.LocalizacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalizacaoService {

    private final LocalizacaoRepository repo;

    public LocalizacaoService(LocalizacaoRepository repo) {
        this.repo = repo;
    }

    /** Última localização de cada moto */
    public List<Localizacao> listarUltimasPorMoto() {
        return repo.findUltimasPorMoto();
    }

    /** Todas as localizações de uma moto (mais recentes primeiro) – ENTIDADE */
    public List<Localizacao> listarPorMoto(Long motoId) {
        return repo.findByMotoIdOrderByDataHoraDesc(motoId);
    }

    /** Todas as localizações de uma moto (mais recentes primeiro) – DTO */
    public List<LocalizacaoDTO> historicoLocalizacoes(Long motoId) {
        return repo.findByMotoIdOrderByDataHoraDesc(motoId)
                .stream()
                .map(LocalizacaoDTO::from)   // precisa existir LocalizacaoDTO.from(Localizacao)
                .toList();
    }

    public void registrar(Long id, double lat, double lng) {
    }
}
