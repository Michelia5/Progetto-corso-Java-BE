package com.michele.caniglia.Tech.Academy.auth.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "Il nome utente è obbligatorio")
    private String username;
    
    @NotBlank(message = "La password è obbligatoria")
    private String password;
}
