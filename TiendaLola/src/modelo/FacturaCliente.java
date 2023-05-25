package modelo;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FacturaCliente {
    //--------------DECLARACIÓN DE VARIABLES--------------
    private int idFacturaCliente;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private Timestamp fechaFactura;
    private int cantidadProducto;
    private BigDecimal total;
    private Cliente cliente;
    private Timestamp fechaModificacion;

    private List<DetalleFacturaCliente> detalleProductosFacturaCliente;

    private DetalleFacturaCliente detalleFacturaCliente;

    // Constructor vacío
    public FacturaCliente() {}

    // Constructor con parámetros
    public FacturaCliente(int idFacturaCliente, String direccion, String telefono, String correoElectronico, Timestamp fechaFactura, BigDecimal total, Cliente idCliente, List<DetalleFacturaCliente> detalleProductosFacturaCliente) {
        this.idFacturaCliente = idFacturaCliente;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.fechaFactura = fechaFactura;
        this.total = total;
        this.cliente = cliente;
        this.detalleProductosFacturaCliente = detalleProductosFacturaCliente;
        this.detalleFacturaCliente = new DetalleFacturaCliente();
    }

    // Método para insertar una factura de cliente en la base de datos
    public String insertarFacturaCliente(FacturaCliente factura, Connection conexion) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            // Insertar la factura
            String sql = "INSERT INTO factura_cliente (direccion, telefono, correo_electronico, total, id_cliente) VALUES (?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, factura.getDireccion());
            statement.setString(2, factura.getTelefono());
            statement.setString(3, factura.getCorreoElectronico());
            statement.setBigDecimal(4, factura.getTotal());
            statement.setInt(5, factura.getCliente().getIdCliente());
            statement.executeUpdate();

            // Obtener el id generado por la base de datos
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                factura.setIdFacturaCliente(resultSet.getInt(1));
            }

            // Insertar los productos en la factura
            for (DetalleFacturaCliente producto : factura.getProductos()) {
                detalleFacturaCliente.agregarDetalleFactura(producto,factura,conexion);
            }

        return "Factura creada";
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            return "Error creando la factura";
        }
    }

    /*
     * METODOS ELIMINAR FACTURA
     *
     */
    public String eliminarFacturaCliente(int idFacturaCliente, Connection conexion) {
        // Eliminar una factura cliente de la base de datos
        String sql = "DELETE FROM factura_cliente WHERE id_factura_cliente = ?";
        //Eliminar el detalle de la factura
        detalleFacturaCliente.eliminarDetalleFacturaPorIdFactura(idFacturaCliente,conexion);
        try {
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, idFacturaCliente);
            sentencia.executeUpdate();
            return "Factura eliminada";
        } catch (SQLException e) {
            e.printStackTrace();
            return "No se pudo eliminar la factura";
        }
    }

    public List<FacturaCliente> buscarFacturaCliente(Connection conexion) {
        List<FacturaCliente> facturaClientes = new ArrayList<>();
        try {
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM factura_cliente");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                FacturaCliente facturaCliente = new FacturaCliente();
                facturaCliente.setDireccion(result.getString("direccion"));
                facturaCliente.setTelefono(result.getString("telefono"));
                facturaCliente.setCorreoElectronico(result.getString("correo_electronico"));
                facturaCliente.setFechaFactura(result.getTimestamp("fecha_factura"));
                facturaCliente.setTotal(result.getBigDecimal("total"));
                facturaCliente.setCantidadProducto(result.getInt("cantidad_producto"));
                cliente.setIdCliente(result.getInt("id_cliente"));
                facturaCliente.setCliente(cliente.buscarClientes(cliente,conexion).get(0));
                facturaClientes.add(facturaCliente);
            }
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Ocurrió un error al buscar registros por id_factura_cliente en la tabla factura_cliente: " + ex.getMessage());
        }
        return facturaClientes;
    }

    /*
     * METODOS GETTERS AND SETTERS
     *
     */

    public int getIdFacturaCliente() {
        return idFacturaCliente;
    }

    public void setIdFacturaCliente(int idFacturaCliente) {
        this.idFacturaCliente = idFacturaCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Timestamp getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Timestamp fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Timestamp getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Timestamp fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public List<DetalleFacturaCliente> getProductos() {
        return detalleProductosFacturaCliente;
    }

    public void setProductos(List<DetalleFacturaCliente> productos) {
        this.detalleProductosFacturaCliente = productos;
    }
}