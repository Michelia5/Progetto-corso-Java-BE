package com.michele.caniglia.Esame.Java.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EsameDTO {

    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    private LocalDate data;

    @NotNull
    private Long corsoId;
}
