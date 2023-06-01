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
    private Double precio;
    private Producto producto;

    /*
     * METODO CONSTRUCTOR
     */

    public DetalleFacturaCliente(Integer idDetalleFacturaCliente, Integer idFacturaCliente, int cantidad, Double precio, Producto producto) {
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
            String sql = "INSERT INTO detalle_factura_cliente (id_factura, id_producto, cantidad, precio) VALUES (?, ?, ?, ?)";
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, factura.getIdFacturaCliente());
            sentencia.setInt(2, detalleFacturaCliente.getProducto().getIdProducto());
            sentencia.setInt(3, detalleFacturaCliente.getCantidad());
            sentencia.setDouble(4, detalleFacturaCliente.getPrecio());
            //Ejecuta la sentencia
            sentencia.executeUpdate();
            //Cierra la conexion - sentencia
            return "Productos agregados a la factura.";
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return "Error creacion del detalle factura, campos invalidos.";
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
                productoDetalle.setIdFacturaCliente(result.getInt("id_factura"));
                Producto producto =  new Producto();
                producto = producto.buscarProductos(result.getInt("id_producto"),"","","","","","","","","","","","","","","",null,conexion).get(0);
                productoDetalle.setProducto(producto);
                productoDetalle.setCantidad(result.getInt("cantidad"));
                productoDetalle.setPrecio(result.getDouble("precio"));
                productos.add(productoDetalle);
            }
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
