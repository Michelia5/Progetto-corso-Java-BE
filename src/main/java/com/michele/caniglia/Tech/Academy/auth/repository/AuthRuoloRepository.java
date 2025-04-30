package com.michele.caniglia.Tech.Academy.auth.repository;

import com.michele.caniglia.Tech.Academy.auth.model.AuthRuolo;
import com.michele.caniglia.Tech.Academy.auth.model.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRuoloRepository extends JpaRepository<AuthRuolo, Integer> {
    Optional<AuthRuolo> findByNome(ERole nome);

    boolean existsByNome(ERole nome);
}


