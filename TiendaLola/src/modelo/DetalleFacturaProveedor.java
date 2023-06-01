package modelo;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DetalleFacturaProveedor {
    /*
     * DECLARACIÓN DE VARIABLES
     */
    private Integer idDetalleFacturaProveedor;
    private int idFacturaProveedor;
    private int cantidad;
    private Integer precio;
    private Producto producto;

    /*
     * METODO CREAR
     */

    public String agregarDetalleFactura(DetalleFacturaProveedor detalleFacturaProveedor, FacturaProveedor factura, Connection conexion) {
        PreparedStatement sentencia = null;
        try {
            String sql = "INSERT INTO factura_proveedor_producto (id_factura_proveedor, id_producto, cantidad, precio) VALUES (?, ?, ?, ?, ?)";
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, factura.getIdFacturaProveedor());
            sentencia.setInt(2, detalleFacturaProveedor.getProducto().getIdProducto());
            sentencia.setInt(3, detalleFacturaProveedor.getCantidad());
            sentencia.setInt(4, detalleFacturaProveedor.getPrecio());
            sentencia.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            sentencia.executeUpdate();
            //Ejecuta la sentencia
            sentencia.executeUpdate();
            //Cierra la conexion - sentencia
            sentencia.close();
            conexion.close();
            return "Productos agregados a la factura.";
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return "Error creacion del detalle factura, campos invalidos.";
        }
    }

    /*
     * METODO ELIMINAR
     */
    public String eliminarDetalleFacturaPorIdFactura(int idFacturaProveedor, Connection conexion) {
        try {
            PreparedStatement statement = conexion.prepareStatement("DELETE FROM detalle_factura_proveedor WHERE id_factura = ?");
            statement.setInt(1, idFacturaProveedor);
            statement.executeUpdate();
            conexion.close();
           return "Productos eliminados de la factura";
        } catch (SQLException ex) {
           return "Ocurrió un error al eliminar los productos de la factura: " + ex.getMessage();
        }
    }
    /*
     * METODO BUSCAR
     */
    public List<DetalleFacturaProveedor>  buscarPorIdFactura(int idFactura, Connection conexion) {
            List<DetalleFacturaProveedor> productos = new ArrayList<>();
        try {
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM detalle_factura_proveedor WHERE id_factura = ?");
            statement.setInt(1, idFactura);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                DetalleFacturaProveedor productoDetalle = new DetalleFacturaProveedor();
                productoDetalle.setIdFacturaProveedor(result.getInt("id_detalle_factura_proveedor"));
                productoDetalle.setIdFacturaProveedor(result.getInt("id_factura_proveedor"));
                Producto producto =  new Producto();
                producto = producto.buscarProductos(result.getInt("id_producto"),"","","","","","","","","","","","","","","",null,conexion).get(0);
                productoDetalle.setProducto(producto);
                productoDetalle.setCantidad(result.getInt("cantidad"));
                productoDetalle.setPrecio(result.getInt("precio"));
                productos.add(productoDetalle);
            }
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Ocurrió un error al buscar registros por id_factura en la tabla detalle_factura_proveedor: " + ex.getMessage());
        }
        return productos;
    }

    /*
     * METODO GETTERS AND SETTERS
     */

    public int getIdFacturaProveedor() {
        return idFacturaProveedor;
    }

    public void setIdFacturaProveedor(int idFacturaProveedor) {
        this.idFacturaProveedor = idFacturaProveedor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
