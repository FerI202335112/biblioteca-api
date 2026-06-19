package com.cibertec.biblioteca.controller;

import com.cibertec.biblioteca.entity.Prestamo;
import com.cibertec.biblioteca.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @GetMapping
    public List<Prestamo> listar() {
        return prestamoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Prestamo> registrar(@RequestBody Prestamo prestamo) {
        return ResponseEntity.ok(prestamoService.guardar(prestamo));
    }
}