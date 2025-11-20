package com.todocodeacademy.pruebatecsupermercado.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductoMasVendidoDTO {
    private Long productoId;
    private String  nombreProducto;
    private Integer cantidadVendida;
}
