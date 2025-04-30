package com.michele.caniglia.Tech.Academy.service;

import com.michele.caniglia.Tech.Academy.dto.EsameDTO;
import com.michele.caniglia.Tech.Academy.exception.ResourceNotFoundException;
import com.michele.caniglia.Tech.Academy.model.Corso;
import com.michele.caniglia.Tech.Academy.model.Esame;
import com.michele.caniglia.Tech.Academy.repository.CorsoRepository;
import com.michele.caniglia.Tech.Academy.repository.EsameRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.michele.caniglia.Tech.Academy.dto.mapper.EsameMapper.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class EsameService {

    private final EsameRepository repository;
    private final CorsoRepository corsoRepository;

    public EsameDTO crea(EsameDTO dto) {
        Corso corso = corsoRepository.findById(dto.getCorsoId())
                .orElseThrow(() -> new ResourceNotFoundException("Corso non trovato con ID: " + dto.getCorsoId()));
        Esame salvato = repository.save(fromDTO(dto, corso));
        log.info("Creato nuovo esame: {}", salvato.getNome());
        return toDTO(salvato);
    }

    public List<EsameDTO> getAll() {
        List<EsameDTO> list = new ArrayList<>();
        for (Esame e : repository.findAll()) {
            list.add(toDTO(e));
        }
        log.info("Restituiti {} esami", list.size());
        return list;
    }

    public EsameDTO getById(Long id) {
        Esame esame = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Esame non trovato con ID: " + id));
        log.info("Esame trovato con ID {}: {}", id, esame.getNome());
        return toDTO(esame);
    }

    public EsameDTO aggiorna(Long id, EsameDTO dto) {
        if (!repository.existsById(id)) {
            log.warn("Aggiornamento fallito: esame con ID {} non trovato", id);
            throw new ResourceNotFoundException("Esame da aggiornare non trovato con ID: " + id);
        }

        Corso corso = corsoRepository.findById(dto.getCorsoId())
                .orElseThrow(() -> new ResourceNotFoundException("Corso non trovato con ID: " + dto.getCorsoId()));

        Esame aggiornato = fromDTO(dto, corso);
        aggiornato.setId(id);
        Esame salvato = repository.save(aggiornato);
        log.info("Esame aggiornato con ID {}: {}", id, salvato.getNome());
        return toDTO(salvato);
    }

    public void elimina(Long id) {
        if (!repository.existsById(id)) {
            log.warn("Tentativo di eliminazione fallito: esame con ID {} non trovato", id);
            throw new ResourceNotFoundException("Esame da eliminare non trovato con ID: " + id);
        }

        repository.deleteById(id);
        log.info("Esame eliminato con ID {}", id);
    }
}
