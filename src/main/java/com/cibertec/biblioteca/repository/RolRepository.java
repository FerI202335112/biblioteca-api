package com.cibertec.biblioteca.repository;

import com.cibertec.biblioteca.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    // Esto servirá más adelante para buscar roles específicos en Spring Security
    java.util.Optional<Rol> findByNombre(String nombre);
}