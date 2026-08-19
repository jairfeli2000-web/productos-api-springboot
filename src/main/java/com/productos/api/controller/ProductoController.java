package com.productos.api.controller;

import com.productos.api.dto.ApiResponse;
import com.productos.api.dto.ProductoDTO;
import com.productos.api.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductoDTO>>> obtenerTodos() {
        List<ProductoDTO> productos = productoService.obtenerTodos();
        return ResponseEntity.ok(new ApiResponse<>("Productos obtenidos exitosamente", productos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductoDTO>> obtenerPorId(@PathVariable String id) {
        ProductoDTO producto = productoService.obtenerPorId(id);
        return ResponseEntity.ok(new ApiResponse<>("Producto encontrado exitosamente", producto));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductoDTO>> crear(@Valid @RequestBody ProductoDTO productoDTO) {
        ProductoDTO productoCreado = productoService.crear(productoDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Producto creado exitosamente", productoCreado));
    }

    @PostMapping("/batch")
    public ResponseEntity<ApiResponse<List<ProductoDTO>>> crearVarios(@Valid @RequestBody List<ProductoDTO> productosDTO) {
        List<ProductoDTO> productosCreados = productoService.crearVarios(productosDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Se crearon " + productosCreados.size() + " productos exitosamente", productosCreados));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductoDTO>> actualizar(@PathVariable String id,
                                                               @Valid @RequestBody ProductoDTO productoDTO) {
        ProductoDTO productoActualizado = productoService.actualizar(id, productoDTO);
        return ResponseEntity.ok(new ApiResponse<>("Producto actualizado exitosamente", productoActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> eliminar(@PathVariable String id) {
        productoService.eliminar(id);
        return ResponseEntity.ok(new ApiResponse<>("Producto eliminado exitosamente", id));
    }
}
