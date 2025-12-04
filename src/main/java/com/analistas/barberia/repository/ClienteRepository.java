package com.analistas.barberia.repository;

import com.analistas.barberia.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Hereda métodos CRUD
}