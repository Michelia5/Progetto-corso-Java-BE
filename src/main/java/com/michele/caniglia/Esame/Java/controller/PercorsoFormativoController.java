package com.michele.caniglia.Esame.Java.controller;

import com.michele.caniglia.Esame.Java.dto.PercorsoFormativoDTO;
import com.michele.caniglia.Esame.Java.service.PercorsoFormativoService;
import com.michele.caniglia.Esame.Java.service.excel.PercorsoFormativoExcelService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/percorsi")
@RequiredArgsConstructor
public class PercorsoFormativoController {

    private final PercorsoFormativoService service;
    private final PercorsoFormativoExcelService percorsoFormativoExcelService;


    @PostMapping
    public ResponseEntity<PercorsoFormativoDTO> crea(@Valid @RequestBody PercorsoFormativoDTO dto) {
        return ResponseEntity.ok(service.crea(dto));
    }

    @GetMapping
    public ResponseEntity<List<PercorsoFormativoDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PercorsoFormativoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PercorsoFormativoDTO> aggiorna(@PathVariable Long id, @Valid @RequestBody PercorsoFormativoDTO dto) {
        return ResponseEntity.ok(service.aggiorna(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        service.elimina(id);
        return ResponseEntity.noContent().build();
    }


    // Endpoint per esportare il file excel
    @GetMapping("/export/excel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        percorsoFormativoExcelService.exportPercorsiEdEsami(response);
    }

}
