package com.cibertec.biblioteca.controller;

import com.cibertec.biblioteca.entity.Categoria;
import com.cibertec.biblioteca.exception.RecursoNoEncontradoException;
import com.cibertec.biblioteca.repository.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Categoria buscarPorId(@PathVariable Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró categorias con id " + id));
    }

    @PostMapping
    public ResponseEntity<Categoria> registrar(@Valid @RequestBody Categoria categoria) {
        categoria.setId(null);
        return ResponseEntity.ok(categoriaRepository.save(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(@PathVariable Long id, @Valid @RequestBody Categoria datos) {
        if (!categoriaRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No se encontró categorias con id " + id);
        }
        datos.setId(id);
        return ResponseEntity.ok(categoriaRepository.save(datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No se encontró categorias con id " + id);
        }
        categoriaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
