package com.cibertec.biblioteca.controller;

import com.cibertec.biblioteca.entity.Prestamo;
import com.cibertec.biblioteca.exception.RecursoNoEncontradoException;
import com.cibertec.biblioteca.repository.PrestamoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoRepository prestamoRepository;

    public PrestamoController(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @GetMapping
    public List<Prestamo> listar() {
        return prestamoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Prestamo buscarPorId(@PathVariable Long id) {
        return prestamoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró prestamos con id " + id));
    }

    @PostMapping
    public ResponseEntity<Prestamo> registrar(@Valid @RequestBody Prestamo prestamo) {
        prestamo.setId(null);
        return ResponseEntity.ok(prestamoRepository.save(prestamo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> actualizar(@PathVariable Long id, @Valid @RequestBody Prestamo datos) {
        if (!prestamoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No se encontró prestamos con id " + id);
        }
        datos.setId(id);
        return ResponseEntity.ok(prestamoRepository.save(datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!prestamoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No se encontró prestamos con id " + id);
        }
        prestamoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
