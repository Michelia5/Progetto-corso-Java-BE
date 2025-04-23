package com.michele.caniglia.Esame.Java.repository;

import com.michele.caniglia.Esame.Java.model.PercorsoFormativo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PercorsoFormativoRepository extends JpaRepository<PercorsoFormativo, Long> {
    Optional<PercorsoFormativo> findByNome(String nome);

}
