package com.analistas.barberia.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.Data;


@Data
@Embeddable
public class TurnoServicioId implements Serializable {

    private Long id_turno;
    
    private Long id_servicio;
}
