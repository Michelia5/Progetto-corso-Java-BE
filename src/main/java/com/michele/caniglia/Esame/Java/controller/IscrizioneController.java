package com.michele.caniglia.Esame.Java.controller;

import com.michele.caniglia.Esame.Java.dto.IscrizioneDTO;
import com.michele.caniglia.Esame.Java.service.IscrizioneService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/api/iscrizioni")
@RequiredArgsConstructor
public class IscrizioneController {

    private final IscrizioneService service;

    @PostMapping
    public ResponseEntity<IscrizioneDTO> crea(@Valid @RequestBody IscrizioneDTO dto) {
        return ResponseEntity.ok(service.crea(dto));
    }

    @GetMapping
    public ResponseEntity<List<IscrizioneDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IscrizioneDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IscrizioneDTO> aggiorna(@PathVariable Long id, @Valid @RequestBody IscrizioneDTO dto) {
        return ResponseEntity.ok(service.aggiorna(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        service.elimina(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint che genera il CSV
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
