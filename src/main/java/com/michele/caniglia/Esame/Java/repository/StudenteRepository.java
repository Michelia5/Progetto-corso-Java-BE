package com.michele.caniglia.Esame.Java.repository;

import com.michele.caniglia.Esame.Java.model.Studente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudenteRepository extends JpaRepository<Studente, Long> {
}
