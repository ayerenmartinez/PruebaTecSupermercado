package com.todocodeacademy.pruebatecsupermercado.services;

import com.todocodeacademy.pruebatecsupermercado.dtos.ProductoDTO;
import com.todocodeacademy.pruebatecsupermercado.exceptions.NotFoundException;
import com.todocodeacademy.pruebatecsupermercado.mapper.Mapper;
import com.todocodeacademy.pruebatecsupermercado.models.Producto;
import com.todocodeacademy.pruebatecsupermercado.repositories.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService implements IProductoService {

    private final ProductoRepository productoRepository;

    @Override
    public List<ProductoDTO> traerProductos() {
        return productoRepository.findAll().stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDTO) {

        var  producto = Producto.builder()
                .nombre(productoDTO.getNombre())
                .precio(productoDTO.getPrecio())
                .categoria(productoDTO.getCategoria())
                .precio(productoDTO.getPrecio())
                .cantidad(productoDTO.getCantidad())
                .build();

        return Mapper.toDTO(productoRepository.save(producto));
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO ventaDTO) {
        var producto = productoRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Producto no encontrado"));

        producto.setNombre(ventaDTO.getNombre());
        producto.setPrecio(ventaDTO.getPrecio());
        producto.setCantidad(ventaDTO.getCantidad());
        producto.setCategoria(producto.getCategoria());

        return Mapper.toDTO(productoRepository.save(producto));
    }

    @Override
    public void eliminarProducto(Long id) {
        if(!productoRepository.existsById(id)){
            throw new NotFoundException("Producto no encontrado para eliminar");
        }
        productoRepository.deleteById(id);
    }

}
