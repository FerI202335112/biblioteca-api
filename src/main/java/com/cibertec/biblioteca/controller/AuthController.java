package com.cibertec.biblioteca.controller;

import com.cibertec.biblioteca.dto.LoginRequest;
import com.cibertec.biblioteca.dto.LoginResponse;
import com.cibertec.biblioteca.entity.Usuario;
import com.cibertec.biblioteca.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (usuario == null || !passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            return ResponseEntity.status(401).body(Map.of("mensaje", "Credenciales incorrectas"));
        }

        return ResponseEntity.ok(new LoginResponse(
                "Login correcto",
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        ));
    }
}
