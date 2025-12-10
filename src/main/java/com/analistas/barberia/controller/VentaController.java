package com.analistas.barberia.controller;

import com.analistas.barberia.model.Venta;
import com.analistas.barberia.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ventas") // Mapea todas las solicitudes a /api/ventas
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    /**
     * POST /api/ventas
     * Crea una nueva venta. La lógica de cálculo del total está en el servicio.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Código HTTP 201
    public Venta crearVenta(@RequestBody Venta venta) {
        // Aquí se llama al método save que implementa la lógica de cálculo
        return ventaService.save(venta);
    }

    /**
     * GET /api/ventas
     * Lista todas las ventas registradas.
     */
    @GetMapping
    public List<Venta> listarVentas() {
        return ventaService.findAll();
    }

    /**
     * GET /api/ventas/{id}
     * Obtiene una venta específica por su ID.
     */
    @GetMapping("/{id}")
    public Venta obtenerVenta(@PathVariable Long id) {
        // Nota: En producción, se debería manejar el caso de que la venta sea null (404
        // Not Found)
        return ventaService.findById(id);
    }

    /**
     * DELETE /api/ventas/{id}
     * Elimina una venta por su ID.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Código HTTP 204 (Éxito sin contenido)
    public void eliminarVenta(@PathVariable Long id) {
        ventaService.delete(id);
    }

    @GetMapping("/reportes/diario")
    public Double obtenerIngresosDiarios() {
        return ventaService.calcularIngresosDiarios();

    }

    @GetMapping("/reportes/semanal")
    public Double obtenerIngresosSemanales() {
        return ventaService.calcularIngresosSemanales();
    }

    @GetMapping("/reportes/mensual")
    public Double obtenerIngresosMensuales() {
        return ventaService.calcularIngresosMensuales();
    }
}
