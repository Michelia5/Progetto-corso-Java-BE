package com.michele.caniglia.Tech.Academy.service;

import com.michele.caniglia.Tech.Academy.dto.PercorsoFormativoDTO;
import com.michele.caniglia.Tech.Academy.exception.ResourceNotFoundException;
import com.michele.caniglia.Tech.Academy.model.PercorsoFormativo;
import com.michele.caniglia.Tech.Academy.repository.PercorsoFormativoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.michele.caniglia.Tech.Academy.dto.mapper.PercorsoFormativoMapper.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class PercorsoFormativoService {

    private final PercorsoFormativoRepository repository;

    public PercorsoFormativoDTO crea(PercorsoFormativoDTO dto) {
        PercorsoFormativo salvato = repository.save(fromDTO(dto));
        log.info("Creato percorso formativo: {}", salvato.getNome());
        return toDTO(salvato);
    }

    public List<PercorsoFormativoDTO> getAll() {
        List<PercorsoFormativoDTO> list = new ArrayList<>();
        for (PercorsoFormativo p : repository.findAll()) {
            list.add(toDTO(p));
        }
        log.info("Restituiti {} percorsi formativi", list.size());
        return list;
    }

    public PercorsoFormativoDTO getById(Long id) {
        PercorsoFormativo percorso = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Percorso formativo non trovato con ID: " + id));
        log.info("Percorso formativo trovato con ID {}: {}", id, percorso.getNome());
        return toDTO(percorso);
    }

    public PercorsoFormativoDTO aggiorna(Long id, PercorsoFormativoDTO dto) {
        if (!repository.existsById(id)) {
            log.warn("Aggiornamento fallito: percorso con ID {} non trovato", id);
            throw new ResourceNotFoundException("Percorso formativo da aggiornare non trovato con ID: " + id);
        }
        PercorsoFormativo aggiornato = fromDTO(dto);
        aggiornato.setId(id);
        PercorsoFormativo salvato = repository.save(aggiornato);
        log.info("Percorso formativo aggiornato con ID {}: {}", id, salvato.getNome());
        return toDTO(salvato);
    }

    public void elimina(Long id) {
        if (!repository.existsById(id)) {
            log.warn("Tentativo di eliminazione fallito: percorso con ID {} non trovato", id);
            throw new ResourceNotFoundException("Percorso formativo da eliminare non trovato con ID: " + id);
        }

        repository.deleteById(id);
        log.info("Percorso formativo eliminato con ID {}", id);
    }
}
