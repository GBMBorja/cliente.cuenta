package com.gbm.ms.persona.cliente.mapper;

import com.gbm.ms.persona.cliente.dto.PersonaDTO;
import com.gbm.ms.persona.cliente.model.Persona;

public class PersonaMapper {

    // Método para convertir Persona a PersonaDTO
    public static PersonaDTO toDTO(Persona persona) {
        if (persona == null) {
            return null;
        }

        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setNombre(persona.getNombre());
        personaDTO.setApellido(persona.getApellido());
        personaDTO.setDireccion(persona.getDireccion());
        personaDTO.setEdad(persona.getEdad());
        personaDTO.setGenero(persona.getGenero());
        personaDTO.setIdentificacion(persona.getIdentificacion());
        personaDTO.setTelefono(persona.getTelefono());

        return personaDTO;
    }

    // Método para convertir PersonaDTO a Persona
    public static Persona toEntity(PersonaDTO personaDTO) {
        if (personaDTO == null) {
            return null;
        }

        Persona persona = new Persona();
        persona.setNombre(personaDTO.getNombre());
        persona.setApellido(personaDTO.getApellido());
        persona.setDireccion(personaDTO.getDireccion());
        persona.setEdad(personaDTO.getEdad());
        persona.setGenero(personaDTO.getGenero());
        persona.setIdentificacion(personaDTO.getIdentificacion());
        persona.setTelefono(personaDTO.getTelefono());

        return persona;
    }
}
