package com.michele.caniglia.Esame.Java.auth.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class SignupRequest {

    @NotBlank(message = "Il nome utente è obbligatorio")
    private String username;

    @NotBlank(message = "La password è obbligatoria")
    private String password;

    @Email(message = "Email non valida")
    @NotBlank(message = "L'email è obbligatoria")
    private String email;

    private Set<String> ruoli; // es. ["admin", "studente"]
}
