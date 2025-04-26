package com.peliculas.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*; // Importa funciones para generar enlaces



import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.peliculas.controllers.PeliculasController;
import com.peliculas.dtos.PeliculasDTO;

/**
 * Esta clase convierte una entidad Pelicula en un EntityModel<Pelicula>
 * enriquecido con enlaces HATEOAS.
 * 
 * Implementa RepresentationModelAssembler, que es una interfaz de Spring
 * HATEOAS
 * para transformar objetos de dominio (Pelicula) en recursos RESTful
 * enriquecidos.
 */
@Component
public class PeliculaModelAssembler implements RepresentationModelAssembler<PeliculasDTO , EntityModel<PeliculasDTO >> {

    /**
     * Este método transforma una Pelicula en un EntityModel con enlaces.
     * Incluye:
     * ✅ self → Ver esta película
     * 🗑 delete → Eliminar la película
     * 🔁 update → Actualizar la película
     * 📋 all → Ver todas las películas
     */
    @Override
    public EntityModel<PeliculasDTO> toModel(PeliculasDTO  pelicula) {
        return EntityModel.of(
                pelicula, // Entidad original

                // Enlace al detalle de la película (GET /peliculas/{id})
                linkTo(methodOn(PeliculasController.class)
                        .getPelicula(pelicula.id()))
                        .withSelfRel(),

                // Enlace para eliminar (DELETE /peliculas/{id})
                linkTo(methodOn(PeliculasController.class)
                        .deletePelicula(pelicula.id()))
                        .withRel("delete"),

                // Enlace para actualizar (PUT /peliculas/{id}) – cuerpo ignorado aquí
                linkTo(methodOn(PeliculasController.class)
                        .updatePelicula(pelicula.id(), null))
                        .withRel("update"),

                // Enlace para ver todas las películas (GET /peliculas)
                linkTo(methodOn(PeliculasController.class)
                        .allPeliculas())
                        .withRel("all"));
    }
}
