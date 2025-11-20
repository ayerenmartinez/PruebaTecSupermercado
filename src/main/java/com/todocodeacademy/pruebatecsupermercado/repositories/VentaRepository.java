package com.todocodeacademy.pruebatecsupermercado.repositories;

import com.todocodeacademy.pruebatecsupermercado.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Long> {
}
