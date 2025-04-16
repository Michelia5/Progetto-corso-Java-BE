package com.michele.caniglia.Esame.Java.service;

import com.michele.caniglia.Esame.Java.dto.PercorsoFormativoDTO;
import com.michele.caniglia.Esame.Java.model.PercorsoFormativo;
import com.michele.caniglia.Esame.Java.repository.PercorsoFormativoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.michele.caniglia.Esame.Java.dto.mapper.PercorsoFormativoMapper.*;

@Service
@RequiredArgsConstructor
public class PercorsoFormativoService {

    private final PercorsoFormativoRepository repository;

    public PercorsoFormativoDTO crea(PercorsoFormativoDTO dto) {
        return toDTO(repository.save(fromDTO(dto)));
    }

    public List<PercorsoFormativoDTO> getAll() {
        List<PercorsoFormativoDTO> list = new ArrayList<>();
        for (PercorsoFormativo p : repository.findAll()) {
            list.add(toDTO(p));
        }
        return list;
    }

    public PercorsoFormativoDTO getById(Long id) {
        return toDTO(repository.findById(id).orElse(null));
    }

    public PercorsoFormativoDTO aggiorna(Long id, PercorsoFormativoDTO dto) {
        if (!repository.existsById(id)) return null;
        PercorsoFormativo aggiornato = fromDTO(dto);
        aggiornato.setId(id);
        return toDTO(repository.save(aggiornato));
    }

    public void elimina(Long id) {
        repository.deleteById(id);
    }
}