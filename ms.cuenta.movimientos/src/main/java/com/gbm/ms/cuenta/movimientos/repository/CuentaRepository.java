package com.gbm.ms.cuenta.movimientos.repository;

import com.gbm.ms.cuenta.movimientos.model.Cuenta;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CuentaRepository {
    Mono<Cuenta> guardar(Cuenta cuenta);
    Mono<Cuenta> buscarPorNumeroCuenta(String numeroCuenta);
    Flux<Cuenta> listarTodas();
    Mono<Void> eliminarPorNumeroCuenta(String numeroCuenta);
}
