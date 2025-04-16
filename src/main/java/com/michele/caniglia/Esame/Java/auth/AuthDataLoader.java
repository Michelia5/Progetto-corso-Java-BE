package com.michele.caniglia.Esame.Java.auth;

import com.michele.caniglia.Esame.Java.auth.model.AuthRuolo;
import com.michele.caniglia.Esame.Java.auth.model.ERole;
import com.michele.caniglia.Esame.Java.auth.repository.AuthRuoloRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthDataLoader implements CommandLineRunner {

    private final AuthRuoloRepository ruoloRepository;

    @Override
    @Transactional
    public void run(String... args) {
        loadRuoli();
    }

    private void loadRuoli() {
        for (ERole ruolo : ERole.values()) {
            ruoloRepository.findByNome(ruolo)
                .orElseGet(() -> {
                    AuthRuolo nuovoRuolo = AuthRuolo.builder().nome(ruolo).build();
                    ruoloRepository.save(nuovoRuolo);
                    System.out.println("✅ Ruolo creato: " + ruolo.name());
                    return nuovoRuolo;
                });
        }
    }
}
