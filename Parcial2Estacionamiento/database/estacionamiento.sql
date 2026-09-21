-- ============================================
-- PARCIAL II - SISTEMA DE ESTACIONAMIENTO
-- Base de datos: parcial2_estacionamiento
-- ============================================

-- CREACIÓN DE LA TABLA
CREATE TABLE vehiculo (
                          id SERIAL PRIMARY KEY,
                          placa VARCHAR(20) NOT NULL UNIQUE,
                          propietario VARCHAR(100) NOT NULL,
                          tipo VARCHAR(20) NOT NULL,
                          hora_ingreso TIME NOT NULL,
                          horas_utilizadas INTEGER NOT NULL CHECK (horas_utilizadas > 0),
                          costo NUMERIC(10,2) NOT NULL CHECK (costo >= 0),
                          activo BOOLEAN NOT NULL DEFAULT TRUE
);


-- ============================================
-- INSERTAR REGISTROS
-- ============================================

INSERT INTO vehiculo
(placa, propietario, tipo, hora_ingreso, horas_utilizadas, costo, activo)
VALUES
    ('P123ABC', 'Hans', 'Automóvil', '08:00', 4, 40.00, TRUE),
    ('M456XYZ', 'Juan', 'Motocicleta', '09:00', 6, 32.40, TRUE),
    ('C789DEF', 'Carlos', 'Automóvil', '10:00', 7, 63.00, TRUE),
    ('M321GHI', 'Ana', 'Motocicleta', '11:00', 3, 18.00, TRUE),
    ('P555JKL', 'Luis', 'Automóvil', '12:00', 6, 54.00, TRUE);


-- ============================================
-- CONSULTA GENERAL
-- ============================================

SELECT id, placa, propietario, tipo, hora_ingreso,
       horas_utilizadas, costo, activo
FROM vehiculo;


-- ============================================
-- CONSULTA POR TIPO
-- ============================================

SELECT id, placa, propietario, tipo, hora_ingreso,
       horas_utilizadas, costo, activo
FROM vehiculo
WHERE tipo = 'Automóvil';


-- ============================================
-- CONSULTA DE COSTOS MAYORES A Q40
-- ============================================

SELECT id, placa, propietario, tipo, hora_ingreso,
       horas_utilizadas, costo, activo
FROM vehiculo
WHERE costo > 40;


-- ============================================
-- ORDENAR POR COSTO DESCENDENTE
-- ============================================

SELECT id, placa, propietario, tipo, hora_ingreso,
       horas_utilizadas, costo, activo
FROM vehiculo
ORDER BY costo DESC;


-- ============================================
-- ACTUALIZAR UN REGISTRO
-- ============================================

UPDATE vehiculo
SET propietario = 'Hans Douglas'
WHERE placa = 'P123ABC';


-- ============================================
-- CAMBIAR ESTADO DEL VEHÍCULO
-- ============================================

UPDATE vehiculo
SET activo = FALSE
WHERE placa = 'M456XYZ';


-- ============================================
-- ELIMINAR UN REGISTRO
-- ============================================

DELETE FROM vehiculo
WHERE placa = 'M321GHI';


-- ============================================
-- ERROR INTENCIONAL: PLACA DUPLICADA
-- Ejecutar de forma individual para evidenciar
-- la restricción UNIQUE.
-- ============================================

INSERT INTO vehiculo
(placa, propietario, tipo, hora_ingreso, horas_utilizadas, costo, activo)
VALUES
    ('P123ABC', 'Duplicado', 'Automóvil', '15:00', 2, 20.00, TRUE);


-- ============================================
-- ERROR INTENCIONAL: HORAS INVÁLIDAS
-- Ejecutar de forma individual para evidenciar
-- la restricción CHECK.
-- ============================================

INSERT INTO vehiculo
(placa, propietario, tipo, hora_ingreso, horas_utilizadas, costo, activo)
VALUES
    ('Z999ZZZ', 'Prueba', 'Automóvil', '16:00', 0, 0.00, TRUE);