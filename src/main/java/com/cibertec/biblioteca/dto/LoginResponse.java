package com.cibertec.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String mensaje;
    private Long usuarioId;
    private String nombre;
    private String email;
}
