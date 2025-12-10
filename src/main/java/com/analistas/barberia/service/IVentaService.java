package com.analistas.barberia.service;

import com.analistas.barberia.model.Venta;
import java.util.List;

public interface IVentaService {

    List<Venta> findAll();
    Venta findById(Long id);
    Venta save(Venta venta); 
    void delete(Long id);

    public Double calcularIngresosDiarios();

    public Double calcularIngresosSemanales();

    public Double calcularIngresosMensuales();
}


    
