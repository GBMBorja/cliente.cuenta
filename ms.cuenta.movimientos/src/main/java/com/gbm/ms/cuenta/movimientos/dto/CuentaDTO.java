package com.gbm.ms.cuenta.movimientos.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuentaDTO {
    private Long id;
    @NotBlank(message = "El numeroCuenta es obligatorio")
    private String numeroCuenta;
    @NotBlank(message = "El tipoCuenta es obligatorio")
    private String tipoCuenta;
    @NotBlank(message = "El estado es obligatorio")
    private Boolean estado;
    @NotBlank(message = "El cliente ID es obligatorio")
    private String clienteId;
    @NotBlank(message = "El saldoInicial es obligatorio")
    private double saldoInicial ;
}
