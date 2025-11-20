package com.todocodeacademy.pruebatecsupermercado.dtos;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VentaDTO {
    private Long id;
    private LocalDate fecha;
    private String estado;
    private Double valor;
    private Long idSucursal;
    private List<DetalleVentaDTO> detalles;
    private Double total;
}
