package com.cibertec.biblioteca.controller;

import com.cibertec.biblioteca.entity.Autor;
import com.cibertec.biblioteca.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public List<Autor> listar() {
        return autorService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Autor> registrar(@RequestBody Autor autor) {
        return ResponseEntity.ok(autorService.guardar(autor));
    }
}