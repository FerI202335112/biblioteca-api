package com.cibertec.biblioteca.controller;

import com.cibertec.biblioteca.entity.Libro;
import com.cibertec.biblioteca.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public List<Libro> listar() {
        return libroService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Libro> registrar(@RequestBody Libro libro) {
        return ResponseEntity.ok(libroService.guardar(libro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizar(@PathVariable Long id, @RequestBody Libro libroDetalles) {
        return libroService.buscarPorId(id).map(libro -> {
            libro.setTitulo(libroDetalles.getTitulo());
            libro.setIsbn(libroDetalles.getIsbn()); 
            libro.setStock(libroDetalles.getStock());
            libro.setFecha_publicacion(libroDetalles.getFecha_publicacion());
            libro.setCategoria(libroDetalles.getCategoria());
            libro.setEditorial(libroDetalles.getEditorial());
            libro.setAutores(libroDetalles.getAutores());
            return ResponseEntity.ok(libroService.guardar(libro));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (libroService.buscarPorId(id).isPresent()) {
            libroService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}