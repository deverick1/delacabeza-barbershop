package com.analistas.barberia.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;


@Data
@Entity
@Table(name = "VENTA")
public class Venta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_venta;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_turno", nullable = false)
    private Turno turno;


    private Double total;

    private LocalDate fecha_venta;

    private String metodo_pago;
}
