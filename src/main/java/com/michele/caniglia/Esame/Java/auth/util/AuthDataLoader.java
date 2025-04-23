package com.michele.caniglia.Esame.Java.auth.util;

import com.michele.caniglia.Esame.Java.auth.model.AuthRuolo;
import com.michele.caniglia.Esame.Java.auth.model.AuthUtente;
import com.michele.caniglia.Esame.Java.auth.model.ERole;
import com.michele.caniglia.Esame.Java.auth.repository.AuthRuoloRepository;
import com.michele.caniglia.Esame.Java.auth.repository.AuthUtenteRepository;
import com.michele.caniglia.Esame.Java.model.Docente;
import com.michele.caniglia.Esame.Java.model.Studente;
import com.michele.caniglia.Esame.Java.repository.DocenteRepository;
import com.michele.caniglia.Esame.Java.repository.StudenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
@Order(1)
@RequiredArgsConstructor
public class AuthDataLoader implements CommandLineRunner {

    private final AuthRuoloRepository ruoloRepository;
    private final AuthUtenteRepository utenteRepository;
    private final StudenteRepository studenteRepository;
    private final DocenteRepository docenteRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        // Crea i ruoli se mancanti
        for (ERole ruolo : ERole.values()) {
            ruoloRepository.findByNome(ruolo).orElseGet(() ->
                    ruoloRepository.save(AuthRuolo.builder().nome(ruolo).build()));
        }

        // Studenti
        assegnaUtenteAStudente("luca.rossi@example.com", "luca", "luca123");
        assegnaUtenteAStudente("giulia.verdi@example.com", "giulia", "giulia123");
        assegnaUtenteAStudente("marco.bianchi@example.com", "marco", "marco123");
        assegnaUtenteAStudente("chiara.ferrari@example.com", "chiara", "chiara123");
        assegnaUtenteAStudente("davide.russo@example.com", "davide", "davide123");

        // Docenti
        assegnaUtenteADocente("anna.colombo@example.com", "anna", "anna123");
        assegnaUtenteADocente("roberto.deluca@example.com", "roberto", "roberto123");
        assegnaUtenteADocente("elisa.galli@example.com", "elisa", "elisa123");

        // Admin (solo utente, nessun collegamento)
        creaUtenteSeNonEsiste("admin", "admin123", "admin@email.it", Set.of(ERole.ROLE_ADMIN));
    }

    private void assegnaUtenteAStudente(String emailStudente, String username, String password) {
        Optional<Studente> studenteOpt = studenteRepository.findByEmail(emailStudente);
        studenteOpt.ifPresent(studente -> {
            if (studente.getAuthUtente() != null) return; // già associato

            AuthUtente utente = creaUtenteSeNonEsiste(username, password, emailStudente, Set.of(ERole.ROLE_STUDENTE));
            studente.setAuthUtente(utente);
            studenteRepository.save(studente);
        });
    }

    private void assegnaUtenteADocente(String emailDocente, String username, String password) {
        Optional<Docente> docenteOpt = docenteRepository.findByEmail(emailDocente);
        docenteOpt.ifPresent(docente -> {
            if (docente.getAuthUtente() != null) return; // già associato

            AuthUtente utente = creaUtenteSeNonEsiste(username, password, emailDocente, Set.of(ERole.ROLE_DOCENTE));
            docente.setAuthUtente(utente);
            docenteRepository.save(docente);
        });
    }


    private AuthUtente creaUtenteSeNonEsiste(String username, String rawPassword, String email, Set<ERole> ruoliDaAssegnare) {
        return utenteRepository.findByUsername(username)
                .or(() -> utenteRepository.findByEmail(email))
                .orElseGet(() -> {
                    Set<AuthRuolo> ruoli = new HashSet<>();
                    for (ERole r : ruoliDaAssegnare) {
                        ruoloRepository.findByNome(r).ifPresent(ruoli::add);
                    }

                    AuthUtente nuovoUtente = AuthUtente.builder()
                            .username(username)
                            .password(passwordEncoder.encode(rawPassword))
                            .email(email)
                            .ruoli(ruoli)
                            .build();

                    return utenteRepository.save(nuovoUtente);
                });
    }

}
