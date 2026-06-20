package com.cibertec.biblioteca.controller;

import com.cibertec.biblioteca.entity.Autor;
import com.cibertec.biblioteca.exception.RecursoNoEncontradoException;
import com.cibertec.biblioteca.repository.AutorRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    private final AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @GetMapping
    public List<Autor> listar() {
        return autorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Autor buscarPorId(@PathVariable Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró autores con id " + id));
    }

    @PostMapping
    public ResponseEntity<Autor> registrar(@Valid @RequestBody Autor autor) {
        autor.setId(null);
        return ResponseEntity.ok(autorRepository.save(autor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> actualizar(@PathVariable Long id, @Valid @RequestBody Autor datos) {
        if (!autorRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No se encontró autores con id " + id);
        }
        datos.setId(id);
        return ResponseEntity.ok(autorRepository.save(datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!autorRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No se encontró autores con id " + id);
        }
        autorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
