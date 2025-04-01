package com.peliculas.services.interfaces;

import java.util.List;

import com.peliculas.entities.PeliculasEntity;

public interface IPeliculas {
    List<PeliculasEntity> getAllPeliculas();
    PeliculasEntity getPeliculaById(long id);
}
