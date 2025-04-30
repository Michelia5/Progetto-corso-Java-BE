package com.michele.caniglia.Tech.Academy.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AulaDTO {

    private Long id;

    @NotBlank
    private String nome;

    @Min(1)
    private int capienza;

    @Min(0)
    private int piano;
}