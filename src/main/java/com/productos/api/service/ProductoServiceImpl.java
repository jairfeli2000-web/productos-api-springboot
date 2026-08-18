package com.productos.api.service;

import com.productos.api.dto.ProductoDTO;
import com.productos.api.exception.ProductoNotFoundException;
import com.productos.api.model.Producto;
import com.productos.api.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de productos.
 * Contiene la lógica de negocio y la conversión entre entidades y DTOs.
 * Principio: Single Responsibility - solo lógica de negocio de productos.
 * Principio: Open/Closed - se puede extender sin modificar la interfaz.
 */
@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    /**
     * Constructor con inyección de dependencias.
     * @param productoRepository repositorio de acceso a datos
     */
    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<ProductoDTO> obtenerTodos() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO obtenerPorId(String id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
        return convertirADTO(producto);
    }

    @Override
    public ProductoDTO crear(ProductoDTO productoDTO) {
        Producto producto = convertirAEntidad(productoDTO);
        Producto productoGuardado = productoRepository.save(producto);
        return convertirADTO(productoGuardado);
    }

    @Override
    public ProductoDTO actualizar(String id, ProductoDTO productoDTO) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        productoExistente.setNombre(productoDTO.getNombre());
        productoExistente.setDescripcion(productoDTO.getDescripcion());
        productoExistente.setPrecio(productoDTO.getPrecio());

        Producto productoActualizado = productoRepository.save(productoExistente);
        return convertirADTO(productoActualizado);
    }

    @Override
    public void eliminar(String id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
        productoRepository.delete(producto);
    }

    // --- Métodos privados de conversión ---

    /**
     * Convierte una entidad Producto a ProductoDTO.
     * @param producto entidad de persistencia
     * @return DTO para la capa de presentación
     */
    private ProductoDTO convertirADTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        return dto;
    }

    /**
     * Convierte un ProductoDTO a entidad Producto.
     * @param dto DTO recibido desde la capa de presentación
     * @return entidad lista para persistir
     */
    private Producto convertirAEntidad(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        return producto;
    }
}
