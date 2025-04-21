package com.gbm.ms.cuenta.movimientos.repository;


import com.gbm.ms.cuenta.movimientos.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoJpaRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByNumeroCuenta(String numeroCuenta);
}