package com.michele.caniglia.Tech.Academy.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StudenteDTO {

    private Long id;

    @NotBlank
    private String nome;
    
    @NotBlank
    private String cognome;
    
    @Email
    private String email;
    
    @NotNull 
    private LocalDate dataNascita;
}
