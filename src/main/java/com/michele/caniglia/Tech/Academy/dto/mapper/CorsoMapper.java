package com.michele.caniglia.Tech.Academy.dto.mapper;

import com.michele.caniglia.Tech.Academy.dto.CorsoDTO;
import com.michele.caniglia.Tech.Academy.model.Aula;
import com.michele.caniglia.Tech.Academy.model.Corso;
import com.michele.caniglia.Tech.Academy.model.Docente;
import com.michele.caniglia.Tech.Academy.model.PercorsoFormativo;

public class CorsoMapper {

    public static CorsoDTO toDTO(Corso corso) {
        if (corso == null) return null;

        CorsoDTO dto = new CorsoDTO();
        dto.setId(corso.getId());
        dto.setNome(corso.getNome());
        dto.setDataInizio(corso.getDataInizio());
        dto.setDataFine(corso.getDataFine());
        dto.setDurataOre(corso.getDurataOre());
        dto.setDocenteId(corso.getDocente() != null ? corso.getDocente().getId() : null);
        dto.setAulaId(corso.getAula() != null ? corso.getAula().getId() : null);
        dto.setPercorsoFormativoId(corso.getPercorsoFormativo() != null ? corso.getPercorsoFormativo().getId() : null);
        return dto;
    }

    public static Corso fromDTO(CorsoDTO dto, Docente docente, Aula aula, PercorsoFormativo percorso) {
        if (dto == null) return null;

        return Corso.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .dataInizio(dto.getDataInizio())
                .dataFine(dto.getDataFine())
                .durataOre(dto.getDurataOre())
                .docente(docente)
                .aula(aula)
                .percorsoFormativo(percorso)
                .build();
    }
}
