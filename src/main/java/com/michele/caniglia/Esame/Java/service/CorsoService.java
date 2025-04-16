package com.michele.caniglia.Esame.Java.service;

import com.michele.caniglia.Esame.Java.dto.CorsoDTO;
import com.michele.caniglia.Esame.Java.model.*;
import com.michele.caniglia.Esame.Java.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.michele.caniglia.Esame.Java.dto.mapper.CorsoMapper.*;

@Service
@RequiredArgsConstructor
public class CorsoService {

    private final CorsoRepository corsoRepository;
    private final DocenteRepository docenteRepository;
    private final AulaRepository aulaRepository;
    private final PercorsoFormativoRepository percorsoRepository;

    public CorsoDTO crea(CorsoDTO dto) {
        Corso corso = fromDTO(
                dto,
                docenteRepository.findById(dto.getDocenteId()).orElse(null),
                aulaRepository.findById(dto.getAulaId()).orElse(null),
                percorsoRepository.findById(dto.getPercorsoFormativoId()).orElse(null)
        );
        return toDTO(corsoRepository.save(corso));
    }

    public List<CorsoDTO> getAll() {
        List<CorsoDTO> list = new ArrayList<>();
        for (Corso c : corsoRepository.findAll()) {
            list.add(toDTO(c));
        }
        return list;
    }

    public CorsoDTO getById(Long id) {
        return toDTO(corsoRepository.findById(id).orElse(null));
    }

    public CorsoDTO aggiorna(Long id, CorsoDTO dto) {
        if (!corsoRepository.existsById(id)) return null;

        Corso aggiornato = fromDTO(
                dto,
                docenteRepository.findById(dto.getDocenteId()).orElse(null),
                aulaRepository.findById(dto.getAulaId()).orElse(null),
                percorsoRepository.findById(dto.getPercorsoFormativoId()).orElse(null)
        );
        aggiornato.setId(id);
        return toDTO(corsoRepository.save(aggiornato));
    }

    public void elimina(Long id) {
        corsoRepository.deleteById(id);
    }
}
