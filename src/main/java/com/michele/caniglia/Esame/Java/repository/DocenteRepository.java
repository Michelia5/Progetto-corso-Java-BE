package com.michele.caniglia.Esame.Java.repository;

import com.michele.caniglia.Esame.Java.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocenteRepository extends JpaRepository<Docente, Long> {
    Optional<Docente> findByEmail(String email);
}

