package com.michele.caniglia.Tech.Academy.repository;

import com.michele.caniglia.Tech.Academy.model.Corso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CorsoRepository extends JpaRepository<Corso, Long> {
    Optional<Corso> findByNome(String nome);

}
