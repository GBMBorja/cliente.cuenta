package com.gbm.ms.persona.cliente;

import com.gbm.ms.persona.cliente.dto.PersonaDTO;
import com.gbm.ms.persona.cliente.mapper.PersonaMapper;
import com.gbm.ms.persona.cliente.model.Persona;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonaMapperTest {


    @Test
    void shouldMapPersonaToDTO() {
        // Given
        Persona persona = new Persona();
        persona.setNombre("Juan");
        persona.setApellido("Perez");
        persona.setDireccion("Calle 123");
        persona.setEdad(30);
        persona.setGenero("M");
        persona.setTelefono("12345678");
        persona.setIdentificacion("123456789");

        // When
        PersonaDTO personaDTO = PersonaMapper.toDTO(persona);


        // Then
        assertThat(personaDTO).isNotNull();
        assertThat(personaDTO.getNombre()).isEqualTo(persona.getNombre());
        assertThat(personaDTO.getApellido()).isEqualTo(persona.getApellido());
        assertThat(personaDTO.getDireccion()).isEqualTo(persona.getDireccion());
        assertThat(personaDTO.getEdad()).isEqualTo(persona.getEdad());
        assertThat(personaDTO.getGenero()).isEqualTo(persona.getGenero());
        assertThat(personaDTO.getIdentificacion()).isEqualTo(persona.getIdentificacion());
        assertThat(personaDTO.getTelefono()).isEqualTo(persona.getTelefono());
    }

    @Test
    void shouldMapDTOToPersona() {
        // Given
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setNombre("Juan");
        personaDTO.setApellido("Perez");
        personaDTO.setDireccion("Calle 123");
        personaDTO.setEdad(30);
        personaDTO.setGenero("M");
        personaDTO.setTelefono("12345678");
        personaDTO.setIdentificacion("123456789");
        // When
        Persona persona = PersonaMapper.toEntity(personaDTO);

        // Then
        assertThat(persona).isNotNull();
        assertThat(persona.getNombre()).isEqualTo(personaDTO.getNombre());
        assertThat(persona.getApellido()).isEqualTo(personaDTO.getApellido());
        assertThat(persona.getDireccion()).isEqualTo(personaDTO.getDireccion());
        assertThat(persona.getEdad()).isEqualTo(personaDTO.getEdad());
        assertThat(persona.getGenero()).isEqualTo(personaDTO.getGenero());
        assertThat(persona.getIdentificacion()).isEqualTo(personaDTO.getIdentificacion());
        assertThat(persona.getTelefono()).isEqualTo(personaDTO.getTelefono());
    }
}
