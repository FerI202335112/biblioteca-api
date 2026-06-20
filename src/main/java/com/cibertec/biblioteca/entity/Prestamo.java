package com.cibertec.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "prestamos")
@Data
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha de préstamo es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha_prestamo;

    @NotNull(message = "La fecha de devolución esperada es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha_devolucion_esperada;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha_devolucion_real;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotNull(message = "El usuario es obligatorio")
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @NotNull(message = "El libro es obligatorio")
    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;
}
