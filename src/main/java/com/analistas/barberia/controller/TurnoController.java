package com.analistas.barberia.controller;

import com.analistas.barberia.model.Turno;
import com.analistas.barberia.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/turnos")
public class TurnoController {

    @Autowired
    private ITurnoService turnoService;

    @GetMapping
    public ResponseEntity<List<Turno>> obtenerTodos() {
        List<Turno> turnos = turnoService.findAll();
        return new ResponseEntity<>(turnos, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> crearTurno(@RequestBody Turno turno) {
        try {
            Turno nuevoTurno = turnoService.save(turno);
            return new ResponseEntity<>(nuevoTurno, HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al registrar el turno." + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Turno> obtenerPorId(@PathVariable Long id) {
        Turno turno = turnoService.findById(id);
        if (turno != null) {
            return new ResponseEntity<>(turno, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTurno(@PathVariable Long id) {
        turnoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
