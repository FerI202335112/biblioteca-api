package com.cibertec.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "libros")
@Data
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotBlank(message = "El ISBN es obligatorio")
    @Column(unique = true)
    private String isbn;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha_publicacion;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "editorial_id")
    private Editorial editorial;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToMany
    @JoinTable(name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private Set<Autor> autores;
}
