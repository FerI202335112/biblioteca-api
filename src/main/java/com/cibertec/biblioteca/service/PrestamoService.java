package com.cibertec.biblioteca.service;

import com.cibertec.biblioteca.entity.Prestamo;
import java.util.List;

public interface PrestamoService {
    List<Prestamo> listarTodos();
    Prestamo guardar(Prestamo prestamo);
}