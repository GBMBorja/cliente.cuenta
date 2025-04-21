package com.gbm.ms.cuenta.movimientos.mapper;

import com.gbm.ms.cuenta.movimientos.dto.MovimientoDTO;
import com.gbm.ms.cuenta.movimientos.model.Movimiento;

public class MovimientoMapper {

    public static MovimientoDTO toDTO(Movimiento movimiento) {
        MovimientoDTO dto = new MovimientoDTO();
        dto.setFecha(movimiento.getFecha());
        dto.setTipoMovimiento(movimiento.getTipoMovimiento());
        dto.setValor(movimiento.getValor());
        dto.setSaldo(movimiento.getSaldo());
        dto.setNumeroCuenta(movimiento.getNumeroCuenta());
        return dto;
    }

    public static Movimiento toEntity(MovimientoDTO dto) {
        Movimiento movimiento = new Movimiento();
        movimiento.setFecha(dto.getFecha());
        movimiento.setTipoMovimiento(dto.getTipoMovimiento());
        movimiento.setValor(dto.getValor());
        movimiento.setSaldo(dto.getSaldo());
        movimiento.setNumeroCuenta(dto.getNumeroCuenta());
        return movimiento;
    }
}