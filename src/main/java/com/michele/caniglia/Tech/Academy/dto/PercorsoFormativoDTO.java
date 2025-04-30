package com.michele.caniglia.Tech.Academy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PercorsoFormativoDTO {

    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descrizione;
}