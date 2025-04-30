package com.michele.caniglia.Tech.Academy.auth.repository;

import com.michele.caniglia.Tech.Academy.auth.model.AuthUtente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUtenteRepository extends JpaRepository<AuthUtente, Long> {
    Optional<AuthUtente> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    Optional<AuthUtente> findByEmail(String email);
}

