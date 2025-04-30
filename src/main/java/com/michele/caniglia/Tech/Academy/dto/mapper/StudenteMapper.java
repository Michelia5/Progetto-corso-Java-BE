package com.michele.caniglia.Tech.Academy.dto.mapper;

import com.michele.caniglia.Tech.Academy.dto.StudenteDTO;
import com.michele.caniglia.Tech.Academy.model.Studente;


// Questi metodi rappresentano un'applicazione del Factory Method Pattern.
// Consentono la conversione tra DTO e entità mantenendo separazione tra i livelli.


public class StudenteMapper {

    public static StudenteDTO toDTO(Studente studente) {
        if (studente == null) return null;

        StudenteDTO dto = new StudenteDTO();
        dto.setId(studente.getId());
        dto.setNome(studente.getNome());
        dto.setCognome(studente.getCognome());
        dto.setEmail(studente.getEmail());
        dto.setDataNascita(studente.getDataNascita());
        return dto;
    }

    public static Studente fromDTO(StudenteDTO dto) {
        if (dto == null) return null;

        return Studente.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .cognome(dto.getCognome())
                .email(dto.getEmail())
                .dataNascita(dto.getDataNascita())
                .build();
    }
}
