package com.michele.caniglia.Esame.Java.repository;

import com.michele.caniglia.Esame.Java.model.Iscrizione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IscrizioneRepository extends JpaRepository<Iscrizione, Long> {
    List<Iscrizione> findByStudenteId(Long studenteId);
}
