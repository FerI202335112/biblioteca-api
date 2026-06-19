package com.cibertec.biblioteca.controller;

import com.cibertec.biblioteca.entity.Multa;
import com.cibertec.biblioteca.service.MultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/multas")
public class MultaController {

    @Autowired
    private MultaService multaService;

    @GetMapping
    public List<Multa> listar() {
        return multaService.listarTodas();
    }

    @PostMapping
    public ResponseEntity<Multa> registrar(@RequestBody Multa multa) {
        return ResponseEntity.ok(multaService.guardar(multa));
    }
}