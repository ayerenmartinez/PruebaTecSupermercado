package com.todocodeacademy.pruebatecsupermercado.services;

import com.todocodeacademy.pruebatecsupermercado.dtos.ProductoDTO;

import java.util.List;

public interface IProductoService {
    List<ProductoDTO> traerProductos();
    ProductoDTO crearProducto(ProductoDTO ventaDTO);
    ProductoDTO actualizarProducto(Long id,ProductoDTO ventaDTO);
    void eliminarProducto(Long id);
}
