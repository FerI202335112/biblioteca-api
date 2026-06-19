package com.cibertec.biblioteca.entity;

import java.util.Date;
import jakarta.persistence.*;
import lombok.Data;

@Entity @Table(name = "prestamos") @Data
public class Prestamo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fecha_prestamo;
    private Date fecha_devolucion_esperada;
    private Date fecha_devolucion_real;
    private String estado;

    @ManyToOne @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne @JoinColumn(name = "libro_id")
    private Libro libro;
}