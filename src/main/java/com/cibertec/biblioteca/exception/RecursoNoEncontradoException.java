package com.cibertec.biblioteca.exception;

@SuppressWarnings("serial")
public class RecursoNoEncontradoException extends RuntimeException {
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
