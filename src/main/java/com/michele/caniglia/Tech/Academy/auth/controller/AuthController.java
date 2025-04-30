package com.michele.caniglia.Tech.Academy.auth.controller;

import com.michele.caniglia.Tech.Academy.auth.model.AuthRuolo;
import com.michele.caniglia.Tech.Academy.auth.model.AuthUtente;
import com.michele.caniglia.Tech.Academy.auth.model.ERole;
import com.michele.caniglia.Tech.Academy.auth.payload.request.LoginRequest;
import com.michele.caniglia.Tech.Academy.auth.payload.request.SignupRequest;
import com.michele.caniglia.Tech.Academy.auth.payload.response.JwtResponse;
import com.michele.caniglia.Tech.Academy.auth.payload.response.MessageResponse;
import com.michele.caniglia.Tech.Academy.auth.repository.AuthRuoloRepository;
import com.michele.caniglia.Tech.Academy.auth.repository.AuthUtenteRepository;
import com.michele.caniglia.Tech.Academy.auth.security.jwt.JwtUtils;
import com.michele.caniglia.Tech.Academy.auth.security.services.UserDetailsImpl;
import com.michele.caniglia.Tech.Academy.model.Docente;
import com.michele.caniglia.Tech.Academy.model.Studente;
import com.michele.caniglia.Tech.Academy.repository.DocenteRepository;
import com.michele.caniglia.Tech.Academy.repository.StudenteRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final StudenteRepository studenteRepository;
    private final DocenteRepository docenteRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthUtenteRepository utenteRepository;

    @Autowired
    private AuthRuoloRepository ruoloRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    // Gestisco il login
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> ruoli = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, "Bearer", userDetails.getUsername(), ruoli));
    }


    // Gestisco la registrazione
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (utenteRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Errore: Username già in uso!"));
        }

        if (utenteRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Errore: Email già in uso!"));
        }

        // Crea nuovo utente
        AuthUtente utente = AuthUtente.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .build();

        Set<String> strRuoli = signUpRequest.getRuoli();
        Set<AuthRuolo> ruoli = new HashSet<>();

        if (strRuoli == null || strRuoli.isEmpty()) {
            AuthRuolo defaultRuolo = ruoloRepository.findByNome(ERole.ROLE_STUDENTE)
                    .orElseThrow(() -> new RuntimeException("Ruolo non trovato"));
            ruoli.add(defaultRuolo);
        } else {
            strRuoli.forEach(r -> {
                switch (r.toLowerCase()) {
                    case "admin" -> ruoloRepository.findByNome(ERole.ROLE_ADMIN).ifPresent(ruoli::add);
                    case "docente" -> ruoloRepository.findByNome(ERole.ROLE_DOCENTE).ifPresent(ruoli::add);
                    default -> ruoloRepository.findByNome(ERole.ROLE_STUDENTE).ifPresent(ruoli::add);
                }
            });
        }

        utente.setRuoli(ruoli);
        AuthUtente utenteSalvato = utenteRepository.save(utente);

        // Associazione automatica Studente/Docente
        if (ruoli.stream().anyMatch(r -> r.getNome().equals(ERole.ROLE_STUDENTE))) {
            Studente studente = Studente.builder()
                    .nome(signUpRequest.getNome())
                    .cognome(signUpRequest.getCognome())
                    .email(signUpRequest.getEmail())
                    .dataNascita(signUpRequest.getDataNascita())
                    .authUtente(utenteSalvato)
                    .build();
            studenteRepository.save(studente);
        }

        if (ruoli.stream().anyMatch(r -> r.getNome().equals(ERole.ROLE_DOCENTE))) {
            Docente docente = Docente.builder()
                    .nome(signUpRequest.getNome())
                    .cognome(signUpRequest.getCognome())
                    .email(signUpRequest.getEmail())
                    .authUtente(utenteSalvato)
                    .build();
            docenteRepository.save(docente);
        }

        return ResponseEntity.ok(new MessageResponse("Utente registrato con successo!"));
    }

}

