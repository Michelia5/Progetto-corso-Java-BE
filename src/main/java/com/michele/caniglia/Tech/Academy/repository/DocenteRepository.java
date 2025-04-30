package com.michele.caniglia.Tech.Academy.repository;

import com.michele.caniglia.Tech.Academy.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocenteRepository extends JpaRepository<Docente, Long> {
    Optional<Docente> findByEmail(String email);
}

