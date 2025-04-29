package com.michele.caniglia.Esame.Java.service;

import com.michele.caniglia.Esame.Java.dto.StudenteDTO;
import com.michele.caniglia.Esame.Java.exception.ResourceNotFoundException;
import com.michele.caniglia.Esame.Java.model.Studente;
import com.michele.caniglia.Esame.Java.repository.StudenteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudenteServiceTest {

    private StudenteRepository studenteRepository;
    private StudenteService studenteService;

    @BeforeEach
    void setUp() {
        studenteRepository = Mockito.mock(StudenteRepository.class);
        studenteService = new StudenteService(studenteRepository);
    }


    /*
    Ho effettuato delle modifiche al file poiché in caso di id inesistente prima
    andavo ad aspettarmi come risultato null, ma avendo aggiunto
    poi l'eccezione ResourceNotFound ovviamente i test fallivano
    poiché ricevevo in "risposta" quest'ultima e non più null
    */

    @Test
    void creaStudente_deveRestituireDTOSalvato() {
        StudenteDTO dto = new StudenteDTO();
        dto.setNome("Luca");
        dto.setCognome("Rossi");
        dto.setEmail("luca.rossi@example.com");
        dto.setDataNascita(LocalDate.of(2000, 5, 12));

        Studente savedEntity = Studente.builder()
                .id(1L)
                .nome("Luca")
                .cognome("Rossi")
                .email("luca.rossi@example.com")
                .dataNascita(LocalDate.of(2000, 5, 12))
                .build();

        when(studenteRepository.save(any(Studente.class))).thenReturn(savedEntity);

        StudenteDTO result = studenteService.creaStudente(dto);

        assertNotNull(result);
        assertEquals("Luca", result.getNome());
        assertEquals("Rossi", result.getCognome());
        assertEquals("luca.rossi@example.com", result.getEmail());
        assertEquals(LocalDate.of(2000, 5, 12), result.getDataNascita());

        verify(studenteRepository, times(1)).save(any(Studente.class));
    }

    @Test
    void getAll_deveRestituireListaDTO() {
        List<Studente> studenti = List.of(
                Studente.builder().id(1L).nome("Luca").cognome("Rossi").email("luca@example.com").dataNascita(LocalDate.of(2000, 5, 12)).build(),
                Studente.builder().id(2L).nome("Giulia").cognome("Verdi").email("giulia@example.com").dataNascita(LocalDate.of(2001, 6, 10)).build()
        );

        when(studenteRepository.findAll()).thenReturn(studenti);

        List<StudenteDTO> result = studenteService.getAll();

        assertEquals(2, result.size());
        assertEquals("Luca", result.get(0).getNome());
        assertEquals("Giulia", result.get(1).getNome());
        verify(studenteRepository, times(1)).findAll();
    }

    @Test
    void getById_conIdEsistente_deveRestituireDTO() {
        Studente studente = Studente.builder()
                .id(1L)
                .nome("Luca")
                .cognome("Rossi")
                .email("luca@example.com")
                .dataNascita(LocalDate.of(2000, 5, 12))
                .build();

        when(studenteRepository.findById(1L)).thenReturn(Optional.of(studente));

        StudenteDTO result = studenteService.getById(1L);

        assertNotNull(result);
        assertEquals("Luca", result.getNome());
        verify(studenteRepository, times(1)).findById(1L);
    }

    @Test
    void getById_conIdInesistente_deveLanciareEccezione() {
        when(studenteRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> studenteService.getById(99L));

        verify(studenteRepository, times(1)).findById(99L);
    }

    @Test
    void aggiornaStudente_conIdValido_deveAggiornareERestituireDTO() {
        StudenteDTO dto = new StudenteDTO();
        dto.setNome("Marco");
        dto.setCognome("Neri");
        dto.setEmail("marco@example.com");
        dto.setDataNascita(LocalDate.of(2001, 1, 1));

        when(studenteRepository.existsById(1L)).thenReturn(true);
        when(studenteRepository.save(any(Studente.class))).thenAnswer(invocation -> {
            Studente arg = invocation.getArgument(0);
            arg.setId(1L);
            return arg;
        });

        StudenteDTO result = studenteService.aggiornaStudente(1L, dto);

        assertNotNull(result);
        assertEquals("Marco", result.getNome());
        assertEquals("Neri", result.getCognome());
        assertEquals("marco@example.com", result.getEmail());
        verify(studenteRepository).existsById(1L);
        verify(studenteRepository).save(any(Studente.class));
    }

    @Test
    void aggiornaStudente_conIdNonEsistente_deveLanciareEccezione() {
        StudenteDTO dto = new StudenteDTO();
        when(studenteRepository.existsById(100L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> studenteService.aggiornaStudente(100L, dto));

        verify(studenteRepository).existsById(100L);
        verify(studenteRepository, never()).save(any());
    }

    @Test
    void eliminaStudente_conIdInesistente_deveLanciareEccezione() {
        Long id = 1L;
        when(studenteRepository.existsById(id)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> studenteService.eliminaStudente(id));

        verify(studenteRepository, times(1)).existsById(id);
        verify(studenteRepository, never()).deleteById(id);
    }

}
