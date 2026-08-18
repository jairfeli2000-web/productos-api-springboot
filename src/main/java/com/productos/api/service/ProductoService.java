package com.productos.api.service;

import com.productos.api.dto.ProductoDTO;

import java.util.List;

/**
 * Interfaz del servicio de productos.
 * Define el contrato de operaciones CRUD sin exponer la implementación.
 */
public interface ProductoService {

    List<ProductoDTO> obtenerTodos();

    ProductoDTO obtenerPorId(String id);

    ProductoDTO crear(ProductoDTO productoDTO);

    ProductoDTO actualizar(String id, ProductoDTO productoDTO);

    void eliminar(String id);
}
