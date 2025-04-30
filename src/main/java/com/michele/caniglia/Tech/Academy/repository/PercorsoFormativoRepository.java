package com.michele.caniglia.Tech.Academy.repository;

import com.michele.caniglia.Tech.Academy.model.PercorsoFormativo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PercorsoFormativoRepository extends JpaRepository<PercorsoFormativo, Long> {
    Optional<PercorsoFormativo> findByNome(String nome);

}
