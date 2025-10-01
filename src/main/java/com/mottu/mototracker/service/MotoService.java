// src/main/java/com/mottu/mototracker/service/MotoService.java
package com.mottu.mototracker.service;

import com.mottu.mototracker.DTO.MotoDTO;
import com.mottu.mototracker.DTO.MotoPoint;
import com.mottu.mototracker.model.Localizacao;
import com.mottu.mototracker.model.Moto;
import com.mottu.mototracker.repository.LocalizacaoRepository;
import com.mottu.mototracker.repository.MotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.Normalizer;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MotoService {

    private final MotoRepository motoRepo;
    private final LocalizacaoRepository locRepo;

    public MotoService(MotoRepository motoRepo, LocalizacaoRepository locRepo) {
        this.motoRepo = motoRepo;
        this.locRepo = locRepo;
    }

    /* =========================
       QUERIES PRINCIPAIS
       ========================= */

    @Transactional(readOnly = true)
    public List<MotoDTO> findAllDtos() {
        return motoRepo.findAll().stream()
                .map(m -> MotoDTO.from(m, ultimaLoc(m.getId())))
                .toList();
    }

    @Transactional(readOnly = true)
    public MotoDTO findDtoById(Long id) {
        Moto moto = motoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Moto não encontrada: id=" + id));
        return MotoDTO.from(moto, ultimaLoc(id));
    }

    /* =========================
       WRITE OPERATIONS
       ========================= */

    @Transactional
    public MotoDTO create(MotoDTO dto) {
        validarStatus(dto.getStatus());

        if (motoRepo.existsByPlacaIgnoreCase(dto.getPlaca())) {
            throw new IllegalArgumentException("Já existe moto com a placa: " + dto.getPlaca());
        }

        Moto moto = new Moto();
        moto.setPlaca(dto.getPlaca());
        moto.setModelo(dto.getModelo());
        moto.setStatus(dto.getStatus());

        Moto saved = motoRepo.save(moto);
        return MotoDTO.from(saved, null);
    }

    @Transactional
    public MotoDTO update(Long id, MotoDTO dto) {
        validarStatus(dto.getStatus());

        Moto moto = motoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Moto não encontrada: id=" + id));

        moto.setPlaca(dto.getPlaca());
        moto.setModelo(dto.getModelo());
        moto.setStatus(dto.getStatus());

        Moto saved = motoRepo.save(moto);
        return MotoDTO.from(saved, ultimaLoc(id));
    }

    @Transactional
    public void delete(Long id) {
        if (!motoRepo.existsById(id)) {
            throw new IllegalArgumentException("Moto não encontrada: id=" + id);
        }
        motoRepo.deleteById(id);
    }

    /* =========================
       AÇÕES DE NEGÓCIO
       ========================= */

    @Transactional
    public void enviarParaPatio(Long id) {
        Moto moto = motoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Moto não encontrada: id=" + id));
        moto.setStatus("MANUTENCAO");
        motoRepo.save(moto);
    }

    @Transactional
    public void ativar(Long id) {
        Moto moto = motoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Moto não encontrada: id=" + id));
        moto.setStatus("ATIVA");
        motoRepo.save(moto);
    }

    /* =========================
       PÁTIO
       ========================= */

    @Transactional(readOnly = true)
    public List<MotoDTO> listarEmPatio() {
        return motoRepo.findByStatusNotIgnoreCase("ATIVA").stream()
                .map(m -> MotoDTO.from(m, ultimaLoc(m.getId())))
                .toList();
    }

    /* =========================
       MÉTRICAS DO DASHBOARD
       ========================= */

    @Transactional(readOnly = true)
    public long countAtivas() {
        return motoRepo.countByStatus("ATIVA");
    }

    @Transactional(readOnly = true)
    public long countManutencao() {
        return motoRepo.countByStatus("MANUTENCAO");
    }

    @Transactional(readOnly = true)
    public long countDesativadas() {
        return motoRepo.countByStatus("DESATIVADA");
    }

    @Transactional(readOnly = true)
    public List<MotoDTO> findByStatusDtos(String status) {
        return motoRepo.findByStatusIgnoreCase(status).stream()
                .map(m -> MotoDTO.from(m, ultimaLoc(m.getId())))
                .toList();
    }

    /* =========================
       MAPA
       ========================= */

    @Transactional(readOnly = true)
    public List<MotoPoint> listarParaMapa() {
        // coordenada do pátio (ajuste para o endereço real do seu pátio)
        final double PATIO_LAT = -23.55052;
        final double PATIO_LNG = -46.63331;

        return motoRepo.findAll().stream()
                .map(m -> {
                    Localizacao ultima = ultimaLoc(m.getId());

                    // normaliza status -> front: ativa | manut | off
                    String statusNorm = switch ((m.getStatus() != null ? m.getStatus().toUpperCase() : "")) {
                        case "ATIVA"       -> "ativa";
                        case "MANUTENCAO"  -> "manut";
                        default            -> "off";
                    };

                    Double lat = null, lng = null;

                    if (ultima != null) {
                        lat = ultima.getLatitude();
                        lng = ultima.getLongitude();
                    } else if ("MANUTENCAO".equalsIgnoreCase(m.getStatus())) {
                        // sem histórico: posiciona no pátio
                        lat = PATIO_LAT;
                        lng = PATIO_LNG;
                    }
                    else if ("ATIVA".equalsIgnoreCase(m.getStatus())) {
                        lat = -23.56168;  // sua base/sede (exemplo)
                        lng = -46.65598;
                    }
                    else {
                        // sem histórico e não está em manutenção -> não mostrar
                        return null;
                    }

                    return new MotoPoint(
                            m.getId(),
                            m.getPlaca(),
                            m.getModelo(),
                            statusNorm,
                            lat,
                            lng,
                            (ultima != null ? ultima.getDataHora() : null)
                    );
                })
                .filter(p -> p != null)
                .collect(Collectors.toList());
    }
    /* =========================
       HELPERS
       ========================= */

    private Localizacao ultimaLoc(Long motoId) {
        return locRepo.findTopByMotoIdOrderByDataHoraDesc(motoId).orElse(null);
    }

    private void validarStatus(String status) {
        if (status == null) return;
        String s = semAcento(status).trim().toUpperCase();
        if (!s.equals("ATIVA") && !s.equals("MANUTENCAO") && !s.equals("DESATIVADA")) {
            throw new IllegalArgumentException("Status inválido: " + status +
                    " (use ATIVA, MANUTENCAO ou DESATIVADA)");
        }
    }

    private String normalizaStatusFront(String statusDb) {
        String s = semAcento(statusDb == null ? "" : statusDb).trim().toUpperCase();
        return switch (s) {
            case "ATIVA" -> "ativa";
            case "MANUTENCAO", "MANUT", "EM MANUTENCAO" -> "manut";
            case "DESATIVADA", "INATIVA", "OFF", "DESATIVADO", "INATIVO" -> "off";
            default -> "off";
        };
    }

    private static String semAcento(String v) {
        return Normalizer.normalize(v == null ? "" : v, Normalizer.Form.NFD)
                .replaceAll("\\p{M}+", "");
    }
}
