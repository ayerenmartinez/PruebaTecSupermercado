package com.todocodeacademy.pruebatecsupermercado.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaDTO {
    private Long id;
    private String nombreProducto;
    private Integer cantidadProducto;
    private Double precio;
    private Double subtotal;
}
