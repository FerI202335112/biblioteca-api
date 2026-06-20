package com.cibertec.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "multas")
@Data
public class Multa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El monto es obligatorio")
    @Min(value = 0, message = "El monto no puede ser negativo")
    private Double monto;

    @NotNull(message = "La fecha de emisión es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha_emision;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotNull(message = "El préstamo es obligatorio")
    @OneToOne
    @JoinColumn(name = "prestamo_id")
    private Prestamo prestamo;
}
