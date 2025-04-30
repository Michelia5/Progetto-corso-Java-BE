package com.michele.caniglia.Tech.Academy.controller;

import com.michele.caniglia.Tech.Academy.dto.IscrizioneDTO;
import com.michele.caniglia.Tech.Academy.service.IscrizioneService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/api/iscrizioni")
@RequiredArgsConstructor
public class IscrizioneController {

    private final IscrizioneService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<IscrizioneDTO> crea(@Valid @RequestBody IscrizioneDTO dto) {
        return ResponseEntity.ok(service.crea(dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENTE')")
    @GetMapping
    public ResponseEntity<List<IscrizioneDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<IscrizioneDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<IscrizioneDTO> aggiorna(@PathVariable Long id, @Valid @RequestBody IscrizioneDTO dto) {
        return ResponseEntity.ok(service.aggiorna(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        service.elimina(id);
        return ResponseEntity.noContent().build();
    }


    // Endpoint che genera il CSV
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/export/csv")
    public void exportIscrizioniToCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=iscrizioni.csv");

        try (PrintWriter writer = response.getWriter()) {
            writer.println("ID, Data di Iscrizione, Stato, Voto, Id Studente, Id Corso");

            List<IscrizioneDTO> iscrizioni = service.getAll();
            for (IscrizioneDTO i : iscrizioni) {
                writer.printf("%d, %s, %s, %d, %d, %d%n",
                        i.getId(),
                        i.getDataIscrizione(),
                        i.getStato(),
                        i.getVoto(),
                        i.getStudenteId(),
                        i.getCorsoId()
                );
            }
        }
    }
}
