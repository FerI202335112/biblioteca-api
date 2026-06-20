package com.cibertec.biblioteca.service;

import com.cibertec.biblioteca.entity.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> listarTodos();
    Usuario guardar(Usuario usuario);
}