package com.peliculas.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import com.peliculas.dtos.PeliculasDTO;
import com.peliculas.entities.PeliculasEntity;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PeliculasMapper {
PeliculasMapper INSTANCE = Mappers.getMapper(PeliculasMapper.class);

    // Mapear de Entity a DTO
    PeliculasDTO toDTO(PeliculasEntity entity);

    // Mapear de DTO a Entity
    PeliculasEntity toEntity(PeliculasDTO dto);
}
