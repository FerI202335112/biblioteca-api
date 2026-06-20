package com.cibertec.biblioteca.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> recursoNoEncontrado(RecursoNoEncontradoException ex) {
        return construirRespuesta(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> validacion(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("fecha", LocalDateTime.now());
        respuesta.put("status", HttpStatus.BAD_REQUEST.value());
        respuesta.put("mensaje", "Error de validación");
        respuesta.put("errores", errores);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> datosDuplicados(DataIntegrityViolationException ex) {
        return construirRespuesta(HttpStatus.CONFLICT, "No se puede guardar: existe un dato duplicado o una relación inválida");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> errorGeneral(Exception ex) {
        return construirRespuesta(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    private ResponseEntity<Map<String, Object>> construirRespuesta(HttpStatus status, String mensaje) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("fecha", LocalDateTime.now());
        respuesta.put("status", status.value());
        respuesta.put("mensaje", mensaje);
        return ResponseEntity.status(status).body(respuesta);
    }
}
