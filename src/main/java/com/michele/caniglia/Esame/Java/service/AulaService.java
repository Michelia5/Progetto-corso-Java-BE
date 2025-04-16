package com.michele.caniglia.Esame.Java.service;

import com.michele.caniglia.Esame.Java.dto.AulaDTO;
import com.michele.caniglia.Esame.Java.model.Aula;
import com.michele.caniglia.Esame.Java.repository.AulaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.michele.caniglia.Esame.Java.dto.mapper.AulaMapper.*;

@Service
@RequiredArgsConstructor
public class AulaService {

    private final AulaRepository repository;

    public AulaDTO crea(AulaDTO dto) {
        return toDTO(repository.save(fromDTO(dto)));
    }

    public List<AulaDTO> getAll() {
        List<AulaDTO> list = new ArrayList<>();
        for (Aula a : repository.findAll()) {
            list.add(toDTO(a));
        }
        return list;
    }

    public AulaDTO getById(Long id) {
        return toDTO(repository.findById(id).orElse(null));
    }

    public AulaDTO aggiorna(Long id, AulaDTO dto) {
        if (!repository.existsById(id)) return null;
        Aula aggiornata = fromDTO(dto);
        aggiornata.setId(id);
        return toDTO(repository.save(aggiornata));
    }

    public void elimina(Long id) {
        repository.deleteById(id);
    }
}
