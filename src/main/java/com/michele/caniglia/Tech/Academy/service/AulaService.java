package com.michele.caniglia.Tech.Academy.service;

import com.michele.caniglia.Tech.Academy.dto.AulaDTO;
import com.michele.caniglia.Tech.Academy.exception.ResourceNotFoundException;
import com.michele.caniglia.Tech.Academy.model.Aula;
import com.michele.caniglia.Tech.Academy.repository.AulaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.michele.caniglia.Tech.Academy.dto.mapper.AulaMapper.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AulaService {

    private final AulaRepository repository;

    public AulaDTO crea(AulaDTO dto) {
        Aula nuova = repository.save(fromDTO(dto));
        log.info("Creata aula: {}", nuova.getNome());
        return toDTO(nuova);
    }

    public List<AulaDTO> getAll() {
        List<AulaDTO> list = new ArrayList<>();
        for (Aula a : repository.findAll()) {
            list.add(toDTO(a));
        }
        log.info("Restituite {} aule", list.size());
        return list;
    }

    public AulaDTO getById(Long id) {
        Aula aula = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aula non trovata con ID: " + id));
        log.info("Aula trovata con ID {}: {}", id, aula.getNome());
        return toDTO(aula);
    }

    public AulaDTO aggiorna(Long id, AulaDTO dto) {
        if (!repository.existsById(id)) {
            log.warn("Aggiornamento fallito: aula con ID {} non trovata", id);
            throw new ResourceNotFoundException("Aula da aggiornare non trovata con ID: " + id);
        }

        Aula aggiornata = fromDTO(dto);
        aggiornata.setId(id);
        Aula salvata = repository.save(aggiornata);
        log.info("Aula aggiornata con ID {}: {}", id, salvata.getNome());
        return toDTO(salvata);
    }

    public void elimina(Long id) {
        if (!repository.existsById(id)) {
            log.warn("Tentativo di eliminazione fallito: aula con ID {} non trovata", id);
            throw new ResourceNotFoundException("Aula da eliminare non trovata con ID: " + id);
        }

        repository.deleteById(id);
        log.info("Aula eliminata con ID {}", id);
    }
}
