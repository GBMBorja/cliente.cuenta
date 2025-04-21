package com.gbm.ms.persona.cliente;

import com.gbm.ms.persona.cliente.dto.ClienteDTO;
import com.gbm.ms.persona.cliente.mapper.ClienteMapper;
import com.gbm.ms.persona.cliente.model.Cliente;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ClienteMapperTest {

    @Test
    void shouldMapClienteToDTO() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Ana");
        cliente.setApellido("Gomez");
        cliente.setDireccion("Av. Principal");
        cliente.setEdad(25);
        cliente.setGenero("F");
        cliente.setIdentificacion("99999999");
        cliente.setTelefono("5551234");
        cliente.setClienteId("cli-01");
        cliente.setContrasena("secret");
        cliente.setEstado(true);

        ClienteDTO dto = ClienteMapper.toDTO(cliente);

        assertThat(dto).isNotNull();
        assertThat(dto.getNombre()).isEqualTo(cliente.getNombre());
        assertThat(dto.getClienteId()).isEqualTo(cliente.getClienteId());
    }

    @Test
    void shouldMapDTOToCliente() {
        ClienteDTO dto = new ClienteDTO();
        dto.setNombre("Ana");
        dto.setApellido("Gomez");
        dto.setDireccion("Av. Principal");
        dto.setEdad(25);
        dto.setGenero("F");
        dto.setIdentificacion("99999999");
        dto.setTelefono("5551234");
        dto.setClienteId("cli-01");
        dto.setContrasena("secret");
        dto.setEstado(true);

        Cliente cliente = ClienteMapper.toEntity(dto);

        assertThat(cliente).isNotNull();
        assertThat(cliente.getNombre()).isEqualTo(dto.getNombre());
        assertThat(cliente.getClienteId()).isEqualTo(dto.getClienteId());
    }
}
