package com.cibertec.biblioteca.service;

import com.cibertec.biblioteca.entity.Autor;
import java.util.List;

public interface AutorService {
    List<Autor> listarTodos();
    Autor guardar(Autor autor);
}