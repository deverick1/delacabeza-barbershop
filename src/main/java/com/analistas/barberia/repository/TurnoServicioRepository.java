package com.analistas.barberia.repository;

import com.analistas.barberia.model.TurnoServicio;
import com.analistas.barberia.model.TurnoServicioId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnoServicioRepository extends JpaRepository<TurnoServicio, TurnoServicioId> {

}
