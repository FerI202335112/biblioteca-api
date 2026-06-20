package com.cibertec.biblioteca.service;

import com.cibertec.biblioteca.entity.Usuario;
import com.cibertec.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario guardar(Usuario usuario) {

        usuario.setPassword(
                passwordEncoder.encode(usuario.getPassword())
        );

        return usuarioRepository.save(usuario);
    }
}