package com.michele.caniglia.Esame.Java.controller;

import com.michele.caniglia.Esame.Java.dto.EsameDTO;
import com.michele.caniglia.Esame.Java.service.EsameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/esami")
@RequiredArgsConstructor
public class EsameController {

    private final EsameService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<EsameDTO> crea(@Valid @RequestBody EsameDTO dto) {
        return ResponseEntity.ok(service.crea(dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCENTE')")
    @GetMapping
    public ResponseEntity<List<EsameDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<EsameDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<EsameDTO> aggiorna(@PathVariable Long id, @Valid @RequestBody EsameDTO dto) {
        return ResponseEntity.ok(service.aggiorna(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        service.elimina(id);
        return ResponseEntity.noContent().build();
    }
}
