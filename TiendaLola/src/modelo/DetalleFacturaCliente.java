package modelo;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DetalleFacturaCliente {
    /*
     * DECLARACIÓN DE VARIABLES
     */
    private Integer idDetalleFacturaCliente;
    private Integer idFacturaCliente;
    private int cantidad;
    private Integer precio;
    private Producto producto;

    /*
     * METODO CONSTRUCTOR
     */

    public DetalleFacturaCliente(Integer idDetalleFacturaCliente, Integer idFacturaCliente, int cantidad, Integer precio, Producto producto) {
        this.idDetalleFacturaCliente = idDetalleFacturaCliente;
        this.idFacturaCliente = idFacturaCliente;
        this.cantidad = cantidad;
        this.precio = precio;
        this.producto = producto;
    }

    public DetalleFacturaCliente(){}

    /*
     * METODO CREAR
     */

    public String agregarDetalleFactura(DetalleFacturaCliente detalleFacturaCliente, FacturaCliente factura, Connection conexion) {
        PreparedStatement sentencia = null;
        try {
            String sql = "INSERT INTO factura_cliente_producto (id_factura_cliente, id_producto, cantidad, precio) VALUES (?, ?, ?, ?, ?)";
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, factura.getIdFacturaCliente());
            sentencia.setInt(2, detalleFacturaCliente.getProducto().getIdProducto());
            sentencia.setInt(3, detalleFacturaCliente.getCantidad());
            sentencia.setInt(4, detalleFacturaCliente.getPrecio());
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
    public String eliminarDetalleFacturaPorIdFactura(int idFacturaCliente, Connection conexion) {
        try {
            PreparedStatement statement = conexion.prepareStatement("DELETE FROM detalle_factura_cliente WHERE id_factura = ?");
            statement.setInt(1, idFacturaCliente);
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
    public List<DetalleFacturaCliente>  buscarPorIdFactura(int idFactura, Connection conexion) {
            List<DetalleFacturaCliente> productos = new ArrayList<>();
        try {
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM detalle_factura_cliente WHERE id_factura = ?");
            statement.setInt(1, idFactura);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                DetalleFacturaCliente productoDetalle = new DetalleFacturaCliente();
                productoDetalle.setIdFacturaCliente(result.getInt("id_detalle_factura_cliente"));
                productoDetalle.setIdFacturaCliente(result.getInt("id_factura_cliente"));
                Producto producto =  new Producto();
                producto = producto.buscarProductos(result.getInt("id_producto"),"","","","","","","","","","","","","","","",null,conexion).get(0);
                productoDetalle.setProducto(producto);
                productoDetalle.setCantidad(result.getInt("cantidad"));
                productoDetalle.setPrecio(result.getInt("precio"));
                productos.add(productoDetalle);
            }
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Ocurrió un error al buscar registros por id_factura en la tabla detalle_factura_cliente: " + ex.getMessage());
        }
        return productos;
    }

    /*
     * METODO GETTERS AND SETTERS
     */

    public int getIdFacturaCliente() {
        return idFacturaCliente;
    }

    public void setIdFacturaCliente(int idFacturaCliente) {
        this.idFacturaCliente = idFacturaCliente;
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
