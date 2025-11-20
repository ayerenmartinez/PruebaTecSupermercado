package com.todocodeacademy.pruebatecsupermercado.services;

import com.todocodeacademy.pruebatecsupermercado.dtos.SucursalDTO;
import com.todocodeacademy.pruebatecsupermercado.exceptions.NotFoundException;
import com.todocodeacademy.pruebatecsupermercado.mapper.Mapper;
import com.todocodeacademy.pruebatecsupermercado.models.Sucursal;
import com.todocodeacademy.pruebatecsupermercado.repositories.SucursalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SucursalService implements ISucursalService{

    private final SucursalRepository sucursalRepository;

    @Override
    public List<SucursalDTO> traerSucursales() {
        return sucursalRepository.findAll()
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public SucursalDTO crearSucural(SucursalDTO sucursalDTO) {
        var sucursal = Sucursal.builder()
                .id(sucursalDTO.getId())
                .nombre(sucursalDTO.getNombre())
                .direccion(sucursalDTO.getDireccion())
                .build();
        return Mapper.toDTO(sucursalRepository.save(sucursal));
    }

    @Override
    public SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDTO) {
        var sucursal = sucursalRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Sucursal no encontrado"));
        sucursal.setNombre(sucursalDTO.getNombre());
        sucursal.setDireccion(sucursalDTO.getDireccion());
        return Mapper.toDTO(sucursalRepository.save(sucursal));
    }

    @Override
    public void eliminarSucursal(Long id) {
        if (!sucursalRepository.existsById(id)) throw new NotFoundException("Sucursal no encontrado");
        sucursalRepository.deleteById(id);
    }
}
