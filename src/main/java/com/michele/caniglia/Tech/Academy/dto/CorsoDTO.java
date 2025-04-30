package com.michele.caniglia.Tech.Academy.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CorsoDTO {

    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    private LocalDate dataInizio;

    @NotNull
    private LocalDate dataFine;

    @Min(1)
    private int durataOre;

    @NotNull
    private Long docenteId;

    @NotNull
    private Long aulaId;

    @NotNull
    private Long percorsoFormativoId;
}

