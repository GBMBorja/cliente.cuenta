package com.gbm.ms.persona.cliente.model;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "persona")  // Nombre de la tabla en la base de datos
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private String apellido;

    private String direccion;

    private Integer edad;

    private String genero;

    @NotNull
    private String identificacion;

    private String telefono;

}