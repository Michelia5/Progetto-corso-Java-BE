package com.michele.caniglia.Esame.Java.controller;

import com.michele.caniglia.Esame.Java.dto.DocenteDTO;
import com.michele.caniglia.Esame.Java.service.DocenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/docenti")
@RequiredArgsConstructor
public class DocenteController {

    private final DocenteService service;

    @PostMapping
    public ResponseEntity<DocenteDTO> crea(@Valid @RequestBody DocenteDTO dto) {
        return ResponseEntity.ok(service.crea(dto));
    }

    @GetMapping
    public ResponseEntity<List<DocenteDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocenteDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocenteDTO> aggiorna(@PathVariable Long id, @Valid @RequestBody DocenteDTO dto) {
        return ResponseEntity.ok(service.aggiorna(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        service.elimina(id);
        return ResponseEntity.noContent().build();
    }
}
