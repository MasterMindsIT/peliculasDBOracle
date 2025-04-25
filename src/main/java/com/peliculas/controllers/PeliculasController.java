package com.peliculas.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peliculas.dtos.PeliculasDTO;
import com.peliculas.responses.ResponseWrapperEntity;
import com.peliculas.responses.ResponseWrapperList;
import com.peliculas.responses.ResponseWrapperMessage;
import com.peliculas.services.interfaces.IPeliculas;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/peliculas")
@AllArgsConstructor
@Slf4j
public class PeliculasController {
    
    private final IPeliculas peliculasServicesImpl; //Usando inversion de control
        
        @GetMapping //No precisa ninguna ruta adicional, ya que se encuentra en la raiz por el metodo get
        public ResponseEntity<?> allPeliculas() {
            log.info("Controller Peliculas getAll");
            List<PeliculasDTO> list = peliculasServicesImpl.getAllPeliculas();
             if(list.isEmpty())
                return ResponseEntity.ok(new ResponseWrapperMessage<>("204", 0,  "No se encontraron datos para mostrar"));
            return ResponseEntity.ok( new ResponseWrapperList<PeliculasDTO>("Ok", list.size(), list));
        
        }
        
        @GetMapping("/{id}")// se le pasa el id de la pelicula que se quiere obtener y lo captura @PathVariable
        public  ResponseEntity<?> getPelicula(@PathVariable int id) {
            log.info("Controller Peliculas getPelicula");
            return ResponseEntity.ok( new ResponseWrapperEntity<PeliculasDTO>("Ok", 1, peliculasServicesImpl.getPeliculaById(id)));
        }
        
        @PostMapping
        public ResponseEntity<?> createPelicula(@Valid @RequestBody PeliculasDTO entity) {
            log.info("Controller Peliculas createPelicula");
            return ResponseEntity.ok( new ResponseWrapperEntity<PeliculasDTO>("Ok", 1, peliculasServicesImpl.createPelicula(entity)));
        }

        @PutMapping("/{id}")
        public ResponseEntity<?> updatePelicula(@PathVariable long id,@Valid @RequestBody PeliculasDTO entity) {
            log.info("Controller Peliculas updatePelicula");
            return ResponseEntity.ok( new ResponseWrapperEntity<PeliculasDTO>("Ok", 1, peliculasServicesImpl.updatePelicula(id, entity)));
        }
        
        @DeleteMapping("/{id}")
        public ResponseEntity<?> deletePelicula(@PathVariable long id) {
            log.info("Controller Peliculas deletePelicula");
            if(peliculasServicesImpl.deletePelicula(id))
                return ResponseEntity.ok( new ResponseWrapperMessage<>("Ok", 1, "Pelicula ID="+id+" eliminada con exito"));
            return ResponseEntity.ok(new ResponseWrapperMessage<>("204", 0,  "No se encontro el ID="+id+" a eliminar"));
        }
    
}
