package com.michele.caniglia.Esame.Java.repository;

import com.michele.caniglia.Esame.Java.model.Corso;
import com.michele.caniglia.Esame.Java.model.Iscrizione;
import com.michele.caniglia.Esame.Java.model.Studente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IscrizioneRepository extends JpaRepository<Iscrizione, Long> {
    List<Iscrizione> findByStudenteId(Long studenteId);
    Optional<Iscrizione> findByStudenteAndCorso(Studente studente, Corso corso);

}
