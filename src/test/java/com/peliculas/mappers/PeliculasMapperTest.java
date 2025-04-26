package com.peliculas.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.peliculas.dtos.PeliculasDTO;
import com.peliculas.entities.PeliculasEntity;

class PeliculasMapperTest {

    private final PeliculasMapper mapper = PeliculasMapper.INSTANCE;

    @Test
    void testToDTO() {
        PeliculasEntity entity = new PeliculasEntity();
        entity.id = 1L;
        entity.titulo = "Matrix";
        entity.anio = 1999;
        entity.director = "Wachowski";
        entity.genero = "Sci-fi";
        entity.sinopsis = "Neo descubre la verdad";

        PeliculasDTO dto = mapper.toDTO(entity);

        assertThat(dto.id()).isEqualTo(1L);
        assertThat(dto.titulo()).isEqualTo("Matrix");
    }

    @Test
    void testToEntity() {
        PeliculasDTO dto = new PeliculasDTO(1L, "Matrix", 1999, "Wachowski", "Sci-fi", "Neo descubre la verdad");

        PeliculasEntity entity = mapper.toEntity(dto);

        assertThat(entity.id).isEqualTo(1L);
        assertThat(entity.titulo).isEqualTo("Matrix");
    }
    @Test
    void testToDTONull() {
        PeliculasDTO dto = mapper.toDTO(null);
        assertThat(dto).isNull();
    }

    @Test
    void testToEntityNull() {
        PeliculasEntity entity = mapper.toEntity(null);
        assertThat(entity).isNull();
    }
}
