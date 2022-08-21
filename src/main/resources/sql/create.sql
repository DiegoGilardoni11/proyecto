CREATE TABLE cliente (
    dni INT NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50),
    PRIMARY KEY (dni)
);

CREATE TABLE producto (
	productoid int NOT NULL AUTO_INCREMENT,
    codigo INT NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    stock INT,
    precio FLOAT(10, 2),
    PRIMARY KEY (productoid)
);

CREATE TABLE factura (
    facturaid int NOT NULL AUTO_INCREMENT,
    fecha DATETIME,
    total FLOAT(10, 2),
    clienteid INT,
    PRIMARY KEY (facturaid),
    CONSTRAINT FK_cliente FOREIGN KEY (clienteid)
    REFERENCES cliente(dni)
);

CREATE TABLE Inventario (
    itemid INT NOT NULL AUTO_INCREMENT,
    descripcion varchar(255) NOT NULL,
    cantidad INT,
    precio FLOAT(10, 2),
    facturaid INT NOT NULL,
    productoid INT NOT NULL,
    PRIMARY KEY (itemid),
    CONSTRAINT FK_comprobante FOREIGN KEY (facturaid)
    REFERENCES factura(facturaid),
    CONSTRAINT FK_producto FOREIGN KEY (productoid)
    REFERENCES producto(productoid)
);