package com.analistas.barberia.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "VENTA")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_venta;

    // Relación Muchos a Uno con Turno (FK: id_turno)
    // Se asume que una Venta siempre se asocia a un Turno completado.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_turno", nullable = false) 
    private Turno turno;

    private Double total; // Monto total de la venta
    
    private LocalDate fecha_venta; // Día en que se realizó la venta

    private String metodo_pago; // Efectivo, Tarjeta, etc.

    
}