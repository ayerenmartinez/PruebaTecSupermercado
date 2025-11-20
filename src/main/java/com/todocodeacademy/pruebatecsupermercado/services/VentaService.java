package com.todocodeacademy.pruebatecsupermercado.services;

import com.todocodeacademy.pruebatecsupermercado.dtos.DetalleVentaDTO;
import com.todocodeacademy.pruebatecsupermercado.dtos.VentaDTO;
import com.todocodeacademy.pruebatecsupermercado.exceptions.NotFoundException;
import com.todocodeacademy.pruebatecsupermercado.mapper.Mapper;
import com.todocodeacademy.pruebatecsupermercado.models.DetalleVenta;
import com.todocodeacademy.pruebatecsupermercado.models.Producto;
import com.todocodeacademy.pruebatecsupermercado.models.Sucursal;
import com.todocodeacademy.pruebatecsupermercado.models.Venta;
import com.todocodeacademy.pruebatecsupermercado.repositories.ProductoRepository;
import com.todocodeacademy.pruebatecsupermercado.repositories.SucursalRepository;
import com.todocodeacademy.pruebatecsupermercado.repositories.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaService implements IVentaService{

    private final VentaRepository ventaRepository;

    private final ProductoRepository productoRepository;

    private final SucursalRepository sucursalRepository;

    @Override
    public List<VentaDTO> traerVentas() {
        return ventaRepository.findAll().stream()
                .map(Mapper::toDTO)
                .toList();
    }

    /*Forma imperactiva
        @Override
        public List<VentaDTO> traerVentas() {
            List<Venta> ventas = ventaRepository.findAll();
            List<VentaDTO> ventasDTO = new ArrayList<>();
            VentaDTO ventaDTO;
            for(Venta venta : ventas){
                 ventaDTO = Mapper.toDTO(venta);
                 ventasDTO.add(ventaDTO);
            }
            return ventasDTO;
        }
        */
    @Override
    public VentaDTO crearVenta(VentaDTO ventaDTO) {

        if (ventaDTO == null) throw new RuntimeException("VentaDTO es nulo");
        if (ventaDTO.getIdSucursal() == null) throw new RuntimeException("Debe indicar la sucursal");
        if(ventaDTO.getDetalles() == null || ventaDTO.getDetalles().isEmpty())
            throw new RuntimeException("Debe incluir al menos un producto");

        Sucursal sucursal = sucursalRepository.findById(ventaDTO.getIdSucursal()).orElse(null);
        if(sucursal == null) throw new NotFoundException("Sucursal no encontrado");

        Venta venta = new Venta();
        venta.setFecha(ventaDTO.getFecha());
        venta.setEstado(ventaDTO.getEstado());
        venta.setSucursal(sucursal);
        venta.setTotal(ventaDTO.getTotal());

        List<DetalleVenta> detalles = new ArrayList<>();
        Double totalCalculado = 0.0;

        for (DetalleVentaDTO detalleVentaDTO : ventaDTO.getDetalles()) {
            Producto producto = productoRepository.findByNombre(detalleVentaDTO.getNombreProducto()).orElse(null);
            if(producto == null) throw new NotFoundException("Producto no encontrado: "+detalleVentaDTO.getNombreProducto());
            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setProducto(producto);
            detalleVenta.setPrecio(detalleVentaDTO.getPrecio());
            detalleVenta.setCantidad(detalleVentaDTO.getCantidadProducto());
            detalleVenta.setVenta(venta);
            detalles.add(detalleVenta);
            totalCalculado+=detalleVenta.getPrecio()*detalleVenta.getCantidad();
        }

        venta.setDetalleVenta(detalles);
        return Mapper.toDTO(ventaRepository.save(venta));
    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDTO) {
        Venta venta = ventaRepository.findById(id).orElse(null);
        if (venta == null) throw new NotFoundException("Venta no encontrado");

        if (ventaDTO.getFecha()!=null){
            venta.setFecha(ventaDTO.getFecha());
        }

        if (ventaDTO.getEstado()!=null){
            venta.setEstado(ventaDTO.getEstado());
        }

        if (ventaDTO.getTotal()!=null){
            venta.setTotal(ventaDTO.getTotal());
        }

        if (ventaDTO.getIdSucursal()!=null){
           Sucursal sucursal = sucursalRepository.findById(ventaDTO.getIdSucursal()).orElse(null);
           if (sucursal == null) throw new NotFoundException("Sucursal no encontrado");
           venta.setSucursal(sucursal);

        }

        return Mapper.toDTO(ventaRepository.save(venta));
    }

    @Override
    public void eliminarVenta(Long id) {
        Venta venta = ventaRepository.findById(id).orElse(null);
        if (venta == null) throw new NotFoundException("Venta no encontrado");
        ventaRepository.delete(venta);
    }
}
