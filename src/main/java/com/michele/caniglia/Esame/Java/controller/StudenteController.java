package com.michele.caniglia.Esame.Java.controller;

import com.michele.caniglia.Esame.Java.dto.StudenteDTO;
import com.michele.caniglia.Esame.Java.service.StudenteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/studenti")
@RequiredArgsConstructor
public class StudenteController {

    private final StudenteService studenteService;

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
}