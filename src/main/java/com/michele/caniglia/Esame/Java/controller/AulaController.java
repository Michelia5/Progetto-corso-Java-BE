package com.michele.caniglia.Esame.Java.controller;

import com.michele.caniglia.Esame.Java.dto.AulaDTO;
import com.michele.caniglia.Esame.Java.service.AulaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/api/aule")
@RequiredArgsConstructor
public class AulaController {

    private final AulaService service;

    @PostMapping
    public ResponseEntity<AulaDTO> crea(@Valid @RequestBody AulaDTO dto) {
        return ResponseEntity.ok(service.crea(dto));
    }

    @GetMapping
    public ResponseEntity<List<AulaDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AulaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AulaDTO> aggiorna(@PathVariable Long id, @Valid @RequestBody AulaDTO dto) {
        return ResponseEntity.ok(service.aggiorna(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        service.elimina(id);
        return ResponseEntity.noContent().build();
    }
}