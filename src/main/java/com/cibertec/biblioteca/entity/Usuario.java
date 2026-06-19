package com.cibertec.biblioteca.entity;

import java.util.Set;
import jakarta.persistence.*; // El asterisco jala @Entity, @JoinColumn, @ManyToMany, etc.
import lombok.Data;

@Entity @Table(name = "usuarios") @Data
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    @Column(unique = true) private String dni;
    @Column(unique = true) private String email;
    private String password;
    private Boolean estado = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_roles",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles;
}