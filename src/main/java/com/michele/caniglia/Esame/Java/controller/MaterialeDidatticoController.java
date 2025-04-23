package com.michele.caniglia.Esame.Java.controller;

import com.michele.caniglia.Esame.Java.dto.MaterialeDidatticoDTO;
import com.michele.caniglia.Esame.Java.service.MaterialeDidatticoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiali")
@RequiredArgsConstructor
public class MaterialeDidatticoController {

    private final MaterialeDidatticoService service;

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCENTE')")
    @PostMapping
    public ResponseEntity<MaterialeDidatticoDTO> crea(@Valid @RequestBody MaterialeDidatticoDTO dto) {
        return ResponseEntity.ok(service.crea(dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCENTE')")
    @GetMapping
    public ResponseEntity<List<MaterialeDidatticoDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<MaterialeDidatticoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCENTE')")
    @PutMapping("/{id}")
    public ResponseEntity<MaterialeDidatticoDTO> aggiorna(@PathVariable Long id, @Valid @RequestBody MaterialeDidatticoDTO dto) {
        return ResponseEntity.ok(service.aggiorna(id, dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        service.elimina(id);
        return ResponseEntity.noContent().build();
    }
}
