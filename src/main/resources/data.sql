-- Limpiar tablas si existen (útil para pruebas)
-- DESACTIVAR RESTRICCIONES DE CLAVE FORÁNEA TEMPORALMENTE (SÓLO PARA MYSQL):
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE venta;
TRUNCATE TABLE turno_servicio;
TRUNCATE TABLE turno;
TRUNCATE TABLE servicio;
TRUNCATE TABLE empleado;
TRUNCATE TABLE cliente;
SET FOREIGN_KEY_CHECKS = 1;


-- *** 1. Tablas Padres (Empleados y Clientes) ***
INSERT INTO empleado (id_empleado, nombre, apellido, telefono) VALUES (1, 'Juan', 'Perez', '111111111');

INSERT INTO cliente (id_cliente, nombre, apellido, telefono) VALUES (1, 'Carlos', 'Gomez', '222222222');


-- *** 2. Insertar Servicios ***
-- Necesarios para el cálculo del total de la venta.
INSERT INTO servicio (id_servicio, nombre, precio) VALUES (10, 'Corte Masculino', 750.00);
INSERT INTO servicio (id_servicio, nombre, precio) VALUES (11, 'Lavado', 150.00);


-- *** 3. Insertar el Turno (USAREMOS ID_TURNO = 1) ***
-- Esta es la fila que tu clave foránea estaba buscando.
INSERT INTO turno (id_turno, fecha, hora, estado, id_cliente, id_empleado)
VALUES (1, '2025-12-09', '10:00:00', TRUE, 1, 1);


-- *** 4. Asociar Servicios al Turno 1 ***
-- Total del turno: 750.00 + 150.00 = 900.00
INSERT INTO turno_servicio (id_turno, id_servicio) VALUES (1, 10);
INSERT INTO turno_servicio (id_turno, id_servicio) VALUES (1, 11);