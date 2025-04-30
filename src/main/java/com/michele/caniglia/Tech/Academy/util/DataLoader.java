package com.michele.caniglia.Tech.Academy.util;

import com.michele.caniglia.Tech.Academy.model.*;
import com.michele.caniglia.Tech.Academy.repository.*;
import com.michele.caniglia.Tech.Academy.model.*;
import com.michele.caniglia.Tech.Academy.model.MaterialeDidattico.Tipo;
import com.michele.caniglia.Tech.Academy.model.Iscrizione.StatoIscrizione;

import com.michele.caniglia.Tech.Academy.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Order(2)
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
        Studente s1 = studenteRepository.findByEmail("luca.rossi@example.com").orElseGet(() ->
                studenteRepository.save(Studente.builder().nome("Luca").cognome("Rossi").email("luca.rossi@example.com").dataNascita(LocalDate.of(2000, 5, 12)).build()));
        Studente s2 = studenteRepository.findByEmail("giulia.verdi@example.com").orElseGet(() ->
                studenteRepository.save(Studente.builder().nome("Giulia").cognome("Verdi").email("giulia.verdi@example.com").dataNascita(LocalDate.of(1999, 10, 23)).build()));
        Studente s3 = studenteRepository.findByEmail("marco.bianchi@example.com").orElseGet(() ->
                studenteRepository.save(Studente.builder().nome("Marco").cognome("Bianchi").email("marco.bianchi@example.com").dataNascita(LocalDate.of(2001, 3, 30)).build()));
        Studente s4 = studenteRepository.findByEmail("chiara.ferrari@example.com").orElseGet(() ->
                studenteRepository.save(Studente.builder().nome("Chiara").cognome("Ferrari").email("chiara.ferrari@example.com").dataNascita(LocalDate.of(1998, 7, 9)).build()));
        Studente s5 = studenteRepository.findByEmail("davide.russo@example.com").orElseGet(() ->
                studenteRepository.save(Studente.builder().nome("Davide").cognome("Russo").email("davide.russo@example.com").dataNascita(LocalDate.of(2002, 1, 15)).build()));


        // DOCENTI
        Docente d1 = docenteRepository.findByEmail("anna.colombo@example.com").orElseGet(() ->
                docenteRepository.save(Docente.builder().nome("Anna").cognome("Colombo").email("anna.colombo@example.com").build()));
        Docente d2 = docenteRepository.findByEmail("roberto.deluca@example.com").orElseGet(() ->
                docenteRepository.save(Docente.builder().nome("Roberto").cognome("De Luca").email("roberto.deluca@example.com").build()));
        Docente d3 = docenteRepository.findByEmail("elisa.galli@example.com").orElseGet(() ->
                docenteRepository.save(Docente.builder().nome("Elisa").cognome("Galli").email("elisa.galli@example.com").build()));


        // AULE
        Aula aula1 = aulaRepository.findByNome("Aula 101").orElseGet(() ->
                aulaRepository.save(Aula.builder().nome("Aula 101").capienza(25).piano(1).build()));
        Aula aula2 = aulaRepository.findByNome("Laboratorio Networking").orElseGet(() ->
                aulaRepository.save(Aula.builder().nome("Laboratorio Networking").capienza(15).piano(0).build()));
        Aula aula3 = aulaRepository.findByNome("Aula 204").orElseGet(() ->
                aulaRepository.save(Aula.builder().nome("Aula 204").capienza(20).piano(2).build()));


        // PERCORSI FORMATIVI
        PercorsoFormativo p1 = percorsoFormativoRepository.findByNome("Full Stack Developer").orElseGet(() ->
                percorsoFormativoRepository.save(PercorsoFormativo.builder().nome("Full Stack Developer").descrizione("Frontend + Backend con Java e React").build()));
        PercorsoFormativo p2 = percorsoFormativoRepository.findByNome("Cybersecurity Basics").orElseGet(() ->
                percorsoFormativoRepository.save(PercorsoFormativo.builder().nome("Cybersecurity Basics").descrizione("Fondamenti di sicurezza informatica").build()));
        PercorsoFormativo p3 = percorsoFormativoRepository.findByNome("DevOps Junior").orElseGet(() ->
                percorsoFormativoRepository.save(PercorsoFormativo.builder().nome("DevOps Junior").descrizione("Contenitori, CI/CD e strumenti DevOps").build()));


        // CORSI
        Corso c1 = corsoRepository.findByNome("Spring Boot Avanzato").orElseGet(() ->
                corsoRepository.save(Corso.builder()
                        .nome("Spring Boot Avanzato")
                        .dataInizio(LocalDate.now().minusDays(10))
                        .dataFine(LocalDate.now().plusDays(20))
                        .durataOre(40)
                        .docente(d1)
                        .aula(aula1)
                        .percorsoFormativo(p1)
                        .build()));

        Corso c2 = corsoRepository.findByNome("Networking e CyberSecurity").orElseGet(() ->
                corsoRepository.save(Corso.builder()
                        .nome("Networking e CyberSecurity")
                        .dataInizio(LocalDate.now().minusDays(30))
                        .dataFine(LocalDate.now().minusDays(5))
                        .durataOre(30)
                        .docente(d2)
                        .aula(aula2)
                        .percorsoFormativo(p2)
                        .build()));

        Corso c3 = corsoRepository.findByNome("Java Base").orElseGet(() ->
                corsoRepository.save(Corso.builder()
                        .nome("Java Base")
                        .dataInizio(LocalDate.now().minusDays(15))
                        .dataFine(LocalDate.now().plusDays(15))
                        .durataOre(50)
                        .docente(d3)
                        .aula(aula3)
                        .percorsoFormativo(p1)
                        .build()));

        Corso c4 = corsoRepository.findByNome("Linux & Bash").orElseGet(() ->
                corsoRepository.save(Corso.builder()
                        .nome("Linux & Bash")
                        .dataInizio(LocalDate.now().minusDays(25))
                        .dataFine(LocalDate.now().plusDays(5))
                        .durataOre(35)
                        .docente(d3)
                        .aula(aula2)
                        .percorsoFormativo(p3)
                        .build()));



        // MATERIALI DIDATTICI
        materialeDidatticoRepository.findByTitolo("Slide Spring").orElseGet(() ->
                materialeDidatticoRepository.save(MaterialeDidattico.builder()
                        .titolo("Slide Spring")
                        .tipo(Tipo.SLIDE)
                        .descrizione("Materiale per Spring Boot")
                        .corso(c1)
                        .build()));

        materialeDidatticoRepository.findByTitolo("Dispensa Sicurezza").orElseGet(() ->
                materialeDidatticoRepository.save(MaterialeDidattico.builder()
                        .titolo("Dispensa Sicurezza")
                        .tipo(Tipo.PDF)
                        .descrizione("Fondamenti di sicurezza")
                        .corso(c2)
                        .build()));

        materialeDidatticoRepository.findByTitolo("Esercizi Java").orElseGet(() ->
                materialeDidatticoRepository.save(MaterialeDidattico.builder()
                        .titolo("Esercizi Java")
                        .tipo(Tipo.PDF)
                        .descrizione("Esercizi base con soluzioni")
                        .corso(c3)
                        .build()));

        materialeDidatticoRepository.findByTitolo("Comandi Bash").orElseGet(() ->
                materialeDidatticoRepository.save(MaterialeDidattico.builder()
                        .titolo("Comandi Bash")
                        .tipo(Tipo.SLIDE)
                        .descrizione("Comandi principali Linux e scripting")
                        .corso(c4)
                        .build()));


        // ESAMI
        esameRepository.findByNome("Verifica Spring").orElseGet(() ->
                esameRepository.save(Esame.builder()
                        .nome("Verifica Spring")
                        .data(LocalDate.now().plusDays(7))
                        .corso(c1)
                        .build()));

        esameRepository.findByNome("Esame Finale Networking").orElseGet(() ->
                esameRepository.save(Esame.builder()
                        .nome("Esame Finale Networking")
                        .data(LocalDate.now().plusDays(15))
                        .corso(c2)
                        .build()));

        esameRepository.findByNome("Test Java Base").orElseGet(() ->
                esameRepository.save(Esame.builder()
                        .nome("Test Java Base")
                        .data(LocalDate.now().plusDays(15))
                        .corso(c3)
                        .build()));

        esameRepository.findByNome("Prova Bash").orElseGet(() ->
                esameRepository.save(Esame.builder()
                        .nome("Prova Bash")
                        .data(LocalDate.now().plusDays(12))
                        .corso(c4)
                        .build()));


        // ISCRIZIONI
        iscrizioneRepository.findByStudenteAndCorso(s1, c1).orElseGet(() ->
                iscrizioneRepository.save(Iscrizione.builder()
                        .dataIscrizione(LocalDate.now().minusDays(20))
                        .stato(StatoIscrizione.ATTIVA)
                        .voto(28)
                        .studente(s1)
                        .corso(c1)
                        .build()));

        iscrizioneRepository.findByStudenteAndCorso(s2, c2).orElseGet(() ->
                iscrizioneRepository.save(Iscrizione.builder()
                        .dataIscrizione(LocalDate.now().minusDays(35))
                        .stato(StatoIscrizione.COMPLETATA)
                        .voto(30)
                        .studente(s2)
                        .corso(c2)
                        .build()));

        iscrizioneRepository.findByStudenteAndCorso(s3, c1).orElseGet(() ->
                iscrizioneRepository.save(Iscrizione.builder()
                        .dataIscrizione(LocalDate.now().minusDays(10))
                        .stato(StatoIscrizione.RITIRATA)
                        .voto(0)
                        .studente(s3)
                        .corso(c1)
                        .build()));

        iscrizioneRepository.findByStudenteAndCorso(s4, c3).orElseGet(() ->
                iscrizioneRepository.save(Iscrizione.builder()
                        .dataIscrizione(LocalDate.now().minusDays(18))
                        .stato(StatoIscrizione.ATTIVA)
                        .voto(27)
                        .studente(s4)
                        .corso(c3)
                        .build()));

        iscrizioneRepository.findByStudenteAndCorso(s5, c4).orElseGet(() ->
                iscrizioneRepository.save(Iscrizione.builder()
                        .dataIscrizione(LocalDate.now().minusDays(8))
                        .stato(StatoIscrizione.ATTIVA)
                        .voto(0)
                        .studente(s5)
                        .corso(c4)
                        .build()));

        iscrizioneRepository.findByStudenteAndCorso(s1, c3).orElseGet(() ->
                iscrizioneRepository.save(Iscrizione.builder()
                        .dataIscrizione(LocalDate.now().minusDays(5))
                        .stato(StatoIscrizione.COMPLETATA)
                        .voto(25)
                        .studente(s1)
                        .corso(c3)
                        .build()));

    }
}
