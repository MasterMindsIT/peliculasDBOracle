package com.peliculas.responses;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonPropertyOrder({ "status", "cantidad", "timestamp" , "dataCollect"}) // Orden de respuestas json
public class ResponseWrapperList<T> { //la clase sera un envoltorio 
    private String status;
    private int cantidad;
    private LocalDateTime timestamp;
    private List<T> dataCollect;
    //sobre carga de metodo constructor para reutilizar el wrapper
    public ResponseWrapperList(String status, int cantidad, List<T> dataCollect){
        this.status = status;
        this.cantidad = cantidad;
        this.timestamp = LocalDateTime.now();
        this.dataCollect = dataCollect;
    }
}