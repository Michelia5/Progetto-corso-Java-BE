package com.michele.caniglia.Esame.Java.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "percorsi_formativi")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PercorsoFormativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descrizione;

    @OneToMany(mappedBy = "percorsoFormativo")
    private List<Corso> corsi;
}
