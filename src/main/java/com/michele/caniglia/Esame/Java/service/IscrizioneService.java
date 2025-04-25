package com.michele.caniglia.Esame.Java.service;

import com.michele.caniglia.Esame.Java.dto.IscrizioneDTO;
import com.michele.caniglia.Esame.Java.exception.ResourceNotFoundException;
import com.michele.caniglia.Esame.Java.model.Corso;
import com.michele.caniglia.Esame.Java.model.Iscrizione;
import com.michele.caniglia.Esame.Java.model.Studente;
import com.michele.caniglia.Esame.Java.repository.CorsoRepository;
import com.michele.caniglia.Esame.Java.repository.IscrizioneRepository;
import com.michele.caniglia.Esame.Java.repository.StudenteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.michele.caniglia.Esame.Java.dto.mapper.IscrizioneMapper.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class IscrizioneService {

    private final IscrizioneRepository repository;
    private final StudenteRepository studenteRepository;
    private final CorsoRepository corsoRepository;

    public IscrizioneDTO crea(IscrizioneDTO dto) {
        Studente studente = studenteRepository.findById(dto.getStudenteId())
                .orElseThrow(() -> new ResourceNotFoundException("Studente non trovato con ID: " + dto.getStudenteId()));
        Corso corso = corsoRepository.findById(dto.getCorsoId())
                .orElseThrow(() -> new ResourceNotFoundException("Corso non trovato con ID: " + dto.getCorsoId()));

        Iscrizione salvata = repository.save(fromDTO(dto, studente, corso));
        log.info("Iscrizione creata per studente {} al corso {}", studente.getId(), corso.getId());
        return toDTO(salvata);
    }

    public List<IscrizioneDTO> getAll() {
        List<IscrizioneDTO> list = new ArrayList<>();
        for (Iscrizione i : repository.findAll()) {
            list.add(toDTO(i));
        }
        log.info("Restituite {} iscrizioni", list.size());
        return list;
    }

    public IscrizioneDTO getById(Long id) {
        Iscrizione iscrizione = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Iscrizione non trovata con ID: " + id));
        log.info("Iscrizione trovata con ID {}", id);
        return toDTO(iscrizione);
    }

    public IscrizioneDTO aggiorna(Long id, IscrizioneDTO dto) {
        if (!repository.existsById(id)) {
            log.warn("Aggiornamento fallito: iscrizione con ID {} non trovata", id);
            throw new ResourceNotFoundException("Iscrizione da aggiornare non trovata con ID: " + id);
        }

        Studente studente = studenteRepository.findById(dto.getStudenteId())
                .orElseThrow(() -> new ResourceNotFoundException("Studente non trovato con ID: " + dto.getStudenteId()));
        Corso corso = corsoRepository.findById(dto.getCorsoId())
                .orElseThrow(() -> new ResourceNotFoundException("Corso non trovato con ID: " + dto.getCorsoId()));

        Iscrizione aggiornata = fromDTO(dto, studente, corso);
        aggiornata.setId(id);
        Iscrizione salvata = repository.save(aggiornata);
        log.info("Iscrizione aggiornata con ID {}", id);
        return toDTO(salvata);
    }

    public void elimina(Long id) {
        if (!repository.existsById(id)) {
            log.warn("Tentativo di eliminazione fallito: iscrizione con ID {} non trovata", id);
            throw new ResourceNotFoundException("Iscrizione da eliminare non trovata con ID: " + id);
        }
        repository.deleteById(id);
        log.info("Iscrizione eliminata con ID {}", id);
    }
}
