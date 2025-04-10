package com.michele.caniglia.Esame.Java.service;

import com.michele.caniglia.Esame.Java.dto.StudenteDTO;
import com.michele.caniglia.Esame.Java.model.Studente;
import com.michele.caniglia.Esame.Java.repository.StudenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudenteService {

    private final StudenteRepository repository;

    public StudenteDTO creaStudente(StudenteDTO dto) {
        Studente nuovo = repository.save(fromDTO(dto));
        return toDTO(nuovo);
    }

    public List<StudenteDTO> getAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public StudenteDTO getById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
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

    private StudenteDTO toDTO(Studente studente) {
        StudenteDTO dto = new StudenteDTO();
        dto.setId(studente.getId());
        dto.setNome(studente.getNome());
        dto.setCognome(studente.getCognome());
        dto.setEmail(studente.getEmail());
        dto.setDataNascita(studente.getDataNascita());
        return dto;
    }

    private Studente fromDTO(StudenteDTO dto) {
        return Studente.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .cognome(dto.getCognome())
                .email(dto.getEmail())
                .dataNascita(dto.getDataNascita())
                .build();
    }
}