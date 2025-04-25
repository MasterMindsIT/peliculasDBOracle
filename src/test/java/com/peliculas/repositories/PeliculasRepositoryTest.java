package com.peliculas.repositories;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import com.peliculas.entities.PeliculasEntity;

import java.util.List;
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
}
