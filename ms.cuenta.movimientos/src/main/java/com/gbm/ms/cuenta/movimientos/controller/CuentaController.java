package com.gbm.ms.cuenta.movimientos.controller;
import com.gbm.ms.cuenta.movimientos.dto.CuentaDTO;
import com.gbm.ms.cuenta.movimientos.service.CuentaService;
import com.gbm.ms.cuenta.movimientos.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    @Autowired
    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    // Endpoint para crear una cuenta
    @PostMapping
    public Mono<ResponseEntity<ResponseWrapper<CuentaDTO>>> crearCuenta(@RequestBody CuentaDTO cuentaDTO) {
        return cuentaService.crearCuenta(cuentaDTO);
    }

    // Endpoint para obtener una cuenta por número de cuenta
    @GetMapping("/{numeroCuenta}")
    public Mono<ResponseEntity<ResponseWrapper<CuentaDTO>>> buscarPorNumeroCuenta(@PathVariable String numeroCuenta) {
        return cuentaService.buscarPorNumeroCuenta(numeroCuenta);
    }

    // Endpoint para listar todas las cuentas
    @GetMapping
    public Flux<CuentaDTO> listarCuentas() {
        return cuentaService.listarCuentas();
    }

    // Endpoint para actualizar una cuenta
    @PutMapping("/{numeroCuenta}")
    public Mono<ResponseEntity<ResponseWrapper<CuentaDTO>>> actualizarCuenta(
            @PathVariable String numeroCuenta,
            @RequestBody CuentaDTO cuentaDTO) {
        return cuentaService.actualizarCuenta(numeroCuenta, cuentaDTO);
    }

    // Endpoint para eliminar una cuenta
    @DeleteMapping("/{numeroCuenta}")
    public Mono<ResponseEntity<ResponseWrapper<Object>>> eliminarCuenta(@PathVariable String numeroCuenta) {
        return cuentaService.eliminarCuenta(numeroCuenta);
    }
}