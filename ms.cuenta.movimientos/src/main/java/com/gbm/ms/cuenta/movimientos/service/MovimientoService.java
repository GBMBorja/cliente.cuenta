package com.gbm.ms.cuenta.movimientos.service;

import com.gbm.ms.cuenta.movimientos.dto.MovimientoDTO;
import com.gbm.ms.cuenta.movimientos.model.Movimiento;

import com.gbm.ms.cuenta.movimientos.repository.MovimientoRepository;
import com.gbm.ms.cuenta.movimientos.repository.CuentaRepository;
import com.gbm.ms.cuenta.movimientos.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    @Autowired
    public MovimientoService(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
    }

    public Mono<ResponseEntity<ResponseWrapper<MovimientoDTO>>> realizarMovimiento(MovimientoDTO movimientoDTO) {
        return cuentaRepository.buscarPorNumeroCuenta(movimientoDTO.getNumeroCuenta())
                .flatMap(cuenta -> {
                    // Verificar si la cuenta tiene saldo suficiente para un débito
                    if (movimientoDTO.getTipoMovimiento().equals("DEBITO") && cuenta.getSaldoInicial() < movimientoDTO.getValor()) {
                        return Mono.just(ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(new ResponseWrapper<MovimientoDTO>("Saldo insuficiente para realizar el débito")));
                    }

                    // Actualizar saldo según tipo de movimiento
                    if (movimientoDTO.getTipoMovimiento().equals("DEBITO")) {
                        cuenta.setSaldoInicial(cuenta.getSaldoInicial() - movimientoDTO.getValor());
                    } else if (movimientoDTO.getTipoMovimiento().equals("DEPOSITO")) {
                        cuenta.setSaldoInicial(cuenta.getSaldoInicial() + movimientoDTO.getValor());
                    }

                    // Guardar el movimiento
                    Movimiento movimiento = new Movimiento();
                    movimiento.setFecha(movimientoDTO.getFecha());
                    movimiento.setTipoMovimiento(movimientoDTO.getTipoMovimiento());
                    movimiento.setValor(movimientoDTO.getValor());
                    movimiento.setSaldo(cuenta.getSaldoInicial());
                    movimiento.setNumeroCuenta(cuenta.getNumeroCuenta());

                    return movimientoRepository.guardar(movimiento)
                            .flatMap(savedMovimiento -> {
                                // Guardar la cuenta con el nuevo saldo
                                return cuentaRepository.guardar(cuenta)
                                        .map(savedCuenta -> ResponseEntity
                                                .status(HttpStatus.CREATED)
                                                .body(new ResponseWrapper<MovimientoDTO>(movimientoDTO)));
                            });
                })
                .switchIfEmpty(Mono.just(ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ResponseWrapper<MovimientoDTO>("Cuenta no encontrada con número: " + movimientoDTO.getNumeroCuenta()))))
                .onErrorResume(e -> Mono.just(ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ResponseWrapper<MovimientoDTO>("Error al realizar el movimiento: " + e.getMessage()))));
    }
}