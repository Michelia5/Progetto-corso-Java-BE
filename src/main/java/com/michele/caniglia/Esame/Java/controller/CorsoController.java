package com.michele.caniglia.Esame.Java.controller;

import com.michele.caniglia.Esame.Java.dto.CorsoDTO;
import com.michele.caniglia.Esame.Java.service.CorsoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/corsi")
@RequiredArgsConstructor
public class CorsoController {

    private final CorsoService service;

    @PostMapping
    public ResponseEntity<CorsoDTO> crea(@Valid @RequestBody CorsoDTO dto) {
        return ResponseEntity.ok(service.crea(dto));
    }

    @GetMapping
    public ResponseEntity<List<CorsoDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CorsoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CorsoDTO> aggiorna(@PathVariable Long id, @Valid @RequestBody CorsoDTO dto) {
        return ResponseEntity.ok(service.aggiorna(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        service.elimina(id);
        return ResponseEntity.noContent().build();
    }
}
