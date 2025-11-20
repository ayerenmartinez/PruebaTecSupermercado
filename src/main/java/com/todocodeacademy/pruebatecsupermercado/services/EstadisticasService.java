package com.todocodeacademy.pruebatecsupermercado.services;

import com.todocodeacademy.pruebatecsupermercado.dtos.ProductoMasVendidoDTO;
import com.todocodeacademy.pruebatecsupermercado.models.Producto;
import com.todocodeacademy.pruebatecsupermercado.models.Venta;
import com.todocodeacademy.pruebatecsupermercado.repositories.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EstadisticasService {

    private final VentaRepository ventaRepository;

    public ProductoMasVendidoDTO obtenerProductoMasVendido(){
        List<Venta> ventas = ventaRepository.findAll();
        if (ventas.isEmpty()){
            throw new RuntimeException("No existen ventas registradas");
        }
        Map<Producto, Integer> cantidadesPorProducto = ventas.stream()
                .flatMap(venta -> venta.getDetalleVenta().stream())
                .collect(Collectors.groupingBy(
                        detalle -> detalle.getProducto(),
                        Collectors.summingInt(detalle -> detalle.getCantidad())
                ));
        Map.Entry<Producto, Integer> entry = cantidadesPorProducto.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new RuntimeException("No fue posible calcular el producto más vendido"));

        Producto producto = entry.getKey();
        Integer cantidadVendida = entry.getValue();

        return ProductoMasVendidoDTO.builder()
                .productoId(producto.getId())
                .nombreProducto(producto.getNombre())
                .cantidadVendida((int) cantidadVendida.doubleValue())
                .build();
    }

}
