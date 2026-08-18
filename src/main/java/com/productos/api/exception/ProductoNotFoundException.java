package com.productos.api.exception;

/**
 * Excepción personalizada lanzada cuando un producto no se encuentra en la base de datos.
 */
public class ProductoNotFoundException extends RuntimeException {

    public ProductoNotFoundException(String id) {
        super("No se encontró el producto con id: " + id);
    }
}
