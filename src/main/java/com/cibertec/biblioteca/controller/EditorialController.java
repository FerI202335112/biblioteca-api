package com.cibertec.biblioteca.controller;

import com.cibertec.biblioteca.entity.Editorial;
import com.cibertec.biblioteca.service.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editoriales")
public class EditorialController {

    @Autowired
    private EditorialService editorialService;

    @GetMapping
    public List<Editorial> listar() {
        return editorialService.listarTodas();
    }

    @PostMapping
    public ResponseEntity<Editorial> registrar(@RequestBody Editorial editorial) {
        return ResponseEntity.ok(editorialService.guardar(editorial));
    }
}