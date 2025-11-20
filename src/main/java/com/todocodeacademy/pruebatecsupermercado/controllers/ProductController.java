package com.todocodeacademy.pruebatecsupermercado.controllers;

import com.todocodeacademy.pruebatecsupermercado.dtos.ProductoDTO;
import com.todocodeacademy.pruebatecsupermercado.services.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/productos")
public class ProductController {

    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> traerProductos() {
        return ResponseEntity.ok(productoService.traerProductos());
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO productoDTO) {
        ProductoDTO crearProductoDTO = productoService.crearProducto(productoDTO);
        return ResponseEntity.created(URI.create("/api/productos"+crearProductoDTO.getId())).body(crearProductoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto( @PathVariable Long id,@RequestBody ProductoDTO productoDTO) {
        return ResponseEntity.ok(productoService.actualizarProducto(id,productoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

}
