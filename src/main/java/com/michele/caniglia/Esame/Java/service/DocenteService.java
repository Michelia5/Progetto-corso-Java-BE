package com.michele.caniglia.Esame.Java.service;

import com.michele.caniglia.Esame.Java.dto.DocenteDTO;
import com.michele.caniglia.Esame.Java.model.Docente;
import com.michele.caniglia.Esame.Java.repository.DocenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.michele.caniglia.Esame.Java.dto.mapper.DocenteMapper.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocenteService {

    private final DocenteRepository repository;

    public DocenteDTO crea(DocenteDTO dto) {
        Docente nuovo = repository.save(fromDTO(dto));
        return toDTO(nuovo);
    }

    public List<DocenteDTO> getAll() {
        List<DocenteDTO> lista = new ArrayList<>();
        for (Docente d : repository.findAll()) {
            lista.add(toDTO(d));
        }
        return lista;
    }

    public DocenteDTO getById(Long id) {
        Docente d = repository.findById(id).orElse(null);
        return toDTO(d);
    }

    public DocenteDTO aggiorna(Long id, DocenteDTO dto) {
        if (!repository.existsById(id)) return null;
        Docente aggiornato = fromDTO(dto);
        aggiornato.setId(id);
        return toDTO(repository.save(aggiornato));
    }

    public void elimina(Long id) {
        repository.deleteById(id);
    }
}
