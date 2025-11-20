package com.todocodeacademy.pruebatecsupermercado.services;

import com.todocodeacademy.pruebatecsupermercado.dtos.SucursalDTO;

import java.util.List;

public interface ISucursalService {
    List<SucursalDTO> traerSucursales();
    SucursalDTO crearSucural(SucursalDTO sucursalDTO);
    SucursalDTO actualizarSucursal(Long id,SucursalDTO sucursalDTO);
    void eliminarSucursal(Long id);
}
