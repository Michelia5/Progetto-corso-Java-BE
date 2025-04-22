package com.michele.caniglia.Esame.Java.service;

import com.michele.caniglia.Esame.Java.dto.MaterialeDidatticoDTO;
import com.michele.caniglia.Esame.Java.model.Corso;
import com.michele.caniglia.Esame.Java.model.MaterialeDidattico;
import com.michele.caniglia.Esame.Java.repository.CorsoRepository;
import com.michele.caniglia.Esame.Java.repository.MaterialeDidatticoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.michele.caniglia.Esame.Java.dto.mapper.MaterialeDidatticoMapper.*;

@Service
@RequiredArgsConstructor
public class MaterialeDidatticoService {

    private final MaterialeDidatticoRepository repository;
    private final CorsoRepository corsoRepository;

    public MaterialeDidatticoDTO crea(MaterialeDidatticoDTO dto) {
        Corso corso = corsoRepository.findById(dto.getCorsoId()).orElse(null);
        return toDTO(repository.save(fromDTO(dto, corso)));
    }

    public List<MaterialeDidatticoDTO> getAll() {
        List<MaterialeDidatticoDTO> list = new ArrayList<>();
        for (MaterialeDidattico m : repository.findAll()) {
            list.add(toDTO(m));
        }
        return list;
    }

    public MaterialeDidatticoDTO getById(Long id) {
        return toDTO(repository.findById(id).orElse(null));
    }

    public MaterialeDidatticoDTO aggiorna(Long id, MaterialeDidatticoDTO dto) {
        if (!repository.existsById(id)) return null;
        Corso corso = corsoRepository.findById(dto.getCorsoId()).orElse(null);
        MaterialeDidattico aggiornato = fromDTO(dto, corso);
        aggiornato.setId(id);
        return toDTO(repository.save(aggiornato));
    }

    public void elimina(Long id) {
        repository.deleteById(id);
    }
}
