package com.mottu.mototracker.service;

import com.mottu.mototracker.model.Moto;
import com.mottu.mototracker.repository.MotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

    private final MotoRepository repository;

    public MotoService(MotoRepository repository) {
        this.repository = repository;
    }

    public List<Moto> listar() {
        return repository.findAll();
    }

    public Moto salvar(Moto moto) {
        return repository.save(moto);
    }

    public Moto buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Moto não encontrada"));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
