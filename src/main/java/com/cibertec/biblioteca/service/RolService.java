package com.cibertec.biblioteca.service;

import com.cibertec.biblioteca.entity.Rol;
import java.util.List;

public interface RolService {
    List<Rol> listarTodos();
    Rol guardar(Rol rol);
}