package com.michele.caniglia.Tech.Academy.service;

import com.michele.caniglia.Tech.Academy.dto.DocenteDTO;
import com.michele.caniglia.Tech.Academy.exception.ResourceNotFoundException;
import com.michele.caniglia.Tech.Academy.model.Docente;
import com.michele.caniglia.Tech.Academy.repository.DocenteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.michele.caniglia.Tech.Academy.dto.mapper.DocenteMapper.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocenteService {

    private final DocenteRepository repository;

    public DocenteDTO crea(DocenteDTO dto) {
        Docente nuovo = repository.save(fromDTO(dto));
        log.info("Creato nuovo docente: {}", nuovo.getEmail());
        return toDTO(nuovo);
    }

    public List<DocenteDTO> getAll() {
        List<DocenteDTO> lista = new ArrayList<>();
        for (Docente d : repository.findAll()) {
            lista.add(toDTO(d));
        }
        log.info("Recuperati {} docenti", lista.size());
        return lista;
    }

    public DocenteDTO getById(Long id) {
        Docente d = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Docente non trovato con ID: " + id));
        log.info("Docente trovato con ID {}: {}", id, d.getEmail());
        return toDTO(d);
    }

    public DocenteDTO aggiorna(Long id, DocenteDTO dto) {
        if (!repository.existsById(id)) {
            log.warn("Aggiornamento fallito: docente con ID {} non trovato", id);
            throw new ResourceNotFoundException("Docente da aggiornare non trovato con ID: " + id);
        }
        Docente aggiornato = fromDTO(dto);
        aggiornato.setId(id);
        Docente salvato = repository.save(aggiornato);
        log.info("Docente aggiornato con ID {}: {}", id, salvato.getEmail());
        return toDTO(salvato);
    }

    public void elimina(Long id) {
        if (!repository.existsById(id)) {
            log.warn("Tentativo di eliminazione fallito: docente con ID {} non trovato", id);
            throw new ResourceNotFoundException("Docente da eliminare non trovato con ID: " + id);
        }
        repository.deleteById(id);
        log.info("Docente eliminato con ID {}", id);
    }
}
