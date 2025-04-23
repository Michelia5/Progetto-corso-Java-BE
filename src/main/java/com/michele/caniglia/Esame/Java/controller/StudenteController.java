package com.michele.caniglia.Esame.Java.controller;

import com.michele.caniglia.Esame.Java.dto.StudenteDTO;
import com.michele.caniglia.Esame.Java.model.Studente;
import com.michele.caniglia.Esame.Java.service.StudenteService;

import java.io.IOException;

import com.michele.caniglia.Esame.Java.service.pdf.StudentePdfService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.util.List;


// Questo controller implementa il pattern Controller del modello MVC.
// Gestisce le richieste REST per l'entità Studente e delega la logica al Service.


@RestController
@RequestMapping("/api/studenti")
@RequiredArgsConstructor
public class StudenteController {

    private final StudenteService studenteService;
    private final StudentePdfService studentePdfService;


    @PostMapping
    public ResponseEntity<StudenteDTO> creaStudente(@Valid @RequestBody StudenteDTO dto) {
        return ResponseEntity.ok(studenteService.creaStudente(dto));
    }

    @GetMapping
    public ResponseEntity<List<StudenteDTO>> getAll() {
        return ResponseEntity.ok(studenteService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudenteDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(studenteService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudenteDTO> aggiorna(@PathVariable Long id, @Valid @RequestBody StudenteDTO dto) {
        return ResponseEntity.ok(studenteService.aggiornaStudente(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        studenteService.eliminaStudente(id);
        return ResponseEntity.noContent().build();
    }


    // Endpoint che genera il CSV
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
    @GetMapping("/{id}/export/pdf")
    public void exportProfiloStudente(@PathVariable Long id, HttpServletResponse response) throws IOException {
        try {

            studentePdfService.exportProfiloStudente(id, response);
        } catch (Exception e) {
            e.printStackTrace(); // stampa in console
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore generazione PDF");
        }
    }

}