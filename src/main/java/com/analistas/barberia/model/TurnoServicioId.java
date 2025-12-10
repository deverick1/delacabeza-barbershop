package com.analistas.barberia.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class TurnoServicioId implements Serializable {

    private Long id_turno;
    
    private Long id_servicio;
}
