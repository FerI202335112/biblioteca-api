package com.cibertec.biblioteca.service;

import com.cibertec.biblioteca.entity.Libro;
import java.util.List;
import java.util.Optional;

public interface LibroService {
    List<Libro> listarTodos();
    Libro guardar(Libro libro);
    
    Optional<Libro> buscarPorId(Long id);
    void eliminar(Long id);
}