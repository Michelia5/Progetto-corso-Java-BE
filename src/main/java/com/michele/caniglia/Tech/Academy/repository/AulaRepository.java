package com.michele.caniglia.Tech.Academy.repository;

import com.michele.caniglia.Tech.Academy.model.Aula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AulaRepository extends JpaRepository<Aula, Long> {
    Optional<Aula> findByNome(String nome);
}

