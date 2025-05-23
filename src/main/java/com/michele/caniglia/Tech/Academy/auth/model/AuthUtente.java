package com.michele.caniglia.Tech.Academy.auth.model;

import com.michele.caniglia.Tech.Academy.model.Docente;
import com.michele.caniglia.Tech.Academy.model.Studente;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "utenti")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUtente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "utente_ruoli",
            joinColumns = @JoinColumn(name = "utente_id"),
            inverseJoinColumns = @JoinColumn(name = "ruolo_id"))
    @Builder.Default
    private Set<AuthRuolo> ruoli = new HashSet<>();

    @OneToOne(mappedBy = "authUtente")
    private Studente studente;

    @OneToOne(mappedBy = "authUtente")
    private Docente docente;
}
