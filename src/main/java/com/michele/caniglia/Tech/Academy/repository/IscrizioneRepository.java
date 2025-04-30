package com.michele.caniglia.Tech.Academy.repository;

import com.michele.caniglia.Tech.Academy.model.Corso;
import com.michele.caniglia.Tech.Academy.model.Iscrizione;
import com.michele.caniglia.Tech.Academy.model.Studente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IscrizioneRepository extends JpaRepository<Iscrizione, Long> {
    List<Iscrizione> findByStudenteId(Long studenteId);
    Optional<Iscrizione> findByStudenteAndCorso(Studente studente, Corso corso);

}
