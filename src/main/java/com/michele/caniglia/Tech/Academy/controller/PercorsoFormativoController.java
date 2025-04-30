package com.michele.caniglia.Tech.Academy.controller;

import com.michele.caniglia.Tech.Academy.dto.PercorsoFormativoDTO;
import com.michele.caniglia.Tech.Academy.service.PercorsoFormativoService;
import com.michele.caniglia.Tech.Academy.service.excel.PercorsoFormativoExcelService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/percorsi")
@RequiredArgsConstructor
public class PercorsoFormativoController {

    private final PercorsoFormativoService service;
    private final PercorsoFormativoExcelService percorsoFormativoExcelService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PercorsoFormativoDTO> crea(@Valid @RequestBody PercorsoFormativoDTO dto) {
        return ResponseEntity.ok(service.crea(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<PercorsoFormativoDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<PercorsoFormativoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PercorsoFormativoDTO> aggiorna(@PathVariable Long id, @Valid @RequestBody PercorsoFormativoDTO dto) {
        return ResponseEntity.ok(service.aggiorna(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        service.elimina(id);
        return ResponseEntity.noContent().build();
    }


    // Endpoint per esportare il file excel
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/export/excel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        percorsoFormativoExcelService.exportPercorsiEdEsami(response);
    }
}
