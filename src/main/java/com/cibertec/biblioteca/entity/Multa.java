package com.cibertec.biblioteca.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity @Table(name = "multas") @Data
public class Multa {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double monto;
    private Date fecha_emision;
    private String estado;

    @OneToOne @JoinColumn(name = "prestamo_id")
    private Prestamo prestamo;
}