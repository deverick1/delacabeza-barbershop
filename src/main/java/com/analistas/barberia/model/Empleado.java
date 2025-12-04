package com.analistas.barberia.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "EMPLEADO")
public class Empleado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_empleado;

    private String nombre;
  


}
