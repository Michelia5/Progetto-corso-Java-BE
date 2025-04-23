package com.michele.caniglia.Esame.Java.repository;

import com.michele.caniglia.Esame.Java.model.Esame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EsameRepository extends JpaRepository<Esame, Long> {
    Optional<Esame> findByNome(String nome);
}
