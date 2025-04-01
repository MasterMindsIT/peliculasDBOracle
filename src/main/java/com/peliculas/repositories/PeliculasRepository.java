package com.peliculas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peliculas.entities.PeliculasEntity;

public interface PeliculasRepository extends JpaRepository<PeliculasEntity, Long>{

}
