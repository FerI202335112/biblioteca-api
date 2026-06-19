package com.cibertec.biblioteca.service;

import com.cibertec.biblioteca.entity.Editorial;
import com.cibertec.biblioteca.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EditorialServiceImpl implements EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    @Override
    public List<Editorial> listarTodas() {
        return editorialRepository.findAll();
    }

    @Override
    public Editorial guardar(Editorial editorial) {
        return editorialRepository.save(editorial);
    }
}