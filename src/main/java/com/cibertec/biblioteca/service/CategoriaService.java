package com.cibertec.biblioteca.service;

import com.cibertec.biblioteca.entity.Categoria;
import java.util.List;

public interface CategoriaService {
    List<Categoria> listarTodas();
    Categoria guardar(Categoria categoria);
}