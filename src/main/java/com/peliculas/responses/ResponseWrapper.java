package com.peliculas.responses;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonPropertyOrder({ "status", "cantidad", "timestamp", "data" , "dataCollect", "mensaje"}) // Orden de respuestas json
public class ResponseWrapper<T> { //la clase sera un envoltorio 
    private String status;
    private int cantidad;
    private LocalDateTime timestamp;
    private List<T> dataCollect;
    private T data;
    private String mensaje;
    //sobre carga de metodo constructor para reutilizar el wrapper
    public ResponseWrapper(String status, int cantidad, List<T> dataCollect){
        this.status = status;
        this.cantidad = cantidad;
        this.timestamp = LocalDateTime.now();
        this.dataCollect = dataCollect;
    }
    //sobre carga de metodo constructor para reutilizar el wrapper
    public ResponseWrapper(String status, int cantidad, T data){
        this.status = status;
        this.cantidad = cantidad;
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }

    //sobre carga de metodo constructor para reutilizar el wrapper
    public ResponseWrapper(String status, int cantidad, String mensaje){
        this.status = status;
        this.cantidad = cantidad;
        this.timestamp = LocalDateTime.now();
        this.mensaje = mensaje;
    }

}
