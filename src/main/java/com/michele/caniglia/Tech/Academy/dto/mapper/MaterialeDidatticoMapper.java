package com.michele.caniglia.Tech.Academy.dto.mapper;

import com.michele.caniglia.Tech.Academy.dto.MaterialeDidatticoDTO;
import com.michele.caniglia.Tech.Academy.model.Corso;
import com.michele.caniglia.Tech.Academy.model.MaterialeDidattico;

public class MaterialeDidatticoMapper {

    public static MaterialeDidatticoDTO toDTO(MaterialeDidattico m) {
        if (m == null) return null;

        MaterialeDidatticoDTO dto = new MaterialeDidatticoDTO();
        dto.setId(m.getId());
        dto.setTitolo(m.getTitolo());
        dto.setTipo(m.getTipo() != null ? m.getTipo().name() : null);
        dto.setDescrizione(m.getDescrizione());
        dto.setCorsoId(m.getCorso() != null ? m.getCorso().getId() : null);
        return dto;
    }

    public static MaterialeDidattico fromDTO(MaterialeDidatticoDTO dto, Corso corso) {
        if (dto == null) return null;

        return MaterialeDidattico.builder()
                .id(dto.getId())
                .titolo(dto.getTitolo())
                .tipo(MaterialeDidattico.Tipo.valueOf(dto.getTipo().toUpperCase()))
                .descrizione(dto.getDescrizione())
                .corso(corso)
                .build();
    }
}
