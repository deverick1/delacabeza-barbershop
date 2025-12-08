package com.analistas.barberia.service;

import com.analistas.barberia.model.Turno;
import com.analistas.barberia.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TurnoServiceImpl implements ITurnoService {

    // 1. Inyección de dependencias
    @Autowired
    private TurnoRepository turnoRepo;

    // Métodos CRUD Estándar
    // =========================================================================

    @Override
    public List<Turno> findAll() {
        return turnoRepo.findAll();
    }
    
    @Override
    public Turno findById(Long id) {
        // Busca por ID y usa orElse(null) para devolver null si no lo encuentra.
        return turnoRepo.findById(id).orElse(null); 
    }
    
    @Override
    public void delete(Long id) {
        // Elimina el turno por ID
        turnoRepo.deleteById(id);
    }
    
    // Lógica de Negocio Específica (Registrar Turno)
    // =========================================================================

    @Override
    public Turno save(Turno turno) {
        
        // 1. Verificar Disponibilidad (Subproceso 2.1.2)
        if (isDisponible(turno)) {
            // Si está disponible, se guarda en la base de datos
            return turnoRepo.save(turno);
        } else {
            // Manejar error de ocupado (como dicta tu pseudocódigo)
            // Se lanza una excepción que luego debe ser capturada por el Controlador.
            throw new IllegalStateException("El turno ya está reservado para la fecha y hora seleccionada."); 
        }
    }
    
    // Subproceso 2.1.2: Verificar Disponibilidad
    @Override
    public Boolean isDisponible(Turno turno) {
        // Lógica: busca un turno con la misma fecha y hora.
        // Si el resultado de la búsqueda está vacío (isEmpty() es TRUE), entonces está disponible.
        return turnoRepo.findByFechaAndHora(turno.getFecha(), turno.getHora()).isEmpty();
    }

    @Override
    public Boolean IsDisponible(Turno turno) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'IsDisponible'");
    } 

   
}