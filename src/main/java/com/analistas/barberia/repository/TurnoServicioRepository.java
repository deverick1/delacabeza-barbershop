package com.analistas.barberia.repository;

import com.analistas.barberia.model.TurnoServicio;
import com.analistas.barberia.model.TurnoServicioId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface TurnoServicioRepository extends JpaRepository<TurnoServicio, TurnoServicioId> {
    
    @Query("SELECT t FROM TurnoServicio t JOIN FETCH t.servicio WHERE t.turno.id_turno = :idTurno")
    List<TurnoServicio> buscarPorIdTurno(Long idTurno);

   
}