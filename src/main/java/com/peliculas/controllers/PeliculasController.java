package com.peliculas.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peliculas.entities.PeliculasEntity;
import com.peliculas.responses.ResponseWrapper;
import com.peliculas.services.PeliculasServicesImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("peliculas")
@AllArgsConstructor
@Slf4j
public class PeliculasController {
    
    private final PeliculasServicesImpl peliculasServicesImpl;
        
        @GetMapping //No precisa ninguna ruta adicional, ya que se encuentra en la raiz por el metodo get
        public ResponseEntity<?> allPeliculas() {
            log.info("Controller Peliculas getAll");
            List<PeliculasEntity> list = peliculasServicesImpl.getAllPeliculas();
             if(list.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay peliculas para mostrar");
            return ResponseEntity.ok( new ResponseWrapper<PeliculasEntity>("Ok", list.size(), list));
        
        }
        
        @GetMapping("/{id}")// se le pasa el id de la pelicula que se quiere obtener y lo captura @PathVariable
        public ResponseEntity<PeliculasEntity> getPelicula(@PathVariable int id) {
            log.info("Controller Peliculas getPelicula");
            return ResponseEntity.ok(peliculasServicesImpl.getPeliculaById(id));

        }
    
    
}
