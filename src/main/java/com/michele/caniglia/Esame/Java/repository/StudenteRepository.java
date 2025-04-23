package com.michele.caniglia.Esame.Java.repository;

import com.michele.caniglia.Esame.Java.model.Studente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


// Questo è un DAO (Data Access Object) che segue il Repository Pattern.
// Fornisce metodi CRUD per accedere ai dati dell'entità Studente.


@Repository
public interface StudenteRepository extends JpaRepository<Studente, Long> {
    Optional<Studente> findByEmail(String email);
}
