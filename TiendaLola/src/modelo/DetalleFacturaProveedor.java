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
    private Double precio;
    private Producto producto;

    /*
     * METODO CONSTRUCTOR
     */

    public DetalleFacturaProveedor(Integer idDetalleFacturaProveedor, Integer idFacturaProveedor, int cantidad, Double precio, Producto producto) {
        this.idDetalleFacturaProveedor = idDetalleFacturaProveedor;
        this.idFacturaProveedor = idFacturaProveedor;
        this.cantidad = cantidad;
        this.precio = precio;
        this.producto = producto;
    }

    public DetalleFacturaProveedor(){}

    /*
     * METODO CREAR
     */

    public String agregarDetalleFactura(DetalleFacturaProveedor detalleFacturaProveedor, FacturaProveedor factura, Connection conexion) {
        PreparedStatement sentencia = null;
        try {
            String sql = "INSERT INTO factura_proveedor_producto (id_factura_proveedor, id_producto, cantidad, precio) VALUES (?, ?, ?, ?)";
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, factura.getIdFacturaProveedor());
            sentencia.setInt(2, detalleFacturaProveedor.getProducto().getIdProducto());
            sentencia.setInt(3, detalleFacturaProveedor.getCantidad());
            sentencia.setDouble(4, detalleFacturaProveedor.getPrecio());
            sentencia.executeUpdate();
            //Ejecuta la sentencia
            sentencia.executeUpdate();
            //Cierra la conexion - sentencia
            sentencia.close();
            return "Productos agregados a la factura.";
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return "Error creacion del detalle factura, campos invalidos.";
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
                productoDetalle.setPrecio(result.getDouble("precio"));
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
