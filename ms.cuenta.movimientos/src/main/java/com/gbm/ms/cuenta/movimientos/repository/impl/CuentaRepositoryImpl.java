package com.gbm.ms.cuenta.movimientos.repository.impl;

import com.gbm.ms.cuenta.movimientos.model.Cuenta;
import com.gbm.ms.cuenta.movimientos.repository.CuentaRepository;
import com.gbm.ms.cuenta.movimientos.repository.CuentaJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Repository
public class CuentaRepositoryImpl implements CuentaRepository {

    private final CuentaJpaRepository cuentaJpaRepository;

    @Autowired
    public CuentaRepositoryImpl(CuentaJpaRepository cuentaJpaRepository) {
        this.cuentaJpaRepository = cuentaJpaRepository;
    }

    @Override
    public Mono<Cuenta> guardar(Cuenta cuenta) {
        return Mono.fromCallable(() -> cuentaJpaRepository.save(cuenta))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Cuenta> buscarPorNumeroCuenta(String numeroCuenta) {
        return Mono.fromCallable(() -> cuentaJpaRepository.findByNumeroCuenta(numeroCuenta))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(optional -> optional.map(Mono::just).orElseGet(Mono::empty));
    }

    @Override
    public Flux<Cuenta> listarTodas() {
        return Mono.fromCallable(() -> cuentaJpaRepository.findAll())
                .subscribeOn(Schedulers.boundedElastic())
                .flatMapMany(Flux::fromIterable);
    }

    @Override
    public Mono<Void> eliminarPorNumeroCuenta(String numeroCuenta) {
        return buscarPorNumeroCuenta(numeroCuenta)
                .flatMap(cuenta -> Mono.fromRunnable(() -> cuentaJpaRepository.delete(cuenta))
                        .subscribeOn(Schedulers.boundedElastic()))
                .then(); // Retorna Mono<Void>
    }
}
