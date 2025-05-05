package com.peliculas.controllers;

import org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peliculas.dtos.PeliculasDTO;
import com.peliculas.services.interfaces.IPeliculas; 
//@WebMvcTest(PeliculasController.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) // Desactiva seguridad en los tests
@Import(HypermediaAutoConfiguration.class)
public class PeliculasControllerTest {
     @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IPeliculas peliculasServicesImpl;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAllPeliculas_Con_Peliculas() throws Exception {
        // Crea un objeto PeliculasDTO para simular la respuesta del servicio
        PeliculasDTO pelicula = new PeliculasDTO(1L, "Matrix", 1999, "Wachowski", "Sci-fi", "Neo descubre la verdad");
        List<PeliculasDTO> peliculas = List.of(pelicula);

        Mockito.when(peliculasServicesImpl.getAllPeliculas()).thenReturn(peliculas);

        // When & Then
        mockMvc.perform(get("/peliculas")) // Asegúrate de que el controller tenga @RequestMapping("/api/peliculas") si no está en raíz
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Ok"))
                .andExpect(jsonPath("$.cantidad").value(1))
                .andExpect(jsonPath("$.dataCollect[0].titulo").value("Matrix"))
                .andExpect(jsonPath("$.dataCollect[0].links[?(@.rel=='self')].href").value("http://localhost/peliculas/1"))
                .andExpect(jsonPath("$.dataCollect[0].links[?(@.rel=='delete')].href").value("http://localhost/peliculas/1"))
                .andExpect(jsonPath("$.dataCollect[0].links[?(@.rel=='update')].href").value("http://localhost/peliculas/1"))
                .andExpect(jsonPath("$.dataCollect[0].links[?(@.rel=='all')].href").value("http://localhost/peliculas"));
                
    }

    @Test
    void testgetPelicula() throws Exception {   
         long id = 1L;
    PeliculasDTO pelicula = new PeliculasDTO(id, "Interestelar 2", 2020, "Christopher Nolan", "Ciencia ficción", "Otro grupo de astronautas...");

    Mockito.when(peliculasServicesImpl.getPeliculaById(id)).thenReturn(pelicula);

    mockMvc.perform(get("/peliculas/{id}", id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("Ok"))
            .andExpect(jsonPath("$.cantidad").value(1))
            .andExpect(jsonPath("$.data.titulo").value("Interestelar 2"))
            .andExpect(jsonPath("$.data.links[?(@.rel=='self')].href").value("http://localhost/peliculas/1"))
            .andExpect(jsonPath("$.data.links[?(@.rel=='delete')].href").value("http://localhost/peliculas/1"))
            .andExpect(jsonPath("$.data.links[?(@.rel=='update')].href").value("http://localhost/peliculas/1"))
            .andExpect(jsonPath("$.data.links[?(@.rel=='all')].href").value("http://localhost/peliculas"));


    }

   
     
    @Test
    void testAllPeliculas_Sin_Peliculas() throws Exception {
        Mockito.when(peliculasServicesImpl.getAllPeliculas()).thenReturn(List.of());

        mockMvc.perform(get("/peliculas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("204"))
                .andExpect(jsonPath("$.cantidad").value(0))
                .andExpect(jsonPath("$.mensaje").value("No se encontraron datos para mostrar"));
    }
 
    
    @Test
    void testCreatePelicula()  throws Exception{
        // Crea un objeto PeliculasDTO para simular la entrada
        PeliculasDTO pelicula = new PeliculasDTO(1L, "Matrix", 1999, "Wachowski", "Sci-fi", "Neo descubre la verdad");

        // Simula que al llamar al service con ese DTO, devuelve el mismo objeto
        Mockito.when(peliculasServicesImpl.createPelicula(Mockito.any(PeliculasDTO.class)))
           .thenReturn(pelicula);

        // Realiza la petición POST y verifica la respuesta
        mockMvc.perform(post("/peliculas") 
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(pelicula)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("Ok"))
        .andExpect(jsonPath("$.cantidad").value(1))
        .andExpect(jsonPath("$.data.titulo").value("Matrix"))
        .andExpect(jsonPath("$.data.links[?(@.rel=='self')].href").value("http://localhost/peliculas/1"))
        .andExpect(jsonPath("$.data.links[?(@.rel=='delete')].href").value("http://localhost/peliculas/1"))
        .andExpect(jsonPath("$.data.links[?(@.rel=='update')].href").value("http://localhost/peliculas/1"))
        .andExpect(jsonPath("$.data.links[?(@.rel=='all')].href").value("http://localhost/peliculas"));

    }

    @Test
    void testUpdatePelicula() throws Exception {
        long id = 1L;
        PeliculasDTO updatedPelicula = new PeliculasDTO(id, "Matrix Reloaded", 2003, "Wachowski", "Sci-fi", "Neo sigue luchando");

        // Mock del servicio: cuando se llame al update con ese ID y DTO, debe devolver el mismo DTO actualizado
        Mockito.when(peliculasServicesImpl.updatePelicula(eq(id), any(PeliculasDTO.class)))
           .thenReturn(updatedPelicula);

        // llama al endpoint PUT y verifica la respuesta
        mockMvc.perform(put("/peliculas/{id}", id) 
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedPelicula)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("Ok"))
        .andExpect(jsonPath("$.cantidad").value(1))
        .andExpect(jsonPath("$.data.titulo").value("Matrix Reloaded"))
        .andExpect(jsonPath("$.data.anio").value(2003))
        .andExpect(jsonPath("$.data.director").value("Wachowski"))
        .andExpect(jsonPath("$.data.links[0].href").value("http://localhost/peliculas/1"))
        .andExpect(jsonPath("$.data.links[1].href").value("http://localhost/peliculas/1"))
        .andExpect(jsonPath("$.data.links[2].href").value("http://localhost/peliculas/1"))
        .andExpect(jsonPath("$.data.links[3].href").value("http://localhost/peliculas"));
                                        // Sin ?(@.rel=='delete' y solo por indice de los enlaces)
    }
    
    @Test
    void testDeletePelicula_Encuentra_Id() throws Exception {
        long id = 1L;
        // Simula que el servicio devuelve true al eliminar la película
        Mockito.when(peliculasServicesImpl.deletePelicula(id)).thenReturn(true);

        // Realiza la petición DELETE y verifica la respuesta
        mockMvc.perform(delete("/peliculas/{id}", id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("Ok"))
            .andExpect(jsonPath("$.cantidad").value(1))
            .andExpect(jsonPath("$.mensaje").value("Pelicula ID=1 eliminada con exito"));
    }
    @Test
    void testDeletePelicula_No_Encuentra_Id() throws Exception {
        long id = 1L;
        // Simula que el servicio devuelve true al eliminar la película
        Mockito.when(peliculasServicesImpl.deletePelicula(id)).thenReturn(false);

        // Realiza la petición DELETE y verifica la respuesta
        mockMvc.perform(delete("/peliculas/{id}", id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("204"))
            .andExpect(jsonPath("$.cantidad").value(0))
            .andExpect(jsonPath("$.mensaje").value("No se encontro el ID=1 a eliminar"));
    } 
}
