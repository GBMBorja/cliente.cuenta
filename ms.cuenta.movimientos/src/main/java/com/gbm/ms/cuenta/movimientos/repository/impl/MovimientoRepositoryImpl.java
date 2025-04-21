package com.gbm.ms.cuenta.movimientos.repository.impl;

import com.gbm.ms.cuenta.movimientos.model.Movimiento;
import com.gbm.ms.cuenta.movimientos.repository.MovimientoRepository;
import com.gbm.ms.cuenta.movimientos.repository.MovimientoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Repository
public class MovimientoRepositoryImpl implements MovimientoRepository {

    private final MovimientoJpaRepository movimientoJpaRepository;

    @Autowired
    public MovimientoRepositoryImpl(MovimientoJpaRepository movimientoJpaRepository) {
        this.movimientoJpaRepository = movimientoJpaRepository;
    }

    @Override
    public Mono<Movimiento> guardar(Movimiento movimiento) {
        return Mono.fromCallable(() -> movimientoJpaRepository.save(movimiento))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<Movimiento> listarPorCuenta(String numeroCuenta) {
        return Mono.fromCallable(() -> movimientoJpaRepository.findByNumeroCuenta(numeroCuenta))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMapMany(Flux::fromIterable);
    }

    @Override
    public Flux<Movimiento> listarTodos() {
        return Mono.fromCallable(() -> movimientoJpaRepository.findAll())
                .subscribeOn(Schedulers.boundedElastic())
                .flatMapMany(Flux::fromIterable);
    }

    @Override
    public Mono<Void> eliminarPorId(Long id) {
        return Mono.fromRunnable(() -> movimientoJpaRepository.deleteById(id))
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }
}