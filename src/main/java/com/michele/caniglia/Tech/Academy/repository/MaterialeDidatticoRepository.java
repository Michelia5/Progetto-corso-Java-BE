package com.michele.caniglia.Tech.Academy.repository;

import com.michele.caniglia.Tech.Academy.model.MaterialeDidattico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaterialeDidatticoRepository extends JpaRepository<MaterialeDidattico, Long> {
    Optional<MaterialeDidattico> findByTitolo(String titolo);
}
