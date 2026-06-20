package com.cibertec.biblioteca.controller;

import com.cibertec.biblioteca.entity.Rol;
import com.cibertec.biblioteca.exception.RecursoNoEncontradoException;
import com.cibertec.biblioteca.repository.RolRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolRepository rolRepository;

    public RolController(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @GetMapping
    public List<Rol> listar() {
        return rolRepository.findAll();
    }

    @GetMapping("/{id}")
    public Rol buscarPorId(@PathVariable Long id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró roles con id " + id));
    }

    @PostMapping
    public ResponseEntity<Rol> registrar(@Valid @RequestBody Rol rol) {
        rol.setId(null);
        return ResponseEntity.ok(rolRepository.save(rol));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizar(@PathVariable Long id, @Valid @RequestBody Rol datos) {
        if (!rolRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No se encontró roles con id " + id);
        }
        datos.setId(id);
        return ResponseEntity.ok(rolRepository.save(datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!rolRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No se encontró roles con id " + id);
        }
        rolRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
