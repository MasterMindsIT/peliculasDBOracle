package com.peliculas.responses;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonPropertyOrder({ "status", "cantidad", "timestamp", "data" })
public class ResponseWrapperEntity<T>{
     private String status;
    private int cantidad;
    private LocalDateTime timestamp;
    private T data;
    public ResponseWrapperEntity(String status, int cantidad, T data) {
        this.status = status;
        this.cantidad = cantidad;
        this.timestamp = LocalDateTime.now();;
        this.data = data;
    }
    
}
