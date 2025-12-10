package com.analistas.barberia.controller;

import com.analistas.barberia.model.Turno;
import com.analistas.barberia.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController // 1. Indica que esta clase maneja peticiones REST
@RequestMapping("/api/turnos") // 2. Define el endpoint base para este controlador
public class TurnoController {

    // 3. Inyección del servicio (Lógica de Negocio)
    @Autowired
    private ITurnoService turnoService;

    // A. Endpoint: OBTENER TODOS LOS TURNOS
    // Mapeo: GET /api/turnos
    @GetMapping
    public ResponseEntity<List<Turno>> obtenerTodos() {
        List<Turno> turnos = turnoService.findAll();
        return new ResponseEntity<>(turnos, HttpStatus.OK);
    }

    // B. Endpoint: REGISTRAR UN NUEVO TURNO
    // Mapeo: POST /api/turnos
    @PostMapping
    public ResponseEntity<?> crearTurno(@RequestBody Turno turno) {
        try {
            // El servicio contiene la lógica de negocio (validar disponibilidad)
            Turno nuevoTurno = turnoService.save(turno);
            // Si tiene éxito, devuelve el objeto creado con el código 201 (CREATED)
            return new ResponseEntity<>(nuevoTurno, HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            // Si falla la lógica (ej. turno no disponible), devuelve 400 (Bad Request)
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Captura cualquier otro error interno del servidor
            return new ResponseEntity<>("Error al registrar el turno: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // C. Endpoint: OBTENER TURNO POR ID
    // Mapeo: GET /api/turnos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Turno> obtenerPorId(@PathVariable Long id) {
        Turno turno = turnoService.findById(id);
        if (turno != null) {
            return new ResponseEntity<>(turno, HttpStatus.OK);
        }
        // Si no se encuentra, devuelve 404 (Not Found)
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) {
        try {
            turnoService.eliminar(id);
            // Retorna 204 No Content
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            // Capturado cuando buscarPorId() no encuentra el turno
            return ResponseEntity.notFound().build(); // 404 Not Found
        } catch (DataIntegrityViolationException e) {
            // El turno tiene Ventas u otras FKs asociadas (ya funciona)
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar el turno porque tiene registros asociados (ej. Ventas)."); // 409
                                                                                                           // Conflict
        } catch (Exception e) {
            // Otros errores inesperados.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTurno(@PathVariable Long id, @RequestBody Turno turno) {
        try {
            // Aseguramos que la ID del objeto sea la misma que la de la URL para el UPDATE
            turno.setId_turno(id);

            // El método save() actualizará el turno si la ID existe y valida la
            // disponibilidad.
            Turno turnoActualizado = turnoService.save(turno);

            // Retorna 200 OK con el objeto actualizado
            return new ResponseEntity<>(turnoActualizado, HttpStatus.OK);

        } catch (IllegalStateException e) {
            // Captura la excepción de indisponibilidad (lanzada desde el servicio)
            // Retorna 409 Conflict y el mensaje de error en el cuerpo
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            // Captura cualquier otro error del servidor o DB
            // Retorna 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}