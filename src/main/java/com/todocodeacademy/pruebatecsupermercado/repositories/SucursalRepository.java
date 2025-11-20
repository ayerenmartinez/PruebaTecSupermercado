package com.todocodeacademy.pruebatecsupermercado.repositories;

import com.todocodeacademy.pruebatecsupermercado.models.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal,Long> {
}
