DROP SCHEMA IF EXISTS deustotraveladvisor;
DROP USER IF EXISTS 'admin'@'localhost';

CREATE SCHEMA deustotraveladvisor;
CREATE USER IF NOT EXISTS 'admin'@'localhost' IDENTIFIED BY 'admin1234';


USE deustotraveladvisor;

GRANT ALL PRIVILEGES ON deustotraveladvisor.* TO 'admin'@'localhost';


CREATE TABLE Cliente (
  dni VARCHAR(255) NOT NULL UNIQUE,  -- Assuming dni is alphanumeric, adjust if needed
  nombre VARCHAR(255) NOT NULL,
  gmail VARCHAR(255),
  telefono VARCHAR(255),
  residencia TEXT,
  password VARCHAR(255),
  PRIMARY KEY (dni)
);
CREATE TABLE Viaje (
  ID INT NOT NULL AUTO_INCREMENT,
  origen TEXT NOT NULL,
  destino TEXT NOT NULL,
  fecha DATE,  -- Assuming date format is YYYY-MM-DD, adjust if needed
  duracion DECIMAL(10,2) CHECK (duracion > 0),  -- Use DECIMAL for price as well
  precio DECIMAL(10,2) CHECK (precio > 0),
  oferta INT DEFAULT 0 CHECK (oferta BETWEEN 0 AND 50),
  empresa TEXT,
  asientos_disponibles INT DEFAULT 100 CHECK (asientos_disponibles >= 0),
  asientos_totales INT DEFAULT 0 CHECK (asientos_totales > 0),  -- Changed to > 0 as 0 total seats wouldn't make sense
  PRIMARY KEY (ID)
);

CREATE TABLE Favorito (
  id INT NOT NULL AUTO_INCREMENT,  -- Use AUTO_INCREMENT for primary key
  cliente VARCHAR(255) NOT NULL,
  viaje INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (cliente) REFERENCES Cliente(dni) ON DELETE CASCADE,
  FOREIGN KEY (viaje) REFERENCES Viaje(ID) ON DELETE CASCADE
);
CREATE TABLE Reserva (
  cliente VARCHAR(255) NOT NULL,
  viaje INT NOT NULL,
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  num_plazas INT,
  FOREIGN KEY (cliente) REFERENCES Cliente(dni) ON DELETE CASCADE,
  FOREIGN KEY (viaje) REFERENCES Viaje(ID) ON DELETE CASCADE
);
CREATE TABLE Trabajador (
  DNI VARCHAR(255) PRIMARY KEY,  -- Assuming DNI is alphanumeric, adjust if needed
  Nombre VARCHAR(255),
  Gmail VARCHAR(255),
  Telefono VARCHAR(255),
  Sueldo INT,
  password VARCHAR(255)
);

INSERT INTO Cliente (dni, nombre, gmail, telefono, residencia, password) VALUES
('14235378L', 'Borja Perez', 'prueba@gmail.com', '666666666', 'Bilbao', 'prueba123');

INSERT INTO Trabajador (DNI, Nombre, Gmail, Telefono, Sueldo, password) VALUES
('21121546C', 'Unai Aira', 'trabajador1@deustoadvisor.es', '654741894', 2000, 'trabajador123');

INSERT INTO Viaje (origen, destino, fecha, duracion, precio, empresa, asientos_disponibles, asientos_totales) VALUES
('Bilbao', 'Madrid', '2021-12-01', 2.5, 50.00, 'Iberia', 40, 200),
('Madrid', 'Barcelona', '2021-12-02', 1.5, 30.00, 'Vueling', 50, 100),
('Barcelona', 'Bilbao', '2021-12-03', 2.0, 40.00, 'Iberia', 75, 150);

