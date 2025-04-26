package com.michele.caniglia.Esame.Java.controller;

import com.michele.caniglia.Esame.Java.dto.StudenteDTO;
import com.michele.caniglia.Esame.Java.service.StudenteService;

import java.io.IOException;

import com.michele.caniglia.Esame.Java.service.pdf.StudentePdfService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.util.List;


// Questa classe implementa il Controller del modello MVC.
// Gestisce le richieste REST per l'entità Studente e delega la logica al Service.


@RestController
@RequestMapping("/api/studenti")
@RequiredArgsConstructor
public class StudenteController {

    private final StudenteService studenteService;
    private final StudentePdfService studentePdfService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<StudenteDTO> creaStudente(@Valid @RequestBody StudenteDTO dto) {
        return ResponseEntity.ok(studenteService.creaStudente(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<StudenteDTO>> getAll() {
        return ResponseEntity.ok(studenteService.getAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<StudenteDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(studenteService.getById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<StudenteDTO> aggiorna(@PathVariable Long id, @Valid @RequestBody StudenteDTO dto) {
        return ResponseEntity.ok(studenteService.aggiornaStudente(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        studenteService.eliminaStudente(id);
        return ResponseEntity.noContent().build();
    }


    // Endpoint per generare il csv
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/export/csv")
    public void exportStudentiToCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=studenti.csv");

        try (PrintWriter writer = response.getWriter()) {
            writer.println("ID, Nome, Cognome, Email, Data di Nascita");

            List<StudenteDTO> studenti = studenteService.getAll();
            for (StudenteDTO s : studenti) {
                writer.printf("%d, %s, %s, %s, %s%n",
                        s.getId(),
                        s.getNome(),
                        s.getCognome(),
                        s.getEmail(),
                        s.getDataNascita()
                );
            }
        }
    }


    // Endpoint per generare il Pdf
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/export/pdf")
    public void exportProfiloStudente(@PathVariable Long id, HttpServletResponse response) throws IOException {
        try {
            studentePdfService.exportProfiloStudente(id, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore generazione PDF");
        }
    }
}