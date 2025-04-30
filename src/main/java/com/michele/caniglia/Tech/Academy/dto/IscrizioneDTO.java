package com.michele.caniglia.Tech.Academy.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class IscrizioneDTO {

    private Long id;

    @NotNull
    private LocalDate dataIscrizione;

    @NotBlank
    @Pattern(regexp = "ATTIVA|COMPLETATA|RITIRATA", message = "Stato non valido (ATTIVA, COMPLETATA o RITIRATA)")
    private String stato;

    @Min(18)
    @Max(30)
    private Integer voto;

    @NotNull
    private Long studenteId;

    @NotNull
    private Long corsoId;
}
