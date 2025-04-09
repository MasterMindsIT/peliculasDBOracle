package com.peliculas.services.interfaces;

import java.util.List;

import com.peliculas.dtos.PeliculasDTO;

public interface IPeliculas {
    List<PeliculasDTO> getAllPeliculas();
    PeliculasDTO getPeliculaById(long id);
    PeliculasDTO createPelicula(PeliculasDTO pelicula);
    PeliculasDTO updatePelicula(long id, PeliculasDTO pelicula);
    boolean deletePelicula(long id);
}
