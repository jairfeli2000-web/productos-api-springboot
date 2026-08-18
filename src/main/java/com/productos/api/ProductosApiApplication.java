package com.productos.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot.
 * Punto de entrada para iniciar el servidor y la configuración automática.
 */
@SpringBootApplication
public class ProductosApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductosApiApplication.class, args);
    }
}
