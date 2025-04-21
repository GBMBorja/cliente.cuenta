package com.gbm.ms.persona.cliente.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Persona {

    @Column(name = "cliente_id", unique = false, nullable = false)
    private String clienteId;
    @Column(nullable = false)
    private String contrasena;
    @Column(nullable = false)
    private Boolean estado;
}