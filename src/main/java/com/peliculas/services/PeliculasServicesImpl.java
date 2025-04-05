package com.peliculas.services;

import java.util.List;


import org.springframework.stereotype.Service;

import com.peliculas.entities.PeliculasEntity;
import com.peliculas.exceptions.PeliculasNotFoundException;
import com.peliculas.repositories.PeliculasRepository;
import com.peliculas.services.interfaces.IPeliculas;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class PeliculasServicesImpl implements IPeliculas{

    private final PeliculasRepository peliculasRepository;

    @Override
    public List<PeliculasEntity> getAllPeliculas() {
        return peliculasRepository.findAll();
    }

    @Override
    public PeliculasEntity getPeliculaById(long id) {
        return peliculasRepository.findById(id).orElseThrow(()->new PeliculasNotFoundException(id));
    }

}
