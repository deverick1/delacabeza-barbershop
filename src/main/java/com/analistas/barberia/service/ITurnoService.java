package com.analistas.barberia.service;

import com.analistas.barberia.model.Turno;
import java.util.List;

public interface ITurnoService {

    List<Turno> findAll();
    Turno findById(Long id);
    Turno save(Turno turno);
    void delete(Long id);

    
    public Boolean isDisponible(Turno turno);

    public void guardar(Turno turno);

    public void eliminar(Long id);

    public Turno buscarPorId(Long id);
}
