package com.cibertec.biblioteca.service;

import com.cibertec.biblioteca.entity.Multa;
import java.util.List;

public interface MultaService {
    List<Multa> listarTodas();
    Multa guardar(Multa multa);
}