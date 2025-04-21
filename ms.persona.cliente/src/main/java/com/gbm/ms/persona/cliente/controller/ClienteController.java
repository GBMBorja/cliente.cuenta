package com.gbm.ms.persona.cliente.controller;
import com.gbm.ms.persona.cliente.dto.ClienteDTO;
import com.gbm.ms.persona.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<?> buscarCliente(@PathVariable String clienteId) {
        return clienteService.buscarPorClienteId(clienteId);
    }

    @PostMapping
    public ResponseEntity<?> crearCliente(@RequestBody ClienteDTO clienteDTO) {
        clienteDTO.setClienteId(clienteDTO.getIdentificacion());
        return clienteService.crearCliente(clienteDTO);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<?> actualizarCliente(@PathVariable String clienteId, @RequestBody ClienteDTO clienteDTO) {
        return clienteService.actualizarCliente(clienteId, clienteDTO);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<?> eliminarCliente(@PathVariable String clienteId) {
        return clienteService.eliminarCliente(clienteId);
    }
}