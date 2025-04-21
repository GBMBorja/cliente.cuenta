package com.gbm.ms.cuenta.movimientos.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MovimientoDTO {

    @NotBlank(message = "El número de cuenta es obligatorio")
    private String numeroCuenta;

    @NotBlank(message = "El tipo de movimiento es obligatorio")
    @Pattern(regexp = "DEBITO|DEPOSITO", message = "El tipo de movimiento debe ser 'DEBITO' o 'DEPOSITO'")
    private String tipoMovimiento;

    @NotNull(message = "El valor es obligatorio")
    @Min(value = 0, message = "El valor no puede ser negativo")
    private Double valor;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @NotNull(message = "El saldo es obligatorio")
    private Double saldo;
}