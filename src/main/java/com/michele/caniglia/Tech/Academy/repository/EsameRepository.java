package com.michele.caniglia.Tech.Academy.repository;

import com.michele.caniglia.Tech.Academy.model.Esame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EsameRepository extends JpaRepository<Esame, Long> {
    Optional<Esame> findByNome(String nome);
}
