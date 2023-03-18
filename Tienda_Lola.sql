CREATE DATABASE TIENDA_LOLA;
USE TIENDA_LOLA;


CREATE TABLE USUARIO_ACCESO (
    id_usuario INT PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL,
    contrasenia VARCHAR(100) NOT NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_modificacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE CLIENTE (
    id_cliente INT PRIMARY KEY,
    tipo_identificacion ENUM('DNI', 'NIE', 'PASAPORTE','CC') NOT NULL,
    primer_nombre VARCHAR(50) NOT NULL,
    segundo_nombre VARCHAR(50) NOT NULL,
    primer_apallido VARCHAR(50) NOT NULL,
    segundo_apellido VARCHAR(50) NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    correo_electronico VARCHAR(50) NOT NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_modificacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE PROVEEDOR (
    id_proveedor INT PRIMARY KEY,
    tipo_identificacion ENUM('NIE', 'PASAPORTE','CC', 'NIT', 'RUT') NOT NULL,
    primer_nombre VARCHAR(50) NOT NULL,
    segundo_nombre VARCHAR(50) NOT NULL,
    primer_apallido VARCHAR(50) NOT NULL,
    segundo_apellido VARCHAR(50) NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    correo_electronico VARCHAR(50) NOT NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_modificacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE categoria(
    id_categoria INT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_modificacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE PRODUCTO (
    id_producto INT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    gramos DECIMAL(10,5) NOT NULL,
    empaque_general VARCHAR(255) NOT NULL,
    empaque VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    precio_venta DECIMAL(10,2) NOT NULL,
    precio_proveedor DECIMAL(10,2) NOT NULL,
    fecha_vencimiento DATE NOT NULL,
    cantidad INT NOT NULL,
    id_categoria INT NOT NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_modificacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_producto_categoria FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

CREATE TABLE factura_cliente (
    id_factura_cliente INT PRIMARY KEY,
    direccion VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    correo_electronico VARCHAR(50) NOT NULL,
    fecha_factura DATE NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    id_cliente INT NOT NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_modificacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_factura_cliente FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
    
);

CREATE TABLE detalle_factura_cliente (
    id_detalle_factura_cliente INT PRIMARY KEY,
    id_factura INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_modificacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_detalle_factura_cliente FOREIGN KEY (id_factura) REFERENCES factura_cliente(id_factura_cliente),
    CONSTRAINT fk_detalle_factura_producto FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

CREATE TABLE factura_proveedor (
    id_factura_proveedor INT PRIMARY KEY,
    direccion VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    correo_electronico VARCHAR(50) NOT NULL,
    fecha_factura_proveedor DATE NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    id_proveedor INT NOT NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_modificacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_factura_proveedor_proveedor FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)
);

CREATE TABLE detalle_factura_proveedor (
    id_detalle_factura_proveedor INT PRIMARY KEY,
    id_factura_proveedor INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_modificacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	CONSTRAINT fk_detalle_factura_proveedor_factura FOREIGN KEY (id_factura_proveedor)
    REFERENCES factura_proveedor(id_factura_proveedor),
    CONSTRAINT fk_detalle_factura_proveedor_producto FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);



	