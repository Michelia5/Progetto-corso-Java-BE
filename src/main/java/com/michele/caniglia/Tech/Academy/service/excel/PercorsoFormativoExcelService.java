package com.michele.caniglia.Tech.Academy.service.excel;

import com.michele.caniglia.Tech.Academy.model.Corso;
import com.michele.caniglia.Tech.Academy.model.Esame;
import com.michele.caniglia.Tech.Academy.model.PercorsoFormativo;
import com.michele.caniglia.Tech.Academy.repository.PercorsoFormativoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PercorsoFormativoExcelService {

    private final PercorsoFormativoRepository percorsoFormativoRepository;

    public void exportPercorsiEdEsami(HttpServletResponse response) throws IOException {

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=percorsi_esami.xlsx");

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Percorsi & Esami");

        // Header
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Percorso");
        header.createCell(1).setCellValue("Corso");
        header.createCell(2).setCellValue("Esame");
        header.createCell(3).setCellValue("Data");

        int rowNum = 1;

        List<PercorsoFormativo> percorsi = percorsoFormativoRepository.findAll();
        for (PercorsoFormativo percorso : percorsi) {
            for (Corso corso : percorso.getCorsi()) {
                for (Esame esame : corso.getEsami()) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(percorso.getNome());
                    row.createCell(1).setCellValue(corso.getNome());
                    row.createCell(2).setCellValue(esame.getNome());
                    row.createCell(3).setCellValue(esame.getData().toString());
                }
            }
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
