package com.peliculas.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerExceptionsPersonalize {

    @ExceptionHandler(PeliculasNotFoundException.class)
    public ResponseEntity<Object> peliculasNotFoundException(PeliculasNotFoundException e){
        Map<String, Object> body = new HashMap<>();
        body.put("Error", "Not Found");
        body.put("Status", HttpStatus.NOT_FOUND.value());
        body.put("Mensaje", e);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

}
