package com.cibertec.biblioteca.controller;

import com.cibertec.biblioteca.entity.Usuario;
import com.cibertec.biblioteca.exception.RecursoNoEncontradoException;
import com.cibertec.biblioteca.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró usuarios con id " + id));
    }

    @PostMapping
    public ResponseEntity<Usuario> registrar(@Valid @RequestBody Usuario usuario) {
        usuario.setId(null);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @Valid @RequestBody Usuario datos) {
        Usuario actual = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró usuarios con id " + id));

        datos.setId(id);
        if (datos.getPassword() == null || datos.getPassword().isBlank()) {
            datos.setPassword(actual.getPassword());
        } else if (!datos.getPassword().startsWith("$2a$") && !datos.getPassword().startsWith("$2b$") && !datos.getPassword().startsWith("$2y$")) {
            datos.setPassword(passwordEncoder.encode(datos.getPassword()));
        }
        return ResponseEntity.ok(usuarioRepository.save(datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No se encontró usuarios con id " + id);
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
