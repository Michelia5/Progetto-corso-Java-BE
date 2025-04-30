package com.michele.caniglia.Tech.Academy.service;

import com.michele.caniglia.Tech.Academy.dto.CorsoDTO;
import com.michele.caniglia.Tech.Academy.exception.ResourceNotFoundException;
import com.michele.caniglia.Tech.Academy.model.Corso;
import com.michele.caniglia.Tech.Academy.model.Aula;
import com.michele.caniglia.Tech.Academy.model.Docente;
import com.michele.caniglia.Tech.Academy.model.PercorsoFormativo;
import com.michele.caniglia.Tech.Academy.repository.AulaRepository;
import com.michele.caniglia.Tech.Academy.repository.CorsoRepository;
import com.michele.caniglia.Tech.Academy.repository.DocenteRepository;
import com.michele.caniglia.Tech.Academy.repository.PercorsoFormativoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.michele.caniglia.Tech.Academy.dto.mapper.CorsoMapper.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CorsoService {

    private final CorsoRepository corsoRepository;
    private final DocenteRepository docenteRepository;
    private final AulaRepository aulaRepository;
    private final PercorsoFormativoRepository percorsoRepository;

    public CorsoDTO crea(CorsoDTO dto) {
        Docente docente = docenteRepository.findById(dto.getDocenteId())
                .orElseThrow(() -> new ResourceNotFoundException("Docente non trovato con ID: " + dto.getDocenteId()));

        Aula aula = aulaRepository.findById(dto.getAulaId())
                .orElseThrow(() -> new ResourceNotFoundException("Aula non trovata con ID: " + dto.getAulaId()));

        PercorsoFormativo percorso = percorsoRepository.findById(dto.getPercorsoFormativoId())
                .orElseThrow(() -> new ResourceNotFoundException("Percorso formativo non trovato con ID: " + dto.getPercorsoFormativoId()));

        Corso corso = fromDTO(dto, docente, aula, percorso);
        Corso salvato = corsoRepository.save(corso);
        log.info("Creato nuovo corso: {}", corso.getNome());
        return toDTO(salvato);
    }

    public List<CorsoDTO> getAll() {
        List<CorsoDTO> list = new ArrayList<>();
        for (Corso c : corsoRepository.findAll()) {
            list.add(toDTO(c));
        }
        log.info("Restituiti {} corsi", list.size());
        return list;
    }

    public CorsoDTO getById(Long id) {
        Corso corso = corsoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Corso non trovato con ID: " + id));
        log.info("Corso trovato con ID {}: {}", id, corso.getNome());
        return toDTO(corso);
    }

    public CorsoDTO aggiorna(Long id, CorsoDTO dto) {
        if (!corsoRepository.existsById(id)) {
            log.warn("Aggiornamento fallito: corso con ID {} non trovato", id);
            throw new ResourceNotFoundException("Corso da aggiornare non trovato con ID: " + id);
        }

        Docente docente = docenteRepository.findById(dto.getDocenteId())
                .orElseThrow(() -> new ResourceNotFoundException("Docente non trovato con ID: " + dto.getDocenteId()));

        Aula aula = aulaRepository.findById(dto.getAulaId())
                .orElseThrow(() -> new ResourceNotFoundException("Aula non trovata con ID: " + dto.getAulaId()));

        PercorsoFormativo percorso = percorsoRepository.findById(dto.getPercorsoFormativoId())
                .orElseThrow(() -> new ResourceNotFoundException("Percorso formativo non trovato con ID: " + dto.getPercorsoFormativoId()));

        Corso aggiornato = fromDTO(dto, docente, aula, percorso);
        aggiornato.setId(id);
        Corso salvato = corsoRepository.save(aggiornato);
        log.info("Corso aggiornato con ID {}: {}", id, salvato.getNome());
        return toDTO(salvato);
    }

    public void elimina(Long id) {
        if (!corsoRepository.existsById(id)) {
            log.warn("Tentativo di eliminazione fallito: corso con ID {} non trovato", id);
            throw new ResourceNotFoundException("Corso da eliminare non trovato con ID: " + id);
        }
        corsoRepository.deleteById(id);
        log.info("Corso eliminato con ID {}", id);
    }
}
