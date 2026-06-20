package com.cibertec.biblioteca.controller;

import com.cibertec.biblioteca.entity.Libro;
import com.cibertec.biblioteca.exception.RecursoNoEncontradoException;
import com.cibertec.biblioteca.repository.LibroRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroRepository libroRepository;

    public LibroController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @GetMapping
    public List<Libro> listar() {
        return libroRepository.findAll();
    }

    @GetMapping("/{id}")
    public Libro buscarPorId(@PathVariable Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró libros con id " + id));
    }

    @PostMapping
    public ResponseEntity<Libro> registrar(@Valid @RequestBody Libro libro) {
        libro.setId(null);
        return ResponseEntity.ok(libroRepository.save(libro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizar(@PathVariable Long id, @Valid @RequestBody Libro datos) {
        if (!libroRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No se encontró libros con id " + id);
        }
        datos.setId(id);
        return ResponseEntity.ok(libroRepository.save(datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!libroRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No se encontró libros con id " + id);
        }
        libroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
