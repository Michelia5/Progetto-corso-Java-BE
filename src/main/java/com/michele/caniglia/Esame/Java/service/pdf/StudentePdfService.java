package com.michele.caniglia.Esame.Java.service.pdf;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import com.michele.caniglia.Esame.Java.model.Iscrizione;
import com.michele.caniglia.Esame.Java.model.Studente;
import com.michele.caniglia.Esame.Java.repository.IscrizioneRepository;
import com.michele.caniglia.Esame.Java.repository.StudenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentePdfService {

    private final StudenteRepository studenteRepository;
    private final IscrizioneRepository iscrizioneRepository;

    public void exportProfiloStudente(Long studenteId, HttpServletResponse response) throws IOException {
        Studente studente = studenteRepository.findById(studenteId).orElse(null);
        if (studente == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Studente non trovato");
            return;
        }

        List<Iscrizione> iscrizioni = iscrizioneRepository.findByStudenteId(studenteId);

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=profilo-studente-" + studenteId + ".pdf");

        document.open();

        Font titoloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.BLUE);
        Font normaleFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Color.BLACK);

        document.add(new Paragraph("Profilo Studente", titoloFont));
        document.add(new Paragraph(" "));

        document.add(new Paragraph("ID: " + studente.getId(), normaleFont));
        document.add(new Paragraph("Nome: " + studente.getNome(), normaleFont));
        document.add(new Paragraph("Cognome: " + studente.getCognome(), normaleFont));
        document.add(new Paragraph("Email: " + studente.getEmail(), normaleFont));
        document.add(new Paragraph("Data di nascita: " + studente.getDataNascita(), normaleFont));
        document.add(new Paragraph(" "));

        document.add(new Paragraph("Iscrizioni:", titoloFont));
        document.add(new Paragraph(" "));

        for (Iscrizione i : iscrizioni) {
            document.add(new Paragraph("- Corso: " + i.getCorso().getNome(), normaleFont));
            document.add(new Paragraph("  Data iscrizione: " + i.getDataIscrizione(), normaleFont));
            document.add(new Paragraph("  Stato: " + i.getStato(), normaleFont));
            document.add(new Paragraph("  Voto: " + i.getVoto(), normaleFont));
            document.add(new Paragraph(" "));
        }

        document.close();
    }
}
