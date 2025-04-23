package com.michele.caniglia.Esame.Java.util;

import com.michele.caniglia.Esame.Java.model.*;
import com.michele.caniglia.Esame.Java.model.MaterialeDidattico.Tipo;
import com.michele.caniglia.Esame.Java.model.Iscrizione.StatoIscrizione;
import com.michele.caniglia.Esame.Java.repository.*;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final StudenteRepository studenteRepository;
    private final DocenteRepository docenteRepository;
    private final AulaRepository aulaRepository;
    private final PercorsoFormativoRepository percorsoFormativoRepository;
    private final CorsoRepository corsoRepository;
    private final MaterialeDidatticoRepository materialeDidatticoRepository;
    private final EsameRepository esameRepository;
    private final IscrizioneRepository iscrizioneRepository;

    @Override
    public void run(String... args) {

        // STUDENTI
        Studente s1 = studenteRepository.save(Studente.builder().nome("Luca").cognome("Rossi").email("luca.rossi@example.com").dataNascita(LocalDate.of(2000, 5, 12)).build());
        Studente s2 = studenteRepository.save(Studente.builder().nome("Giulia").cognome("Verdi").email("giulia.verdi@example.com").dataNascita(LocalDate.of(1999, 10, 23)).build());
        Studente s3 = studenteRepository.save(Studente.builder().nome("Marco").cognome("Bianchi").email("marco.bianchi@example.com").dataNascita(LocalDate.of(2001, 3, 30)).build());
        Studente s4 = studenteRepository.save(Studente.builder().nome("Chiara").cognome("Ferrari").email("chiara.ferrari@example.com").dataNascita(LocalDate.of(1998, 7, 9)).build());
        Studente s5 = studenteRepository.save(Studente.builder().nome("Davide").cognome("Russo").email("davide.russo@example.com").dataNascita(LocalDate.of(2002, 1, 15)).build());


        // DOCENTI
        Docente d1 = docenteRepository.save(Docente.builder().nome("Anna").cognome("Colombo").email("anna.colombo@example.com").build());
        Docente d2 = docenteRepository.save(Docente.builder().nome("Roberto").cognome("De Luca").email("roberto.deluca@example.com").build());
        Docente d3 = docenteRepository.save(Docente.builder().nome("Elisa").cognome("Galli").email("elisa.galli@example.com").build());


        // AULE
        Aula aula1 = aulaRepository.save(Aula.builder().nome("Aula 101").capienza(25).piano(1).build());
        Aula aula2 = aulaRepository.save(Aula.builder().nome("Laboratorio Networking").capienza(15).piano(0).build());
        Aula aula3 = aulaRepository.save(Aula.builder().nome("Aula 204").capienza(20).piano(2).build());


        // PERCORSI FORMATIVI
        PercorsoFormativo p1 = percorsoFormativoRepository.save(PercorsoFormativo.builder().nome("Full Stack Developer").descrizione("Frontend + Backend con Java e React").build());
        PercorsoFormativo p2 = percorsoFormativoRepository.save(PercorsoFormativo.builder().nome("Cybersecurity Basics").descrizione("Fondamenti di sicurezza informatica").build());
        PercorsoFormativo p3 = percorsoFormativoRepository.save(PercorsoFormativo.builder().nome("DevOps Junior").descrizione("Contenitori, CI/CD e strumenti DevOps").build());


        // CORSI
        Corso c1 = corsoRepository.save(Corso.builder()
                .nome("Spring Boot Avanzato")
                .dataInizio(LocalDate.now().minusDays(10))
                .dataFine(LocalDate.now().plusDays(20))
                .durataOre(40)
                .docente(d1)
                .aula(aula1)
                .percorsoFormativo(p1)
                .build());

        Corso c2 = corsoRepository.save(Corso.builder()
                .nome("Networking e CyberSecurity")
                .dataInizio(LocalDate.now().minusDays(30))
                .dataFine(LocalDate.now().minusDays(5))
                .durataOre(30)
                .docente(d2)
                .aula(aula2)
                .percorsoFormativo(p2)
                .build());

        Corso c3 = corsoRepository.save(Corso.builder()
                .nome("Java Base")
                .dataInizio(LocalDate.now().minusDays(15))
                .dataFine(LocalDate.now().plusDays(15))
                .durataOre(50)
                .docente(d3)
                .aula(aula3)
                .percorsoFormativo(p1)
                .build());

        Corso c4 = corsoRepository.save(Corso.builder()
                .nome("Linux & Bash")
                .dataInizio(LocalDate.now().minusDays(25))
                .dataFine(LocalDate.now().plusDays(5))
                .durataOre(35)
                .docente(d3)
                .aula(aula2)
                .percorsoFormativo(p3)
                .build());


        // MATERIALI DIDATTICI
        materialeDidatticoRepository.save(MaterialeDidattico.builder()
                .titolo("Slide Spring")
                .tipo(Tipo.SLIDE)
                .descrizione("Materiale per Spring Boot")
                .corso(c1)
                .build());

        materialeDidatticoRepository.save(MaterialeDidattico.builder()
                .titolo("Dispensa Sicurezza")
                .tipo(Tipo.PDF)
                .descrizione("Fondamenti di sicurezza")
                .corso(c2)
                .build());

        materialeDidatticoRepository.save(MaterialeDidattico.builder()
                .titolo("Esercizi Java")
                .tipo(Tipo.PDF)
                .descrizione("Esercizi base con soluzioni")
                .corso(c3)
                .build());

        materialeDidatticoRepository.save(MaterialeDidattico.builder()
                .titolo("Comandi Bash")
                .tipo(Tipo.SLIDE)
                .descrizione("Comandi principali Linux e scripting")
                .corso(c4)
                .build());


        // ESAMI
        esameRepository.save(Esame.builder()
                .nome("Verifica Spring")
                .data(LocalDate.now().plusDays(7))
                .corso(c1)
                .build());

        esameRepository.save(Esame.builder()
                .nome("Esame Finale Networking")
                .data(LocalDate.now().plusDays(15))
                .corso(c2)
                .build());

        esameRepository.save(Esame.builder()
                .nome("Test Java Base")
                .data(LocalDate.now().plusDays(15))
                .corso(c3)
                .build());

        esameRepository.save(Esame.builder()
                .nome("Prova Bash")
                .data(LocalDate.now().plusDays(12))
                .corso(c4)
                .build());


        // ISCRIZIONI
        iscrizioneRepository.save(Iscrizione.builder()
                .dataIscrizione(LocalDate.now().minusDays(20))
                .stato(StatoIscrizione.ATTIVA)
                .voto(28)
                .studente(s1)
                .corso(c1)
                .build());

        iscrizioneRepository.save(Iscrizione.builder()
                .dataIscrizione(LocalDate.now().minusDays(35))
                .stato(StatoIscrizione.COMPLETATA)
                .voto(30)
                .studente(s2)
                .corso(c2)
                .build());

        iscrizioneRepository.save(Iscrizione.builder()
                .dataIscrizione(LocalDate.now().minusDays(10))
                .stato(StatoIscrizione.RITIRATA)
                .voto(0)
                .studente(s3)
                .corso(c1)
                .build());

        iscrizioneRepository.save(Iscrizione.builder()
                .dataIscrizione(LocalDate.now().minusDays(18))
                .stato(StatoIscrizione.ATTIVA)
                .voto(27)
                .studente(s4)
                .corso(c3)
                .build());

        iscrizioneRepository.save(Iscrizione.builder()
                .dataIscrizione(LocalDate.now().minusDays(8))
                .stato(StatoIscrizione.ATTIVA)
                .voto(0)
                .studente(s5)
                .corso(c4)
                .build());

        iscrizioneRepository.save(Iscrizione.builder()
                .dataIscrizione(LocalDate.now().minusDays(5))
                .stato(StatoIscrizione.COMPLETATA)
                .voto(25)
                .studente(s1)
                .corso(c3)
                .build());
    }
}
