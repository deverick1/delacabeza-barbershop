package com.analistas.barberia.repository;

import com.analistas.barberia.model.Turno;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnoRepository extends JpaRepository<Turno, Long> {

    Optional<Turno> findByFechaAndHora(LocalDate fecha, LocalTime hora);
}
