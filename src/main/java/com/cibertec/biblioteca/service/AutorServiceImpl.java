package com.cibertec.biblioteca.service;

import com.cibertec.biblioteca.entity.Autor;
import com.cibertec.biblioteca.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    @Override
    public Autor guardar(Autor autor) {
        return autorRepository.save(autor);
    }
}