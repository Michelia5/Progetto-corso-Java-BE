package com.michele.caniglia.Tech.Academy.service;

import com.michele.caniglia.Tech.Academy.dto.MaterialeDidatticoDTO;
import com.michele.caniglia.Tech.Academy.exception.ResourceNotFoundException;
import com.michele.caniglia.Tech.Academy.model.Corso;
import com.michele.caniglia.Tech.Academy.model.MaterialeDidattico;
import com.michele.caniglia.Tech.Academy.repository.CorsoRepository;
import com.michele.caniglia.Tech.Academy.repository.MaterialeDidatticoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.michele.caniglia.Tech.Academy.dto.mapper.MaterialeDidatticoMapper.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MaterialeDidatticoService {

    private final MaterialeDidatticoRepository repository;
    private final CorsoRepository corsoRepository;

    public MaterialeDidatticoDTO crea(MaterialeDidatticoDTO dto) {
        Corso corso = corsoRepository.findById(dto.getCorsoId())
                .orElseThrow(() -> new ResourceNotFoundException("Corso non trovato con ID: " + dto.getCorsoId()));

        MaterialeDidattico salvato = repository.save(fromDTO(dto, corso));
        log.info("Creato nuovo materiale didattico per il corso {}: {}", corso.getId(), salvato.getTitolo());
        return toDTO(salvato);
    }

    public List<MaterialeDidatticoDTO> getAll() {
        List<MaterialeDidatticoDTO> list = new ArrayList<>();
        for (MaterialeDidattico m : repository.findAll()) {
            list.add(toDTO(m));
        }
        log.info("Restituiti {} materiali didattici", list.size());
        return list;
    }

    public MaterialeDidatticoDTO getById(Long id) {
        MaterialeDidattico materiale = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Materiale didattico non trovato con ID: " + id));
        log.info("Materiale didattico trovato con ID {}: {}", id, materiale.getTitolo());
        return toDTO(materiale);
    }

    public MaterialeDidatticoDTO aggiorna(Long id, MaterialeDidatticoDTO dto) {
        if (!repository.existsById(id)) {
            log.warn("Aggiornamento fallito: materiale con ID {} non trovato", id);
            throw new ResourceNotFoundException("Materiale didattico da aggiornare non trovato con ID: " + id);
        }

        Corso corso = corsoRepository.findById(dto.getCorsoId())
                .orElseThrow(() -> new ResourceNotFoundException("Corso non trovato con ID: " + dto.getCorsoId()));

        MaterialeDidattico aggiornato = fromDTO(dto, corso);
        aggiornato.setId(id);
        MaterialeDidattico salvato = repository.save(aggiornato);
        log.info("Materiale didattico aggiornato con ID {}: {}", id, salvato.getTitolo());
        return toDTO(salvato);
    }

    public void elimina(Long id) {
        if (!repository.existsById(id)) {
            log.warn("Tentativo di eliminazione fallito: materiale con ID {} non trovato", id);
            throw new ResourceNotFoundException("Materiale didattico da eliminare non trovato con ID: " + id);
        }

        repository.deleteById(id);
        log.info("Materiale didattico eliminato con ID {}", id);
    }
}
