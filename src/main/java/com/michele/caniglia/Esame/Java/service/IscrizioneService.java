package com.michele.caniglia.Esame.Java.service;

import com.michele.caniglia.Esame.Java.dto.IscrizioneDTO;
import com.michele.caniglia.Esame.Java.model.Corso;
import com.michele.caniglia.Esame.Java.model.Iscrizione;
import com.michele.caniglia.Esame.Java.model.Studente;
import com.michele.caniglia.Esame.Java.repository.CorsoRepository;
import com.michele.caniglia.Esame.Java.repository.IscrizioneRepository;
import com.michele.caniglia.Esame.Java.repository.StudenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.michele.caniglia.Esame.Java.dto.IscrizioneMapper.*;

@Service
@RequiredArgsConstructor
public class IscrizioneService {

    private final IscrizioneRepository repository;
    private final StudenteRepository studenteRepository;
    private final CorsoRepository corsoRepository;

    public IscrizioneDTO crea(IscrizioneDTO dto) {
        Studente s = studenteRepository.findById(dto.getStudenteId()).orElse(null);
        Corso c = corsoRepository.findById(dto.getCorsoId()).orElse(null);
        return toDTO(repository.save(fromDTO(dto, s, c)));
    }

    public List<IscrizioneDTO> getAll() {
        List<IscrizioneDTO> list = new ArrayList<>();
        for (Iscrizione i : repository.findAll()) {
            list.add(toDTO(i));
        }
        return list;
    }

    public IscrizioneDTO getById(Long id) {
        return toDTO(repository.findById(id).orElse(null));
    }

    public IscrizioneDTO aggiorna(Long id, IscrizioneDTO dto) {
        if (!repository.existsById(id)) return null;
        Studente s = studenteRepository.findById(dto.getStudenteId()).orElse(null);
        Corso c = corsoRepository.findById(dto.getCorsoId()).orElse(null);
        Iscrizione aggiornata = fromDTO(dto, s, c);
        aggiornata.setId(id);
        return toDTO(repository.save(aggiornata));
    }

    public void elimina(Long id) {
        repository.deleteById(id);
    }
}
