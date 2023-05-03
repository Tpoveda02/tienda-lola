package controlador;

import modelo.Categoria;
import modelo.Producto;

import java.math.BigDecimal;
import java.util.List;
import java.sql.*;

public class ProductoControlador {
    //Declaración de variables
    Controlador controlador;

    public ProductoControlador() {
        controlador = new Controlador();
    }

    //----CRUD PRODUCTO----
    //Metodo obtener producto(s) - recibe los valores de los JText
    public List<Producto> listarProductos() {
        Producto producto = new Producto();
        //Llama el metodo de listar productos
        return producto.listarProductos(controlador.getConexion());
    }

    //Metodo obtener producto(s) - recibe los valores de los JText
    public List<Producto> buscarProductos(Integer idProducto, String nombre, String tipoContenidoNeto, String contenidoNeto, String valorContenido, String empaqueGeneral, String empaque, String descripcion, String recomendaciones, String precioProveedor, String porcentajeIva, String precioSinIva, String precioVenta, String fechaVencimiento, String cantidad, String idCategoria) {
        //Instacia el producto con los respectivos valores
        Producto producto = new Producto();
        //Llama el metodo de buscar productos
        return producto.buscarProductos(idProducto, nombre, tipoContenidoNeto, contenidoNeto, valorContenido, empaqueGeneral, empaque, descripcion, recomendaciones, precioProveedor, porcentajeIva, precioSinIva, precioVenta, fechaVencimiento, cantidad, idCategoria, controlador.getConexion());
    }

    //Metodo agregar producto - recibe los valores de los JText
    public String crearProducto(int idProducto, String nombre, String tipoContenidoNeto, BigDecimal contenidoNeto, BigDecimal valorContenido, String empaqueGeneral, String empaque, String descripcion, String recomendaciones, BigDecimal precioProveedor, BigDecimal porcentajeIva, BigDecimal precioSinIva, BigDecimal precioVenta, Date fechaVencimiento, int cantidad, Categoria categoria) {
        //Instacia el producto con los respectivos valores
        Producto producto = new Producto(idProducto, nombre, tipoContenidoNeto, contenidoNeto, valorContenido, empaqueGeneral, empaque, descripcion, recomendaciones, precioProveedor, porcentajeIva, precioSinIva, precioVenta, fechaVencimiento, cantidad, categoria);
        //Llama el metodo de crear producto
        return producto.agregarProducto(producto, controlador.getConexion());
    }

    //Metodo actualizar producto - recibe los valores de los JText
    public String modificarProducto(int idProducto, String nombre, String tipoContenidoNeto, BigDecimal contenidoNeto, BigDecimal valorContenido, String empaqueGeneral, String empaque, String descripcion, String recomendaciones, BigDecimal precioProveedor, BigDecimal porcentajeIva, BigDecimal precioSinIva, BigDecimal precioVenta, Date fechaVencimiento, int cantidad, Categoria categoria) {
        //Instacia el producto con los respectivos valores
        Producto producto = new Producto(idProducto, nombre, tipoContenidoNeto, contenidoNeto, valorContenido, empaqueGeneral, empaque, descripcion, recomendaciones, precioProveedor, porcentajeIva, precioSinIva, precioVenta, fechaVencimiento, cantidad, categoria);
        //Llama el metodo de actualizar producto
        return producto.actualizarProducto(producto, controlador.getConexion());
    }

    //Metodo eliminar producto
    public String eliminarProducto(int id) {
        //Instacia el producto con el ID para eliminarlo
        Producto producto = new Producto();
        producto.setIdProducto(id);
        //Llama el metodo de eliminar producto
        return producto.eliminarProducto(producto, controlador.getConexion());
    }
}
