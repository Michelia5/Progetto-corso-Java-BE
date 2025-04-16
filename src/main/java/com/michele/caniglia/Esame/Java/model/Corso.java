package com.michele.caniglia.Esame.Java.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "corsi")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Corso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private int durataOre;

    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Docente docente;

    @ManyToOne
    @JoinColumn(name = "aula_id")
    private Aula aula;

    @ManyToOne
    @JoinColumn(name = "percorso_formativo_id")
    private PercorsoFormativo percorsoFormativo;

    // @OneToMany(mappedBy = "corso") -> da aggiungere quando creiamo MaterialeDidattico
}
