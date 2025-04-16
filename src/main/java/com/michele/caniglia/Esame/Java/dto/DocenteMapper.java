package com.michele.caniglia.Esame.Java.dto;

import com.michele.caniglia.Esame.Java.model.Docente;

public class DocenteMapper {

    public static DocenteDTO toDTO(Docente docente) {
        if (docente == null) return null;

        DocenteDTO dto = new DocenteDTO();
        dto.setId(docente.getId());
        dto.setNome(docente.getNome());
        dto.setCognome(docente.getCognome());
        dto.setEmail(docente.getEmail());
        return dto;
    }

    public static Docente fromDTO(DocenteDTO dto) {
        if (dto == null) return null;

        return Docente.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .cognome(dto.getCognome())
                .email(dto.getEmail())
                .build();
    }
}
