package com.gbm.ms.persona.cliente.repository;

import com.gbm.ms.persona.cliente.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
