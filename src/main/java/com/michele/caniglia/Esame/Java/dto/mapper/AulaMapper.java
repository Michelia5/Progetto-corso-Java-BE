package com.michele.caniglia.Esame.Java.dto.mapper;

import com.michele.caniglia.Esame.Java.dto.AulaDTO;
import com.michele.caniglia.Esame.Java.model.Aula;

public class AulaMapper {

    public static AulaDTO toDTO(Aula aula) {
        if (aula == null) return null;

        AulaDTO dto = new AulaDTO();
        dto.setId(aula.getId());
        dto.setNome(aula.getNome());
        dto.setCapienza(aula.getCapienza());
        dto.setPiano(aula.getPiano());
        return dto;
    }

    public static Aula fromDTO(AulaDTO dto) {
        if (dto == null) return null;

        return Aula.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .capienza(dto.getCapienza())
                .piano(dto.getPiano())
                .build();
    }
}
