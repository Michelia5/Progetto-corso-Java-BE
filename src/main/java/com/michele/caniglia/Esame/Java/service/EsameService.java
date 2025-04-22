package com.michele.caniglia.Esame.Java.service;

import com.michele.caniglia.Esame.Java.dto.EsameDTO;
import com.michele.caniglia.Esame.Java.model.Corso;
import com.michele.caniglia.Esame.Java.model.Esame;
import com.michele.caniglia.Esame.Java.repository.CorsoRepository;
import com.michele.caniglia.Esame.Java.repository.EsameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.michele.caniglia.Esame.Java.dto.mapper.EsameMapper.*;

@Service
@RequiredArgsConstructor
public class EsameService {

    private final EsameRepository repository;
    private final CorsoRepository corsoRepository;

    public EsameDTO crea(EsameDTO dto) {
        Corso corso = corsoRepository.findById(dto.getCorsoId()).orElse(null);
        return toDTO(repository.save(fromDTO(dto, corso)));
    }

    public List<EsameDTO> getAll() {
        List<EsameDTO> list = new ArrayList<>();
        for (Esame e : repository.findAll()) {
            list.add(toDTO(e));
        }
        return list;
    }

    public EsameDTO getById(Long id) {
        return toDTO(repository.findById(id).orElse(null));
    }

    public EsameDTO aggiorna(Long id, EsameDTO dto) {
        if (!repository.existsById(id)) return null;
        Corso corso = corsoRepository.findById(dto.getCorsoId()).orElse(null);
        Esame aggiornato = fromDTO(dto, corso);
        aggiornato.setId(id);
        return toDTO(repository.save(aggiornato));
    }

    public void elimina(Long id) {
        repository.deleteById(id);
    }
}
