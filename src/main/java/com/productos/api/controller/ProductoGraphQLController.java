package com.productos.api.controller;

import com.productos.api.dto.ProductoDTO;
import com.productos.api.service.ProductoService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Controlador GraphQL para la gestión de productos.
 * Expone consultas (Query) y mutaciones (Mutation) definidas en schema.graphqls.
 * Reutiliza la misma capa de servicio que la API REST (Principio DRY).
 */
@Controller
public class ProductoGraphQLController {

    private final ProductoService productoService;

    public ProductoGraphQLController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /**
     * Query GraphQL: productos
     * Obtiene la lista completa de productos.
     * @return lista de productos
     */
    @QueryMapping
    public List<ProductoDTO> productos() {
        return productoService.obtenerTodos();
    }

    /**
     * Query GraphQL: productoPorId
     * Obtiene un producto por su identificador.
     * @param id identificador del producto
     * @return el producto encontrado
     */
    @QueryMapping
    public ProductoDTO productoPorId(@Argument String id) {
        return productoService.obtenerPorId(id);
    }

    /**
     * Mutation GraphQL: crearProducto
     * Crea un nuevo producto.
     * @param input datos del producto a crear
     * @return el producto creado con su ID generado
     */
    @MutationMapping
    public ProductoDTO crearProducto(@Argument ProductoInput input) {
        ProductoDTO dto = new ProductoDTO();
        dto.setNombre(input.nombre());
        dto.setDescripcion(input.descripcion());
        dto.setPrecio(input.precio());
        return productoService.crear(dto);
    }

    /**
     * Mutation GraphQL: actualizarProducto
     * Actualiza un producto existente.
     * @param id identificador del producto a actualizar
     * @param input nuevos datos del producto
     * @return el producto actualizado
     */
    @MutationMapping
    public ProductoDTO actualizarProducto(@Argument String id, @Argument ProductoInput input) {
        ProductoDTO dto = new ProductoDTO();
        dto.setNombre(input.nombre());
        dto.setDescripcion(input.descripcion());
        dto.setPrecio(input.precio());
        return productoService.actualizar(id, dto);
    }

    /**
     * Mutation GraphQL: eliminarProducto
     * Elimina un producto por su ID.
     * @param id identificador del producto a eliminar
     * @return el ID del producto eliminado
     */
    @MutationMapping
    public String eliminarProducto(@Argument String id) {
        productoService.eliminar(id);
        return id;
    }

    /**
     * Record que representa el input de GraphQL para crear/actualizar productos.
     * Coincide con el tipo ProductoInput definido en el schema.
     */
    public record ProductoInput(String nombre, String descripcion, Double precio) {
    }
}
