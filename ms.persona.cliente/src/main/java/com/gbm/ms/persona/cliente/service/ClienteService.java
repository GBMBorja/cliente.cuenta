package com.gbm.ms.persona.cliente.service;

import com.gbm.ms.persona.cliente.dto.ClienteDTO;
import com.gbm.ms.persona.cliente.util.ResponseWrapper;
import com.gbm.ms.persona.cliente.mapper.ClienteMapper;
import com.gbm.ms.persona.cliente.model.Cliente;
import com.gbm.ms.persona.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ResponseEntity<ResponseWrapper<ClienteDTO>> buscarPorClienteId(String clienteId) {
        try {
            return clienteRepository.findByClienteId(clienteId)
                    .map(ClienteMapper::toDTO)
                    .map(clienteDTO -> ResponseEntity.ok(new ResponseWrapper<>(clienteDTO))) // Wrap the DTO in ResponseWrapper
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new ResponseWrapper<>("Cliente con ID " + clienteId + " no encontrado")));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseWrapper<>("Hubo un error al buscar el cliente: " + e.getMessage()));
        }
    }

    public ResponseEntity<ResponseWrapper<ClienteDTO>> crearCliente(ClienteDTO clienteDTO) {
        try {
            Cliente cliente = ClienteMapper.toEntity(clienteDTO);
            Cliente savedCliente = clienteRepository.save(cliente);
            ClienteDTO savedClienteDTO = ClienteMapper.toDTO(savedCliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>(savedClienteDTO)); // Wrap the DTO
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseWrapper<>("Hubo un error al crear el cliente: " + e.getMessage()));
        }
    }

    public ResponseEntity<ResponseWrapper<ClienteDTO>> actualizarCliente(String clienteId, ClienteDTO clienteDTO) {
        try {
            clienteDTO.setClienteId(clienteDTO.getIdentificacion());
            return clienteRepository.findByClienteId(clienteId)
                    .map(existing -> {
                        Cliente actualizado = ClienteMapper.toEntity(clienteDTO);
                        actualizado.setId(existing.getId()); // Mantener el ID original
                        Cliente updatedCliente = clienteRepository.save(actualizado);
                        ClienteDTO updatedClienteDTO = ClienteMapper.toDTO(updatedCliente);
                        return ResponseEntity.ok(new ResponseWrapper<>(updatedClienteDTO)); // Wrap the DTO
                    })
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new ResponseWrapper<>("Cliente con ID " + clienteId + " no encontrado")));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseWrapper<>("Hubo un error al actualizar el cliente: " + e.getMessage()));
        }
    }

    // Método para eliminar cliente
    public ResponseEntity<ResponseWrapper<String>> eliminarCliente(String clienteId) {
        try {
            Optional<Cliente> clienteOptional = clienteRepository.findByClienteId(clienteId);

            if (clienteOptional.isPresent()) {
                Cliente cliente = clienteOptional.get();
                clienteRepository.delete(cliente);
                // Retorno una respuesta de éxito con el mensaje adecuado
                return ResponseEntity.ok(new ResponseWrapper<>("Cliente con ID " + clienteId + " eliminado correctamente"));
            } else {
                // Si no se encuentra el cliente, retorno un mensaje de error con código 404
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseWrapper<>("Cliente con ID " + clienteId + " no encontrado"));
            }
        } catch (Exception e) {
            // Si ocurre un error, retorno un mensaje de error con código 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseWrapper<>("Hubo un error al eliminar el cliente: " + e.getMessage()));
        }
    }
}