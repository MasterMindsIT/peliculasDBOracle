package com.peliculas.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.peliculas.dtos.PeliculasDTO;
import com.peliculas.exceptions.PeliculasNotFoundException;
import com.peliculas.mappers.PeliculasMapper;
import com.peliculas.repositories.PeliculasRepository;
import com.peliculas.services.interfaces.IPeliculas;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@AllArgsConstructor
@Slf4j
public class PeliculasServicesImpl implements IPeliculas{

    private final PeliculasRepository peliculasRepository;
    private final PeliculasMapper peliculasMapper;

    @Override
    public List<PeliculasDTO> getAllPeliculas() {
        log.info("Service Peliculas getAll");
        Sort sort = Sort.by(Sort.Direction.ASC, "titulo"); //Elememnto por el que se ordena y esta definido en el repositorio danlo la carga a la base de datos
        return peliculasRepository.findAll(sort).stream()
                .map(peliculasMapper::toDTO)
                //.sorted(Comparator.comparing(PeliculasDTO::getTitulo)) // Ordenar por el campo "titulo" del DTO con la carga en spring y no la DB
                .toList();
    }

    @Override
    public PeliculasDTO getPeliculaById(long id) {
        log.info("Service Peliculas getPeliculaById");
        return peliculasMapper.toDTO(peliculasRepository.findById(id).orElseThrow(()->new PeliculasNotFoundException(id)));
    }

    @Override
    public PeliculasDTO createPelicula(PeliculasDTO pelicula) {
        log.info("Service Peliculas createPelicula");
        return peliculasMapper.toDTO(peliculasRepository.save(peliculasMapper.toEntity(pelicula)));
    }

    @Override
    public PeliculasDTO updatePelicula(long id, PeliculasDTO pelicula) {
        log.info("Service Peliculas updatePelicula");
        return peliculasRepository.findById(id).map(p -> {
            p.setTitulo(pelicula.titulo());
            p.setDirector(pelicula.director());
            p.setAnio(pelicula.anio());
            return peliculasMapper.toDTO(peliculasRepository.save(p));
        }).orElseThrow(() -> new PeliculasNotFoundException(id));
    }

    @Override
    public boolean deletePelicula(long id) {
        log.info("Service Peliculas deletePelicula");
        return peliculasRepository.findById(id).map(p -> {
            peliculasRepository.delete(p);
            return true;
        }).orElseThrow(() -> new PeliculasNotFoundException(id));
    }

}
