package com.michele.caniglia.Esame.Java.controller;

import com.michele.caniglia.Esame.Java.dto.MaterialeDidatticoDTO;
import com.michele.caniglia.Esame.Java.service.MaterialeDidatticoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiali")
@RequiredArgsConstructor
public class MaterialeDidatticoController {

    private final MaterialeDidatticoService service;

    @PostMapping
    public ResponseEntity<MaterialeDidatticoDTO> crea(@Valid @RequestBody MaterialeDidatticoDTO dto) {
        return ResponseEntity.ok(service.crea(dto));
    }

    @GetMapping
    public ResponseEntity<List<MaterialeDidatticoDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialeDidatticoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialeDidatticoDTO> aggiorna(@PathVariable Long id, @Valid @RequestBody MaterialeDidatticoDTO dto) {
        return ResponseEntity.ok(service.aggiorna(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        service.elimina(id);
        return ResponseEntity.noContent().build();
    }
}
