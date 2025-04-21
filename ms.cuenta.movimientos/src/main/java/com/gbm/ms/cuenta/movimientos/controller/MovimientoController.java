package com.gbm.ms.cuenta.movimientos.controller;

import com.gbm.ms.cuenta.movimientos.dto.MovimientoDTO;
import com.gbm.ms.cuenta.movimientos.service.MovimientoService;
import com.gbm.ms.cuenta.movimientos.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;

    @Autowired
    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public Mono<ResponseEntity<ResponseWrapper<MovimientoDTO>>> realizarMovimiento(@RequestBody MovimientoDTO movimientoDTO) {
        return movimientoService.realizarMovimiento(movimientoDTO);
    }
}