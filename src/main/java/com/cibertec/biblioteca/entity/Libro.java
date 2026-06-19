package com.cibertec.biblioteca.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.Set;

@Entity @Table(name = "libros") @Data
public class Libro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Column(unique = true) private String isbn;
    private Date fecha_publicacion;
    private Integer stock;

    @ManyToOne @JoinColumn(name = "editorial_id")
    private Editorial editorial;

    @ManyToOne @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    
    @ManyToMany @JoinTable(name = "libro_autor",
        joinColumns = @JoinColumn(name = "libro_id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private Set<Autor> autores;
}