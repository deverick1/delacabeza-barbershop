package com.analistas.barberia.repository;

import com.analistas.barberia.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VentaRepository extends JpaRepository<Venta, Long>{

    @Query("SELECT SUM(v.total) FROM Venta v WHERE DATE(v.fecha_venta) = CURRENT_DATE()")
    Double calcularIngresosDiarios();

    @Query(value = "SELECT SUM(v.total) FROM venta v WHERE v.fecha_venta >= DATE_SUB(CURRENT_DATE(), INTERVAL 7 DAY)", nativeQuery = true)
    Double calcularIngresosSemanales();

    @Query(value = "SELECT SUM(v.total) FROM venta v WHERE MONTH(v.fecha_venta) = MONTH(CURRENT_DATE()) AND YEAR(v.fecha_venta) = YEAR(CURRENT_DATE())", nativeQuery = true)
    Double calcularIngresosMensuales();
}
