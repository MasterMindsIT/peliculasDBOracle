package com.peliculas.repositories;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import com.peliculas.entities.PeliculasEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
//@AutoConfigureMockMvc(addFilters = false) // Desactiva los filtros de seguridad
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // Usa H2 si da error security
public class PeliculasRepositoryTest {


    @Autowired
    private PeliculasRepository peliculasRepository;
    
    @Test
    void testFindAll() {
        PeliculasEntity pelicula1 = new PeliculasEntity(0L, "Matrix", 1999, "Wachowski", "Sci-fi", "Neo descubre la verdad");
        PeliculasEntity pelicula2 = new PeliculasEntity(0L, "Avatar", 2009, "James Cameron", "Sci-fi", "Pandora y los Na'vi");

        peliculasRepository.save(pelicula1);
        peliculasRepository.save(pelicula2);

        // When
        List<PeliculasEntity> peliculas = peliculasRepository.findAll(Sort.by("anio"));

       
       // Then
       assertThat(peliculas).hasSize(2);
       assertThat(peliculas.get(0).getTitulo()).isEqualTo("Matrix");
    }
    
    @Test
    void testGetById() {
        PeliculasEntity pelicula = new PeliculasEntity(0L, "Gladiador", 2000, "Ridley Scott", "Acción", "Roma y venganza");
        PeliculasEntity guardada = peliculasRepository.save(pelicula);

        Optional<PeliculasEntity> encontrada = peliculasRepository.findById(guardada.getId());

        assertThat(encontrada).isPresent();
        assertThat(encontrada.get().getTitulo()).isEqualTo("Gladiador");
    }

    @Test
    void testGetNoId() {
        Optional<PeliculasEntity> resultado = peliculasRepository.findById(999L);
        assertThat(resultado).isEmpty();
    }
    
    @Test
    void testCrearPelicula() {
        //  Test para guardar pelicula
        PeliculasEntity nuevaPelicula = new PeliculasEntity(0L, "Inception", 2010, "Christopher Nolan", "Sci-fi", "Sueños dentro de sueños");

        // Guardar la película
        PeliculasEntity guardada = peliculasRepository.save(nuevaPelicula);

        //verificaciones
        assertThat(guardada.getId()).isNotNull();
        assertThat(guardada.getTitulo()).isEqualTo("Inception");
    }

    @Test
    void testActualizarPelicula() {
        PeliculasEntity pelicula = new PeliculasEntity(0L, "Old Title", 2000, "Director", "Género", "Descripción");
        PeliculasEntity guardada = peliculasRepository.save(pelicula);

        guardada.setTitulo("New Title");
        peliculasRepository.save(guardada);

        Optional<PeliculasEntity> actualizada = peliculasRepository.findById(guardada.getId());

        assertThat(actualizada).isPresent();
        assertThat(actualizada.get().getTitulo()).isEqualTo("New Title");
    }


    @Test
    void testEliminarPorId() {
        PeliculasEntity pelicula = new PeliculasEntity(0L, "Titanic", 1997, "James Cameron", "Romance", "Hundimiento del barco");
        PeliculasEntity guardada = peliculasRepository.save(pelicula);

        peliculasRepository.deleteById(guardada.getId());
        Optional<PeliculasEntity> resultado = peliculasRepository.findById(guardada.getId());
        assertThat(resultado).isEmpty();
    }
  


}
