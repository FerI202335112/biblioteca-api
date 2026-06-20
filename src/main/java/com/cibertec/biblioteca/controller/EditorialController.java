package com.cibertec.biblioteca.controller;

import com.cibertec.biblioteca.entity.Editorial;
import com.cibertec.biblioteca.exception.RecursoNoEncontradoException;
import com.cibertec.biblioteca.repository.EditorialRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editoriales")
public class EditorialController {

    private final EditorialRepository editorialRepository;

    public EditorialController(EditorialRepository editorialRepository) {
        this.editorialRepository = editorialRepository;
    }

    @GetMapping
    public List<Editorial> listar() {
        return editorialRepository.findAll();
    }

    @GetMapping("/{id}")
    public Editorial buscarPorId(@PathVariable Long id) {
        return editorialRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró editoriales con id " + id));
    }

    @PostMapping
    public ResponseEntity<Editorial> registrar(@Valid @RequestBody Editorial editorial) {
        editorial.setId(null);
        return ResponseEntity.ok(editorialRepository.save(editorial));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Editorial> actualizar(@PathVariable Long id, @Valid @RequestBody Editorial datos) {
        if (!editorialRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No se encontró editoriales con id " + id);
        }
        datos.setId(id);
        return ResponseEntity.ok(editorialRepository.save(datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!editorialRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No se encontró editoriales con id " + id);
        }
        editorialRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
