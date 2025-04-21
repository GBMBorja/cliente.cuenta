package com.gbm.ms.cuenta.movimientos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "movimientos")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    @Column(name = "tipo_movimiento", nullable = false)
    private String tipoMovimiento; // "DEBITO" o "DEPOSITO"

    private Double valor;

    private Double saldo;

    @Column(name = "numero_cuenta", nullable = false)
    private String numeroCuenta;
}