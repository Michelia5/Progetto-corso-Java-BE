package com.michele.caniglia.Esame.Java.service;

import com.michele.caniglia.Esame.Java.dto.StudenteDTO;
import com.michele.caniglia.Esame.Java.exception.ResourceNotFoundException;
import com.michele.caniglia.Esame.Java.model.Studente;
import com.michele.caniglia.Esame.Java.repository.StudenteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.michele.caniglia.Esame.Java.dto.mapper.StudenteMapper.*;

// Questo Service implementa il Service Layer Pattern.
// Contiene la logica di business per l'entità Studente, separando il controller dal repository.

@Service
@RequiredArgsConstructor
@Slf4j
public class StudenteService {

    private final StudenteRepository repository;

    public StudenteDTO creaStudente(StudenteDTO dto) {
        Studente nuovo = repository.save(fromDTO(dto));
        log.info("Creato nuovo studente: {}", nuovo.getEmail());
        return toDTO(nuovo);
    }

    public List<StudenteDTO> getAll() {
        List<Studente> studenti = repository.findAll();
        List<StudenteDTO> lista = new ArrayList<>();

        for (Studente s : studenti) {
            lista.add(toDTO(s));
        }

        log.info("Recuperati {} studenti", lista.size());
        return lista;
    }

    public StudenteDTO getById(Long id) {
        Studente studente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Studente non trovato con ID: " + id));
        log.info("Studente trovato con ID {}: {}", id, studente.getEmail());
        return toDTO(studente);
    }

    public StudenteDTO aggiornaStudente(Long id, StudenteDTO dto) {
        if (!repository.existsById(id)) {
            log.warn("Aggiornamento fallito: studente con ID {} non trovato", id);
            throw new ResourceNotFoundException("Studente da aggiornare non trovato con ID: " + id);
        }

        Studente daAggiornare = fromDTO(dto);
        daAggiornare.setId(id);
        Studente aggiornato = repository.save(daAggiornare);
        log.info("Studente aggiornato con ID {}: {}", id, aggiornato.getEmail());
        return toDTO(aggiornato);
    }

    public void eliminaStudente(Long id) {
        if (!repository.existsById(id)) {
            log.warn("Tentativo di eliminazione fallito: studente con ID {} non trovato", id);
            throw new ResourceNotFoundException("Studente da eliminare non trovato con ID: " + id);
        }

        repository.deleteById(id);
        log.info("Studente eliminato con ID {}", id);
    }
}
