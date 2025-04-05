package com.peliculas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor@AllArgsConstructor
@Table(name = "peliculas")
public class PeliculasEntity {
@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public long id;
    public String titulo;
    public int anio;
    public String director;
    public String genero;
    public String sinopsis;
}
