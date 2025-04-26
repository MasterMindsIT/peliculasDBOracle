package com.peliculas.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import com.peliculas.dtos.PeliculasDTO;
import com.peliculas.entities.PeliculasEntity;
import com.peliculas.exceptions.PeliculasNotFoundException;
import com.peliculas.mappers.PeliculasMapper;
import com.peliculas.repositories.PeliculasRepository;

@ExtendWith(MockitoExtension.class) // Extiende la clase de test para usar Mockito e inyectar los mocks
class PeliculasServicesImplTest {

    @Mock
    private PeliculasRepository peliculasRepository;

    @Mock
    private PeliculasMapper peliculasMapper;

    @InjectMocks
    private PeliculasServicesImpl peliculasService;

    private PeliculasEntity entity;
    private PeliculasDTO dto;

    @BeforeEach // se ejecuta antes de cada metodo de test
    void setUp() {
        entity = new PeliculasEntity(1L, "Matrix", 1999, "Wachowski", "Sci-fi", "Neo descubre la verdad");
        dto = new PeliculasDTO(1L, "Matrix", 1999, "Wachowski", "Sci-fi", "Neo descubre la verdad");
    }

    @Test
    void testGetAllPeliculas() {
        when(peliculasRepository.findAll(Sort.by(Sort.Direction.ASC, "titulo"))).thenReturn(List.of(entity));
        when(peliculasMapper.toDTO(entity)).thenReturn(dto);

        List<PeliculasDTO> result = peliculasService.getAllPeliculas();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).titulo()).isEqualTo("Matrix");
    }

    @Test
    void testGetPeliculaById() {
        when(peliculasRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(peliculasMapper.toDTO(entity)).thenReturn(dto);

        PeliculasDTO result = peliculasService.getPeliculaById(1L);

        assertThat(result.titulo()).isEqualTo("Matrix");
    }

    @Test
    void testCrearPelicula() {
        when(peliculasMapper.toEntity(dto)).thenReturn(entity);
        when(peliculasRepository.save(entity)).thenReturn(entity);
        when(peliculasMapper.toDTO(entity)).thenReturn(dto);

        PeliculasDTO result = peliculasService.createPelicula(dto);

        assertThat(result.titulo()).isEqualTo("Matrix");
    }

    @Test
    void testUpdatePelicula() {
        PeliculasEntity updated = new PeliculasEntity(1L, "Matrix Reloaded", 2003, "Wachowski", "Sci-fi", "Segunda parte");
        PeliculasDTO updatedDTO = new PeliculasDTO(1L, "Matrix Reloaded", 2003, "Wachowski", "Sci-fi", "Segunda parte");

        when(peliculasRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(peliculasRepository.save(any())).thenReturn(updated);
        when(peliculasMapper.toDTO(updated)).thenReturn(updatedDTO);

        PeliculasDTO result = peliculasService.updatePelicula(1L, updatedDTO);

        assertThat(result.titulo()).isEqualTo("Matrix Reloaded");
    }

    @Test
    void testDeletePelicula() {
        when(peliculasRepository.findById(1L)).thenReturn(Optional.of(entity));

        boolean result = peliculasService.deletePelicula(1L);

        assertThat(result).isTrue();
        verify(peliculasRepository).delete(entity);
    }

    @Test
    void testGetPeliculaNoId() {
        when(peliculasRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> peliculasService.getPeliculaById(99L))
                .isInstanceOf(PeliculasNotFoundException.class);
    }

    @Test
    void testUpdatePeliculaNotFound() {
        when(peliculasRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> peliculasService.updatePelicula(99L, dto))
                .isInstanceOf(PeliculasNotFoundException.class);
    }

    @Test
    void testDeletePeliculaNotFound() {
        when(peliculasRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> peliculasService.deletePelicula(99L))
                .isInstanceOf(PeliculasNotFoundException.class);
    }
}