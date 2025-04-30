package com.michele.caniglia.Tech.Academy.model;

import com.michele.caniglia.Tech.Academy.auth.model.AuthUtente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


// Il pattern Builder è utilizzato per creare oggetti Studente in modo leggibile e flessibile.
// È abilitato da Lombok con l'annotazione @Builder.


@Entity
@Table(name = "studenti")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Studente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataNascita;

    @OneToMany(mappedBy = "studente")
    private List<Iscrizione> iscrizioni;

    @OneToOne
    @JoinColumn(name = "auth_utente_id")
    private AuthUtente authUtente;
}
