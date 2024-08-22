DROP TABLE DOMICILIOS IF EXISTS;
DROP TABLE PACIENTES IF EXISTS;

CREATE TABLE DOMICILIOS(
ID INT AUTO_INCREMENT PRIMARY KEY,
CALLE VARCHAR(50) NOT NULL,
NUMERO INT NOT NULL,
LOCALIDAD VARCHAR(50) NOT NULL,
PROVINCIA VARCHAR(50) NOT NULL
);

CREATE TABLE PACIENTES(
ID INT AUTO_INCREMENT PRIMARY KEY,
APELLIDO VARCHAR(50) NOT NULL,
NOMBRE VARCHAR(50) NOT NULL,
DNI VARCHAR(10) NOT NULL,
FECHA_INGRESO DATE NOT NULL,
ID_DOMICILIO INT NOT NULL
);

INSERT INTO DOMICILIOS VALUES (DEFAULT,'SIEMPRE VIVA', 123, 'SAN SALVADOR', 'JUJUY');
INSERT INTO PACIENTES VALUES (DEFAULT, 'PEREZ', 'JUAN', '4654666', '2024-07-15',1);

DROP TABLE ODONTOLOGOS IF EXISTS;

CREATE TABLE ODONTOLOGOS (
ID INT AUTO_INCREMENT PRIMARY KEY,
NUMEROMATRICULA INT NOT NULL,
NOMBRE VARCHAR(50) NOT NULL,
APELLIDO VARCHAR(50) NOT NULL
);

INSERT INTO ODONTOLOGOS VALUES (DEFAULT, 202020, 'JUAN', 'PEREZ'),
(DEFAULT, 303030, 'JUANA', 'DELGADO'),
(DEFAULT, 404040, 'PEPITO', 'PEREZ');