package com.peliculas.dtos;

import jakarta.validation.constraints.*;

public record PeliculasDTO(
    Long id,

    @NotBlank(message = "El título no puede estar vacío")
    @Size(max = 255, message = "El título no puede tener más de 255 caracteres")
    String titulo,

    @NotNull(message = "El año no puede ser nulo")
    @Min(value = 1888, message = "El año debe ser mayor o igual a 1888") // Año del primer filme
    Integer anio,

    @NotBlank(message = "El director no puede estar vacío")
    @Size(max = 255, message = "El nombre del director no puede tener más de 255 caracteres")
    String director,

    @NotBlank(message = "El género no puede estar vacío")
    @Size(max = 100, message = "El género no puede tener más de 100 caracteres")
    String genero,

    @NotBlank(message = "La sinopsis no puede estar vacía")
    @Size(max = 1000, message = "La sinopsis no puede tener más de 1000 caracteres")
    String sinopsis
) {

}
