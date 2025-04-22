package com.michele.caniglia.Esame.Java.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "materiali_didattici")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialeDidattico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titolo;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "corso_id")
    private Corso corso;

    public enum Tipo {
        PDF, LINK, SLIDE
    }
}
