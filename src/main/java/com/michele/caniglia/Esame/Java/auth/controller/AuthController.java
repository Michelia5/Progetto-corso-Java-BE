package com.michele.caniglia.Esame.Java.auth.controller;

import com.michele.caniglia.Esame.Java.auth.model.AuthRuolo;
import com.michele.caniglia.Esame.Java.auth.model.AuthUtente;
import com.michele.caniglia.Esame.Java.auth.model.ERole;
import com.michele.caniglia.Esame.Java.auth.payload.request.LoginRequest;
import com.michele.caniglia.Esame.Java.auth.payload.request.SignupRequest;
import com.michele.caniglia.Esame.Java.auth.payload.response.JwtResponse;
import com.michele.caniglia.Esame.Java.auth.repository.AuthRuoloRepository;
import com.michele.caniglia.Esame.Java.auth.repository.AuthUtenteRepository;
import com.michele.caniglia.Esame.Java.auth.security.jwt.JwtUtils;
import com.michele.caniglia.Esame.Java.auth.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
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
public class AuthController {

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
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {

        if (utenteRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Errore: username già in uso!");
        }

        // Crea nuovo utente
        AuthUtente utente = AuthUtente.builder()
                .username(signupRequest.getUsername())
                .email(signupRequest.getEmail())
                .password(encoder.encode(signupRequest.getPassword()))
                .build();

        Set<String> strRuoli = signupRequest.getRuoli();
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
        utenteRepository.save(utente);

        return ResponseEntity.ok("Utente registrato con successo!");
    }
}

