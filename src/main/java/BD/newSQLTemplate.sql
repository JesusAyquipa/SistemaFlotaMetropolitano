CREATE DATABASE bus_metro;
CREATE ROLE bus_metro_admin WITH LOGIN PASSWORD '12345';
ALTER ROLE bus_metro_admin WITH SUPERUSER CREATEDB CREATEROLE REPLICATION;
ALTER DATABASE bus_metro OWNER TO bus_metro_admin;

CREATE TABLE bus (
    id_bus SERIAL PRIMARY KEY,
    placa VARCHAR(10) UNIQUE NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    kilometraje INT NOT NULL DEFAULT 0,
    estado VARCHAR(20) NOT NULL
    -- Ejemplo de estado: 'Disponible', 'En mantenimiento', 'Asignado'
);

-- ===========================================
-- TABLA: RUTA
-- ===========================================
CREATE TABLE ruta (
    id_ruta SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    origen VARCHAR(50) NOT NULL,
    destino VARCHAR(50) NOT NULL
);

-- ===========================================
-- TABLA: ESTACION
-- ===========================================
CREATE TABLE estacion (
    id_estacion SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    distrito VARCHAR(50) NOT NULL,
    capacidad INT NOT NULL CHECK (capacidad > 0)
);

-- ===========================================
-- TABLA: MANTENIMIENTO
-- ===========================================
CREATE TABLE mantenimiento (
    id_mantenimiento SERIAL PRIMARY KEY,
    id_bus INT NOT NULL,
    tipo VARCHAR(30) NOT NULL,         -- 'Preventivo' o 'Correctivo'
    fecha DATE NOT NULL,
    kilometraje INT NOT NULL,
    descripcion VARCHAR(255),
    CONSTRAINT fk_mantenimiento_bus
        FOREIGN KEY (id_bus)
        REFERENCES bus (id_bus)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

-- ===========================================
-- TABLA: FALLA
-- ===========================================
CREATE TABLE falla (
    id_falla SERIAL PRIMARY KEY,
    id_bus INT NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    fecha DATE NOT NULL,
    CONSTRAINT fk_falla_bus
        FOREIGN KEY (id_bus)
        REFERENCES bus (id_bus)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

-- ===========================================
-- TABLA: ASIGNACION
-- ===========================================
CREATE TABLE asignacion (
    id_asignacion SERIAL PRIMARY KEY,
    id_bus INT NOT NULL,
    id_ruta INT NOT NULL,
    fecha DATE NOT NULL,
    CONSTRAINT fk_asignacion_bus
        FOREIGN KEY (id_bus)
        REFERENCES bus (id_bus)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_asignacion_ruta
        FOREIGN KEY (id_ruta)
        REFERENCES ruta (id_ruta)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

-- ===========================================
-- DATOS DE EJEMPLO: BUS
-- ===========================================
INSERT INTO bus (placa, modelo, kilometraje, estado) VALUES
('ABC-123', 'Volvo B12M', 120000, 'Disponible'),
('XYZ-987', 'Scania K310', 98000, 'En mantenimiento'),
('MNO-555', 'Mercedes O500', 56000, 'Asignado'),
('JKL-222', 'Volvo B9S', 75000, 'Disponible'),
('HGR-789', 'Scania K360', 130000, 'Disponible'),
('PQZ-456', 'Mercedes O500', 30000, 'Asignado');

-- ===========================================
-- DATOS DE EJEMPLO: RUTA
-- ===========================================
INSERT INTO ruta (nombre, origen, destino) VALUES
('Ruta Norte-Sur', 'Independencia', 'Chorrillos'),
('Ruta Sur-Norte', 'Chorrillos', 'Independencia'),
('Ruta Expresa', 'Naranjal', 'Central'),
('Ruta Alimentadora Este', 'Central', 'Ate'),
('Ruta Alimentadora Oeste', 'Central', 'San Miguel');

-- ===========================================
-- DATOS DE EJEMPLO: ESTACION
-- ===========================================
INSERT INTO estacion (nombre, distrito, capacidad) VALUES
('Naranjal', 'Independencia', 500),
('Matellini', 'Chorrillos', 600),
('Central', 'Cercado de Lima', 400),
('Tomás Valle', 'Los Olivos', 350),
('Plaza de Flores', 'Barranco', 300),
('28 de Julio', 'Miraflores', 250);

-- ===========================================
-- DATOS DE EJEMPLO: ASIGNACION
-- ===========================================
INSERT INTO asignacion (id_bus, id_ruta, fecha) VALUES
(1, 1, '2025-10-14'),
(3, 2, '2025-10-14'),
(4, 3, '2025-10-14'),
(6, 4, '2025-10-14'),
(1, 5, '2025-10-13'),
(5, 1, '2025-10-13');

-- ===========================================
-- DATOS DE EJEMPLO: MANTENIMIENTO
-- ===========================================
INSERT INTO mantenimiento (id_bus, tipo, fecha, kilometraje, descripcion) VALUES
(2, 'Correctivo', '2025-10-01', 98000, 'Cambio de sistema de frenos'),
(5, 'Preventivo', '2025-09-15', 128000, 'Cambio de aceite y filtros'),
(1, 'Preventivo', '2025-09-30', 118000, 'Revisión general'),
(3, 'Correctivo', '2025-08-20', 55000, 'Reemplazo de neumáticos'),
(4, 'Preventivo', '2025-09-10', 74000, 'Limpieza de inyectores');

-- ===========================================
-- DATOS DE EJEMPLO: FALLA
-- ===========================================
INSERT INTO falla (id_bus, descripcion, fecha) VALUES
(2, 'Fuga en el sistema hidráulico', '2025-09-28'),
(3, 'Ruido en suspensión delantera', '2025-09-20'),
(1, 'Problema eléctrico menor', '2025-08-30'),
(5, 'Sensor de temperatura defectuoso', '2025-09-25'),
(4, 'Falla en el aire acondicionado','2025-10-02');