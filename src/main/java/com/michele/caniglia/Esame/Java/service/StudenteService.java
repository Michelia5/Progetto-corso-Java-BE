package com.michele.caniglia.Esame.Java.service;

import com.michele.caniglia.Esame.Java.dto.StudenteDTO;
import com.michele.caniglia.Esame.Java.model.Studente;
import com.michele.caniglia.Esame.Java.repository.StudenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.michele.caniglia.Esame.Java.dto.mapper.StudenteMapper.*;

import java.util.ArrayList;
import java.util.List;


// Questo Service implementa il Service Layer Pattern.
// Contiene la logica di business per l'entità Studente, separando il controller dal repository.


@Service
@RequiredArgsConstructor
public class StudenteService {

    private final StudenteRepository repository;

    public StudenteDTO creaStudente(StudenteDTO dto) {
        Studente nuovo = repository.save(fromDTO(dto));
        return toDTO(nuovo);
    }

    public List<StudenteDTO> getAll() {
        List<Studente> studenti = repository.findAll();
        List<StudenteDTO> lista = new ArrayList<>();

        for (Studente s : studenti) {
            lista.add(toDTO(s));
        }

        return lista;
    }

    public StudenteDTO getById(Long id) {
        Studente studente = repository.findById(id).orElse(null);
        return toDTO(studente);
    }

    public StudenteDTO aggiornaStudente(Long id, StudenteDTO dto) {
        if (!repository.existsById(id)) return null;

        Studente daAggiornare = fromDTO(dto);
        daAggiornare.setId(id);
        return toDTO(repository.save(daAggiornare));
    }

    public void eliminaStudente(Long id) {
        repository.deleteById(id);
    }
}