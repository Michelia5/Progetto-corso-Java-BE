package com.michele.caniglia.Tech.Academy.controller;

import com.michele.caniglia.Tech.Academy.dto.CorsoDTO;
import com.michele.caniglia.Tech.Academy.service.CorsoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/corsi")
@RequiredArgsConstructor
public class CorsoController {

    private final CorsoService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CorsoDTO> crea(@Valid @RequestBody CorsoDTO dto) {
        return ResponseEntity.ok(service.crea(dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCENTE')")
    @GetMapping
    public ResponseEntity<List<CorsoDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<CorsoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CorsoDTO> aggiorna(@PathVariable Long id, @Valid @RequestBody CorsoDTO dto) {
        return ResponseEntity.ok(service.aggiorna(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        service.elimina(id);
        return ResponseEntity.noContent().build();
    }
}
