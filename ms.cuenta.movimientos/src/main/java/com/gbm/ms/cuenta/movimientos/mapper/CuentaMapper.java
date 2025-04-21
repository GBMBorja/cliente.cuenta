package com.gbm.ms.cuenta.movimientos.mapper;
import com.gbm.ms.cuenta.movimientos.dto.CuentaDTO;
import com.gbm.ms.cuenta.movimientos.model.Cuenta;

public class CuentaMapper {

    public static CuentaDTO toDTO(Cuenta cuenta) {
        if (cuenta == null) {
            return null;
        }
        CuentaDTO dto = new CuentaDTO();
        dto.setId(cuenta.getId());
        dto.setNumeroCuenta(cuenta.getNumeroCuenta());
        dto.setTipoCuenta(cuenta.getTipoCuenta());
        dto.setEstado(cuenta.getEstado());
        dto.setClienteId(cuenta.getClienteId());
        dto.setSaldoInicial(cuenta.getSaldoInicial());
        return dto;
    }

    public static Cuenta toEntity(CuentaDTO dto) {
        if (dto == null) {
            return null;
        }
        Cuenta cuenta = new Cuenta();
        cuenta.setId(dto.getId());
        cuenta.setNumeroCuenta(dto.getNumeroCuenta());
        cuenta.setTipoCuenta(dto.getTipoCuenta());
        cuenta.setEstado(dto.getEstado());
        cuenta.setClienteId(dto.getClienteId());
        cuenta.setSaldoInicial(dto.getSaldoInicial());
        return cuenta;
    }
}