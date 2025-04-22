package com.michele.caniglia.Esame.Java.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MaterialeDidatticoDTO {

    private Long id;

    @NotBlank
    private String titolo;

    @NotBlank
    @Pattern(regexp = "PDF|LINK|SLIDE", message = "Il tipo deve essere PDF, LINK o SLIDE")
    private String tipo;

    @NotBlank
    private String descrizione;

    @NotNull
    private Long corsoId;
}
