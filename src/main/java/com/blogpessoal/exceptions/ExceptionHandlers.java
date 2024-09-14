package com.blogpessoal.exceptions;

import com.blogpessoal.dtos.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity duplicateEntry(DataIntegrityViolationException ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO("Already registered information","400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }



}
