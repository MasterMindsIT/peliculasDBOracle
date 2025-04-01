package com.peliculas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class PeliculasApplication {

	public static void main(String[] args) {
		log.info("Iniciando el MS");
		SpringApplication.run(PeliculasApplication.class, args);
	}

}
