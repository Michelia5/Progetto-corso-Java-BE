package com.michele.caniglia.Esame.Java.repository;

import com.michele.caniglia.Esame.Java.model.MaterialeDidattico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaterialeDidatticoRepository extends JpaRepository<MaterialeDidattico, Long> {
    Optional<MaterialeDidattico> findByTitolo(String titolo);
}
