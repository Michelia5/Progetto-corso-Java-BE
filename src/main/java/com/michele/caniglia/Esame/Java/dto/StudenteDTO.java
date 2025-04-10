package com.michele.caniglia.Esame.Java.dto;

import lombok.Data;

@Data
public class StudenteDTO {
    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private String dataNascita;
}
