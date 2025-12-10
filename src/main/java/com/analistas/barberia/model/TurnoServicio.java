package com.analistas.barberia.model;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TURNO_SERVICIO")
public class TurnoServicio {

    @EmbeddedId
    private TurnoServicioId id;

    @ManyToOne
    @MapsId("id_turno")
    @JoinColumn(name = "id_turno")
    private Turno turno;

    @ManyToOne
    @MapsId("id_servicio")
    @JoinColumn(name = "id_servicio")
    private Servicio servicio;
}
