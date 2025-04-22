package com.michele.caniglia.Esame.Java.dto;

import com.michele.caniglia.Esame.Java.model.Corso;
import com.michele.caniglia.Esame.Java.model.Iscrizione;
import com.michele.caniglia.Esame.Java.model.Studente;

public class IscrizioneMapper {

    public static IscrizioneDTO toDTO(Iscrizione i) {
        if (i == null) return null;

        IscrizioneDTO dto = new IscrizioneDTO();
        dto.setId(i.getId());
        dto.setDataIscrizione(i.getDataIscrizione());
        dto.setStato(i.getStato().name());
        dto.setVoto(i.getVoto());
        dto.setStudenteId(i.getStudente() != null ? i.getStudente().getId() : null);
        dto.setCorsoId(i.getCorso() != null ? i.getCorso().getId() : null);
        return dto;
    }

    public static Iscrizione fromDTO(IscrizioneDTO dto, Studente studente, Corso corso) {
        if (dto == null) return null;

        return Iscrizione.builder()
                .id(dto.getId())
                .dataIscrizione(dto.getDataIscrizione())
                .stato(Iscrizione.StatoIscrizione.valueOf(dto.getStato().toUpperCase()))
                .voto(dto.getVoto())
                .studente(studente)
                .corso(corso)
                .build();
    }
}
