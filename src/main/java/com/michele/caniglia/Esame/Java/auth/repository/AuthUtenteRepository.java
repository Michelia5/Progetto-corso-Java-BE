package com.michele.caniglia.Esame.Java.auth.repository;

import com.michele.caniglia.Esame.Java.auth.model.AuthUtente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUtenteRepository extends JpaRepository<AuthUtente, Long> {
    Optional<AuthUtente> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    Optional<AuthUtente> findByEmail(String email);
}

