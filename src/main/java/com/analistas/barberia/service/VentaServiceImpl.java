package com.analistas.barberia.service;

import com.analistas.barberia.model.*;
import com.analistas.barberia.repository.VentaRepository;
import com.analistas.barberia.repository.TurnoServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class VentaServiceImpl implements IVentaService {

    @Autowired
    private VentaRepository ventaRepo;
    
    @Autowired
    private TurnoServicioRepository turnoServicioRepo;

    // ... (Métodos findAll, findById, delete sin cambios) ...

    @Override
    public Venta save(Venta venta) {
        
        if (venta.getTurno() == null || venta.getTurno().getId_turno() == null) {
             throw new IllegalArgumentException("La venta debe estar asociada a un Turno existente.");
        }
        
        // 1. Asignar Fecha
        if (venta.getFecha_venta() == null) {
            venta.setFecha_venta(LocalDate.now());
        }
        
        // 2. Calcular el total de la venta (Lógica de Negocio)
        Double totalCalculado = calcularTotalVenta(venta.getTurno().getId_turno());
        venta.setTotal(totalCalculado);
        
        // 3. Guardar Venta
        return ventaRepo.save(venta);
    }
    
    /**
     * Calcula el monto total sumando los precios de todos los servicios
     * asociados a un turno específico.
     */
    private Double calcularTotalVenta(Long idTurno) {
        // 1. Obtener todas las entradas de la tabla de enlace para este turno
       List<TurnoServicio> turnosServicios = turnoServicioRepo.buscarPorIdTurno(idTurno);
        
        double total = 0.0;
        
        // 2. Sumar el precio de cada servicio
        for (TurnoServicio ts : turnosServicios) {
            Servicio servicio = ts.getServicio();
            if (servicio != null && servicio.getPrecio() != null) {
                total += servicio.getPrecio();
            }
        }
        
        // Si no se encontraron servicios, el total puede ser cero (o lanzar una excepción si es un error)
        if (total == 0.0) {
            // Se puede lanzar una excepción si un turno debe tener servicios.
            System.out.println("ADVERTENCIA: No se encontraron servicios asociados al turno " + idTurno);
        }

        return total;
    }

    @Override
    public List<Venta> findAll() {
        return ventaRepo.findAll();
    }

    @Override
    public Venta findById(Long id) {
        return ventaRepo.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        ventaRepo.deleteById(id);
    }

    @Override
public Double calcularIngresosDiarios() {
    // Si no hay ventas, SUM() devolverá null, lo manejamos devolviendo 0.0
    Double total = ventaRepo.calcularIngresosDiarios();
    return total != null ? total : 0.0;
}

    @Override
public Double calcularIngresosSemanales() {
    Double total = ventaRepo.calcularIngresosSemanales();
    return total != null ? total : 0.0;
}

@Override
public Double calcularIngresosMensuales() {
    Double total = ventaRepo.calcularIngresosMensuales();
    return total != null ? total : 0.0;
}
}