package com.productos.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Documento (Entity) mapeado a la colección "productos" en MongoDB.
 * Spring Data MongoDB actúa como ODM (Object-Document Mapper).
 * Principio: Single Responsibility - esta clase solo representa la estructura de persistencia.
 */
@Document(collection = "productos")
public class Producto {

    @Id
    private String id;

    private String nombre;

    private String descripcion;

    private Double precio;

    public Producto() {
    }

    public Producto(String nombre, String descripcion, Double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
