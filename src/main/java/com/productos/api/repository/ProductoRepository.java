package com.productos.api.repository;

import com.productos.api.model.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para acceso a datos de productos en MongoDB.
 * Extiende MongoRepository que provee métodos CRUD automáticos:
 * - save(): guardar o actualizar un documento
 * - findAll(): obtener todos los documentos
 * - findById(): buscar por ID
 * - deleteById(): eliminar por ID
 *
 * Spring Data MongoDB genera la implementación automáticamente en tiempo de ejecución.
 */
@Repository
public interface ProductoRepository extends MongoRepository<Producto, String> {
}
