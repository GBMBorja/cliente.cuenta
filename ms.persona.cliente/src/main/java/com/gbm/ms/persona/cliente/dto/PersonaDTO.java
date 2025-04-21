package com.gbm.ms.persona.cliente.dto;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class PersonaDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 0, message = "La edad no puede ser negativa")
    private Integer edad;

    @NotBlank(message = "El género es obligatorio")
    @Pattern(regexp = "M|F", message = "El género debe ser 'M' o 'F'")
    private String genero;

    @NotBlank(message = "La identificación es obligatoria")
    private String identificacion;

    @NotBlank(message = "El teléfono es obligatorio")
    private String telefono;
}
