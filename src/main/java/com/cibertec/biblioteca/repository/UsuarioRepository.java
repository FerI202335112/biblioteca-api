package com.cibertec.biblioteca.repository;

import com.cibertec.biblioteca.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Esto te servirá luego para el login con Spring Security
    java.util.Optional<Usuario> findByEmail(String email);
}