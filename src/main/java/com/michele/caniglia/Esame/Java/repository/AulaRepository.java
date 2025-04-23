package com.michele.caniglia.Esame.Java.repository;

import com.michele.caniglia.Esame.Java.model.Aula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AulaRepository extends JpaRepository<Aula, Long> {
    Optional<Aula> findByNome(String nome);
}

