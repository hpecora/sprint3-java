package com.mottu.mototracker.service;

import com.mottu.mototracker.model.Localizacao;
import com.mottu.mototracker.repository.LocalizacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalizacaoService {

    private final LocalizacaoRepository repository;

    public LocalizacaoService(LocalizacaoRepository repository) {
        this.repository = repository;
    }

    public List<Localizacao> listar() {
        return repository.findAll();
    }

    public Localizacao salvar(Localizacao localizacao) {
        return repository.save(localizacao);
    }

    public Localizacao buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Localizacao não encontrada"));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
