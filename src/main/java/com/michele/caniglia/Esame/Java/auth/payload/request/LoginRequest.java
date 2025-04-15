package com.michele.caniglia.Esame.Java.auth.payload.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
