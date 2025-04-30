package com.michele.caniglia.Tech.Academy.dto.mapper;

import com.michele.caniglia.Tech.Academy.dto.EsameDTO;
import com.michele.caniglia.Tech.Academy.model.Corso;
import com.michele.caniglia.Tech.Academy.model.Esame;

public class EsameMapper {

    public static EsameDTO toDTO(Esame esame) {
        if (esame == null) return null;

        EsameDTO dto = new EsameDTO();
        dto.setId(esame.getId());
        dto.setNome(esame.getNome());
        dto.setData(esame.getData());
        dto.setCorsoId(esame.getCorso() != null ? esame.getCorso().getId() : null);
        return dto;
    }

    public static Esame fromDTO(EsameDTO dto, Corso corso) {
        if (dto == null) return null;

        return Esame.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .data(dto.getData())
                .corso(corso)
                .build();
    }
}
