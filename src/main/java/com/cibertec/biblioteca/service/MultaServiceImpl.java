package com.cibertec.biblioteca.service;

import com.cibertec.biblioteca.entity.Multa;
import com.cibertec.biblioteca.repository.MultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MultaServiceImpl implements MultaService {

    @Autowired
    private MultaRepository multaRepository;

    @Override
    public List<Multa> listarTodas() {
        return multaRepository.findAll();
    }

    @Override
    public Multa guardar(Multa multa) {
        return multaRepository.save(multa);
    }
}