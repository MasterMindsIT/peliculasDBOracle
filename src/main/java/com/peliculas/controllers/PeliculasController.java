package com.peliculas.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peliculas.entities.PeliculasEntity;
import com.peliculas.services.PeliculasServicesImpl;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("peliculas")
@AllArgsConstructor
public class PeliculasController {
    
    private final PeliculasServicesImpl peliculasServicesImpl;
        
        @GetMapping //No precisa ninguna ruta adicional, ya que se encuentra en la raiz por el metodo get
        public ResponseEntity<List<PeliculasEntity>> allPeliculas() {
            return ResponseEntity.ok(peliculasServicesImpl.getAllPeliculas());
        }
        
        @GetMapping("/{id}")// se le pasa el id de la pelicula que se quiere obtener y lo captura @PathVariable
        public ResponseEntity<PeliculasEntity> getPelicula(@PathVariable int id) {
            return ResponseEntity.ok(peliculasServicesImpl.getPeliculaById(id));
        }
    
    
}
