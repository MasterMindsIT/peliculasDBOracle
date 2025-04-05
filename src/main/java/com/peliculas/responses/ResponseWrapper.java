package com.peliculas.responses;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonPropertyOrder({ "status", "cantidad", "timestamp", "data" }) // Orden de respuestas json
public class ResponseWrapper<T> { //la clase sera un envoltorio 
    private String status;
    private int cantidad;
    private LocalDateTime timestamp;
    private List<T> data;

    public ResponseWrapper(String status, int cantidad, List<T> data){
        this.status = status;
        this.cantidad = cantidad;
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }

}
