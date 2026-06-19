package com.cibertec.biblioteca.service;

import com.cibertec.biblioteca.entity.Editorial;
import java.util.List;

public interface EditorialService {
    List<Editorial> listarTodas();
    Editorial guardar(Editorial editorial);
}