package com.gbm.ms.persona.cliente.mapper;

import com.gbm.ms.persona.cliente.dto.ClienteDTO;
import com.gbm.ms.persona.cliente.model.Cliente;

public class ClienteMapper {

    public static ClienteDTO toDTO(Cliente cliente) {
        if (cliente == null) return null;

        ClienteDTO dto = new ClienteDTO();
        dto.setNombre(cliente.getNombre());
        dto.setApellido(cliente.getApellido());
        dto.setDireccion(cliente.getDireccion());
        dto.setEdad(cliente.getEdad());
        dto.setGenero(cliente.getGenero());
        dto.setIdentificacion(cliente.getIdentificacion());
        dto.setTelefono(cliente.getTelefono());
        dto.setClienteId(cliente.getClienteId());
        dto.setContrasena(cliente.getContrasena());
        dto.setEstado(cliente.getEstado());

        return dto;
    }

    public static Cliente toEntity(ClienteDTO dto) {
        if (dto == null) return null;

        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setDireccion(dto.getDireccion());
        cliente.setEdad(dto.getEdad());
        cliente.setGenero(dto.getGenero());
        cliente.setIdentificacion(dto.getIdentificacion());
        cliente.setTelefono(dto.getTelefono());
        cliente.setClienteId(dto.getClienteId());
        cliente.setContrasena(dto.getContrasena());
        cliente.setEstado(dto.getEstado());

        return cliente;
    }
}
