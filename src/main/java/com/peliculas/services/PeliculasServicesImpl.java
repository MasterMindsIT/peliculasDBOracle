package com.peliculas.services;

import java.util.List;


import org.springframework.stereotype.Service;

import com.peliculas.dtos.PeliculasDTO;
import com.peliculas.exceptions.PeliculasNotFoundException;
import com.peliculas.mappers.PeliculasMapper;
import com.peliculas.repositories.PeliculasRepository;
import com.peliculas.services.interfaces.IPeliculas;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class PeliculasServicesImpl implements IPeliculas{

    private final PeliculasRepository peliculasRepository;
    private final PeliculasMapper peliculasMapper;

    @Override
    public List<PeliculasDTO> getAllPeliculas() {
        return peliculasRepository.findAll().stream()
                .map(peliculasMapper::toDTO)
                .toList();
    }

    @Override
    public PeliculasDTO getPeliculaById(long id) {
        return peliculasMapper.toDTO(peliculasRepository.findById(id).orElseThrow(()->new PeliculasNotFoundException(id)));
    }

    @Override
    public PeliculasDTO createPelicula(PeliculasDTO pelicula) {
        return peliculasMapper.toDTO(peliculasRepository.save(peliculasMapper.toEntity(pelicula)));
    }

    @Override
    public PeliculasDTO updatePelicula(long id, PeliculasDTO pelicula) {
        return peliculasRepository.findById(id).map(p -> {
            p.setTitulo(pelicula.titulo());
            p.setDirector(pelicula.director());
            p.setAnio(pelicula.anio());
            return peliculasMapper.toDTO(peliculasRepository.save(p));
        }).orElseThrow(() -> new PeliculasNotFoundException(id));
    }

    @Override
    public boolean deletePelicula(long id) {
        return peliculasRepository.findById(id).map(p -> {
            peliculasRepository.delete(p);
            return true;
        }).orElseThrow(() -> new PeliculasNotFoundException(id));
    }

}
