package com.todocodeacademy.pruebatecsupermercado.controllers;

import com.todocodeacademy.pruebatecsupermercado.dtos.ProductoMasVendidoDTO;
import com.todocodeacademy.pruebatecsupermercado.services.EstadisticasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/estadisticas")
public class EstadisticasController {

    private final EstadisticasService estadisticasService;

    @GetMapping("/producto-mas-vendido")
    public ResponseEntity<ProductoMasVendidoDTO> obtenerProductoMasVendido() {
        ProductoMasVendidoDTO dto = estadisticasService.obtenerProductoMasVendido();
        return ResponseEntity.ok(dto);
    }

}
