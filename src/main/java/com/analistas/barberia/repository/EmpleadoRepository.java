package com.analistas.barberia.repository;

import com.analistas.barberia.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
   

}
