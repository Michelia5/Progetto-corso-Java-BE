package com.michele.caniglia.Esame.Java.repository;

import com.michele.caniglia.Esame.Java.model.Corso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CorsoRepository extends JpaRepository<Corso, Long> {
    Optional<Corso> findByNome(String nome);

}
