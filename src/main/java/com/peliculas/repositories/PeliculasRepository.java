package com.peliculas.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peliculas.entities.PeliculasEntity;
@Repository
public interface PeliculasRepository extends JpaRepository<PeliculasEntity, Long>{
    List<PeliculasEntity> findAll(Sort sort);
}
