package com.todocodeacademy.pruebatecsupermercado.mapper;

import com.todocodeacademy.pruebatecsupermercado.dtos.DetalleVentaDTO;
import com.todocodeacademy.pruebatecsupermercado.dtos.ProductoDTO;
import com.todocodeacademy.pruebatecsupermercado.dtos.SucursalDTO;
import com.todocodeacademy.pruebatecsupermercado.dtos.VentaDTO;
import com.todocodeacademy.pruebatecsupermercado.models.Producto;
import com.todocodeacademy.pruebatecsupermercado.models.Sucursal;
import com.todocodeacademy.pruebatecsupermercado.models.Venta;

import java.util.List;

public class Mapper {

    // =======================
    // PRODUCTO → DTO
    // =======================

    public static ProductoDTO toDTO(Producto producto) {
        if(producto == null) return null;
        return ProductoDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .categoria(producto.getCategoria())
                .precio(producto.getPrecio())
                .cantidad(producto.getCantidad())
                .build();
    }

    // =======================
    // VENTA → DTO
    // =======================
    public static VentaDTO toDTO(Venta venta) {
        if(venta == null) return null;
        var detalles = mapDetalles(venta);
        var total = calcularTotal(detalles);
        return VentaDTO.builder()
                .id(venta.getId())
                .fecha(venta.getFecha())
                .idSucursal(venta.getSucursal().getId())
                .estado(venta.getEstado())
                .detalles(detalles)
                .total(total)
                .build();
    }

    private static List<DetalleVentaDTO> mapDetalles(Venta venta) {
        return venta.getDetalleVenta().stream()
                .map(detalleVenta -> {
                    var producto = detalleVenta.getProducto();
                    double subtotal = detalleVenta.getPrecio() * detalleVenta.getCantidad();

                    return DetalleVentaDTO.builder()
                            .id(producto.getId())
                            .nombreProducto(producto.getNombre())
                            .cantidadProducto(detalleVenta.getCantidad())
                            .precio(producto.getPrecio())
                            .subtotal(subtotal)
                            .build();
                })
                .toList();
    }

    private static double calcularTotal(List<DetalleVentaDTO> detalles) {
        return detalles.stream()
                .mapToDouble(DetalleVentaDTO::getSubtotal)
                .sum();
    }

    // =======================
    // SUCURSAL → DTO
    // =======================
    public static SucursalDTO toDTO(Sucursal sucursal) {
        if(sucursal == null) return null;
        return SucursalDTO.builder()
                .id(sucursal.getId())
                .nombre(sucursal.getNombre())
                .direccion(sucursal.getDireccion())
                .build();
    }


}
