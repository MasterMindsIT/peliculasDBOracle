--Creacion de la tabla
CREATE TABLE if not exists peliculas (
    id SERIAL PRIMARY KEY, -- Generación automática de ID
    titulo VARCHAR(255) NOT NULL,
    anio INT NOT NULL,
    director VARCHAR(255) NOT NULL,
    genero VARCHAR(100) NOT NULL,
    sinopsis TEXT
);