package com.gbm.ms.cuenta.movimientos.service;


import com.gbm.ms.cuenta.movimientos.dto.CuentaDTO;
import com.gbm.ms.cuenta.movimientos.dto.PersonaDTO;
import com.gbm.ms.cuenta.movimientos.mapper.CuentaMapper;
import com.gbm.ms.cuenta.movimientos.model.Cuenta;
import com.gbm.ms.cuenta.movimientos.repository.CuentaRepository;
import com.gbm.ms.cuenta.movimientos.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final WebClient.Builder webClientBuilder;

    @Autowired
    public CuentaService(CuentaRepository cuentaRepository, WebClient.Builder webClientBuilder) {
        this.cuentaRepository = cuentaRepository;
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<ResponseEntity<ResponseWrapper<CuentaDTO>>> crearCuenta(CuentaDTO cuentaDTO) {
        return verificarClienteExistente(cuentaDTO.getClienteId())
                .flatMap(clienteExistente -> {
                    // Si el cliente existe, creamos la cuenta
                    Cuenta cuenta = CuentaMapper.toEntity(cuentaDTO);
                    return cuentaRepository.guardar(cuenta)
                            .map(saved -> ResponseEntity
                                    .status(HttpStatus.CREATED)
                                    .body(new ResponseWrapper<>(CuentaMapper.toDTO(saved))));
                })
                .switchIfEmpty(Mono.just(ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseWrapper<>("El cliente con ID " + cuentaDTO.getClienteId() + " no existe"))))
                .onErrorResume(e -> Mono.just(ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ResponseWrapper<>("Error al verificar cliente: " + e.getMessage()))));
    }

    public Mono<ResponseEntity<ResponseWrapper<CuentaDTO>>> buscarPorNumeroCuenta(String numeroCuenta) {
        try {
            return cuentaRepository.buscarPorNumeroCuenta(numeroCuenta)
                    .map(cuenta -> ResponseEntity.ok(new ResponseWrapper<>(CuentaMapper.toDTO(cuenta))))
                    .defaultIfEmpty(ResponseEntity
                            .status(HttpStatus.NOT_FOUND)
                            .body(new ResponseWrapper<>("Cuenta no encontrada con número: " + numeroCuenta)));
        } catch (Exception e) {
            return Mono.just(ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseWrapper<>("Error al buscar cuenta: " + e.getMessage())));
        }
    }

    public Flux<CuentaDTO> listarCuentas() {
        return cuentaRepository.listarTodas()
                .map(CuentaMapper::toDTO);
    }

    public Mono<ResponseEntity<ResponseWrapper<CuentaDTO>>> actualizarCuenta(String numeroCuenta, CuentaDTO cuentaDTO) {
        try {
            return cuentaRepository.buscarPorNumeroCuenta(numeroCuenta)
                    .flatMap(existente -> {
                        Cuenta actualizado = CuentaMapper.toEntity(cuentaDTO);
                        actualizado.setId(existente.getId());
                        return cuentaRepository.guardar(actualizado)
                                .map(updated -> ResponseEntity.ok(new ResponseWrapper<>(CuentaMapper.toDTO(updated))));
                    })
                    .defaultIfEmpty(ResponseEntity
                            .status(HttpStatus.NOT_FOUND)
                            .body(new ResponseWrapper<>("Cuenta no encontrada con número: " + numeroCuenta)));
        } catch (Exception e) {
            return Mono.just(ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseWrapper<>("Error al actualizar cuenta: " + e.getMessage())));
        }
    }

    public Mono<ResponseEntity<ResponseWrapper<Object>>> eliminarCuenta(String numeroCuenta) {
        return cuentaRepository.buscarPorNumeroCuenta(numeroCuenta)
                .flatMap(existente -> {
                    // Elimina la cuenta si existe y devuelve una respuesta exitosa
                    return cuentaRepository.eliminarPorNumeroCuenta(numeroCuenta)
                            .then(Mono.just(ResponseEntity.ok(new ResponseWrapper<>("Cuenta con número " + numeroCuenta + " eliminada correctamente"))));
                })
                .defaultIfEmpty(
                        // Si no se encuentra la cuenta, se devuelve un mensaje de error 404
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ResponseWrapper<>("Cuenta no encontrada con número: " + numeroCuenta))
                )
                .onErrorResume(e -> {
                    // En caso de error, se devuelve un mensaje de error 500
                    return Mono.just(
                            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .body(new ResponseWrapper<>("Error al eliminar la cuenta: " + e.getMessage()))
                    );
                });
    }


    private Mono<PersonaDTO> verificarClienteExistente(String clienteId) {
        return webClientBuilder.baseUrl("http://localhost:8080")  // URL del microservicio de clientes
                .build()
                .get()
                .uri("/clientes/{clienteId}", clienteId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ResponseWrapper<PersonaDTO>>() {})
                .map(ResponseWrapper::getData)  // Extraemos solo la data (PersonaDTO)
                .onErrorResume(e -> {
                    System.err.println("Error consultando cliente: " + e.getMessage());
                    return Mono.empty(); // Devuelve vacío si hay error
                });
    }
}