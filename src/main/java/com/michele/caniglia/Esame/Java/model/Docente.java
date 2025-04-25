package com.michele.caniglia.Esame.Java.model;

import com.michele.caniglia.Esame.Java.auth.model.AuthUtente;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "docenti")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cognome;
    private String email;

    @OneToMany(mappedBy = "docente")
    private List<Corso> corsi;

    @OneToOne
    @JoinColumn(name = "auth_utente_id")
    private AuthUtente authUtente;
}
