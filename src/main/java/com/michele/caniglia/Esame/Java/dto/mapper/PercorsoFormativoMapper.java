package com.michele.caniglia.Esame.Java.dto.mapper;

import com.michele.caniglia.Esame.Java.dto.PercorsoFormativoDTO;
import com.michele.caniglia.Esame.Java.model.PercorsoFormativo;

public class PercorsoFormativoMapper {

    public static PercorsoFormativoDTO toDTO(PercorsoFormativo percorso) {
        if (percorso == null) return null;

        PercorsoFormativoDTO dto = new PercorsoFormativoDTO();
        dto.setId(percorso.getId());
        dto.setNome(percorso.getNome());
        dto.setDescrizione(percorso.getDescrizione());
        return dto;
    }

    public static PercorsoFormativo fromDTO(PercorsoFormativoDTO dto) {
        if (dto == null) return null;

        return PercorsoFormativo.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .descrizione(dto.getDescrizione())
                .build();
    }
}
