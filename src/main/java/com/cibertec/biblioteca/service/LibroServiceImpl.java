package com.cibertec.biblioteca.service;

import com.cibertec.biblioteca.entity.Libro;
import com.cibertec.biblioteca.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Override
    public List<Libro> listarTodos() {
        return libroRepository.findAll();
    }

    @Override
    public Libro guardar(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public Optional<Libro> buscarPorId(Long id) {
        return libroRepository.findById(id);
    }

    @Override
    public void eliminar(Long id) {
        libroRepository.deleteById(id);
    }
}