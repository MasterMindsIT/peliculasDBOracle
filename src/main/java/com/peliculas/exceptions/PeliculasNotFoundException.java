package com.peliculas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PeliculasNotFoundException extends RuntimeException{

    public PeliculasNotFoundException(long id) {
        super("No se encuentra la pelicula con ID="+id);
    }
    

}
