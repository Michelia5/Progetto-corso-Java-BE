package com.michele.caniglia.Esame.Java.auth.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ruoli")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRuolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole nome;
}