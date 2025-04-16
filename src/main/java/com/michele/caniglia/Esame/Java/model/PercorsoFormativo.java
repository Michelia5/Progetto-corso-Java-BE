package com.michele.caniglia.Esame.Java.model;

import jakarta.persistence.*;
import lombok.*;

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
}
