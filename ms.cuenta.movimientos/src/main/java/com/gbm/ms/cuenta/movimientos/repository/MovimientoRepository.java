package com.gbm.ms.cuenta.movimientos.repository;

import com.gbm.ms.cuenta.movimientos.model.Movimiento;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

public interface MovimientoRepository {
    Mono<Movimiento> guardar(Movimiento movimiento);
    Flux<Movimiento> listarPorCuenta(String numeroCuenta);
    Flux<Movimiento> listarTodos();
    Mono<Void> eliminarPorId(Long id);
}
