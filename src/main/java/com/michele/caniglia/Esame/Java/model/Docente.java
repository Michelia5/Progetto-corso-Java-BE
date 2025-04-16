package com.michele.caniglia.Esame.Java.model;

import jakarta.persistence.*;
import lombok.*;

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
}
