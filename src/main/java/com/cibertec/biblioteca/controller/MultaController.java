package com.cibertec.biblioteca.controller;

import com.cibertec.biblioteca.entity.Multa;
import com.cibertec.biblioteca.exception.RecursoNoEncontradoException;
import com.cibertec.biblioteca.repository.MultaRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/multas")
public class MultaController {

    private final MultaRepository multaRepository;

    public MultaController(MultaRepository multaRepository) {
        this.multaRepository = multaRepository;
    }

    @GetMapping
    public List<Multa> listar() {
        return multaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Multa buscarPorId(@PathVariable Long id) {
        return multaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró multas con id " + id));
    }

    @PostMapping
    public ResponseEntity<Multa> registrar(@Valid @RequestBody Multa multa) {
        multa.setId(null);
        return ResponseEntity.ok(multaRepository.save(multa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Multa> actualizar(@PathVariable Long id, @Valid @RequestBody Multa datos) {
        if (!multaRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No se encontró multas con id " + id);
        }
        datos.setId(id);
        return ResponseEntity.ok(multaRepository.save(datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!multaRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No se encontró multas con id " + id);
        }
        multaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
