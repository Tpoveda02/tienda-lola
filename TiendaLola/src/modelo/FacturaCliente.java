package modelo;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FacturaCliente {
    //--------------DECLARACIÓN DE VARIABLES--------------
    private Integer idFacturaCliente;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private Timestamp fechaFactura;
    private int cantidadProducto;
    private Integer total;
    private Cliente cliente;
    private Boolean estado;
    private Timestamp fechaModificacion;

    private List<DetalleFacturaCliente> detalleProductosFacturaCliente;

    private DetalleFacturaCliente detalleFacturaCliente;

    // Constructor vacío
    public FacturaCliente() {}

    // Constructor con parámetros
    public FacturaCliente(int idFacturaCliente, String direccion, String telefono, String correoElectronico, Timestamp fechaFactura,
                          Integer total, Cliente cliente, List<DetalleFacturaCliente> detalleProductosFacturaCliente, Boolean estado) {
        this.idFacturaCliente = idFacturaCliente;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.fechaFactura = fechaFactura;
        this.total = total;
        this.cliente = cliente;
        this.detalleProductosFacturaCliente = detalleProductosFacturaCliente;
        this.detalleFacturaCliente = new DetalleFacturaCliente();
        this.estado = estado;
    }

    // Método para insertar una factura de cliente en la base de datos
    public String insertarFacturaCliente(FacturaCliente factura, Connection conexion)  {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            // Insertar la factura
            String sql = "INSERT INTO factura_cliente (direccion, telefono, correo_electronico, total, id_cliente) VALUES (?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, factura.getDireccion());
            statement.setString(2, factura.getTelefono());
            statement.setString(3, factura.getCorreoElectronico());
            statement.setInt(4, factura.getTotal());
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
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
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

    // METODO PARA LISTAR TODAS LAS FACTURAS
    public List<FacturaCliente> listarFacturasCliente(Connection conexion) {
        List<FacturaCliente> facturaClientes = new ArrayList<>();
        DetalleFacturaCliente detalleFacturaProductosCliente = new DetalleFacturaCliente();
        try {
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM factura_cliente");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                FacturaCliente facturaCliente = new FacturaCliente();
                facturaCliente.setIdFacturaCliente(result.getInt("id_factura_cliente"));
                facturaCliente.setDireccion(result.getString("direccion"));
                facturaCliente.setTelefono(result.getString("telefono"));
                facturaCliente.setCorreoElectronico(result.getString("correo_electronico"));
                facturaCliente.setFechaFactura(result.getTimestamp("fecha_factura"));
                facturaCliente.setTotal(result.getInt("total"));
                facturaCliente.setCantidadProducto(result.getInt("cantidad_producto"));
                cliente.setIdCliente(result.getInt("id_cliente"));
                facturaCliente.setCliente(cliente.buscarClientes(cliente,conexion).get(0));
                facturaCliente.setProductos(detalleFacturaProductosCliente.buscarPorIdFactura(facturaCliente.getIdFacturaCliente(),conexion));
                facturaClientes.add(facturaCliente);
            }
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Ocurrió un error al buscar registros por id_factura_cliente en la tabla factura_cliente: " + ex.getMessage());
        }
        return facturaClientes;
    }
    // METODO PAR BUSCAR UNA FACTURA POR UN CAMPO ESPECÍFICO
    public ArrayList<FacturaCliente> buscarFacturaCliente(Integer idFacturaCliente, String direccion, String telefono, String correoElectronico, String fechaFactura, String total, String cantidadProducto, String idCliente, Connection conexion) {
        ArrayList<FacturaCliente> listaFacturasCliente = new ArrayList<FacturaCliente>();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM FACTURA_CLIENTE WHERE " +
                    "id_factura_cliente LIKE ? AND direccion LIKE ? AND correo_electronico LIKE ?  AND fecha_factura LIKE ? " +
                    "AND telefono LIKE ? AND cantidad_producto LIKE ? AND total LIKE ? " + "AND id_cliente LIKE ? ");
            if (idFacturaCliente != null) {
                sentencia.setString(1, "%" + idFacturaCliente + "%");
            } else {
                sentencia.setString(1, "%%");
            }
            sentencia.setString(2, "%" + direccion + "%");
            sentencia.setString(3,"%" + correoElectronico + "%");
            sentencia.setString(4,"%" + fechaFactura + "%");
            sentencia.setString(5, "%" + telefono + "%");
            sentencia.setString(6, "%" + cantidadProducto + "%");
            sentencia.setString(7, "%" + total + "%");
            sentencia.setString(8, "%" + idCliente + "%");
            // Ejecuta la sentencia
            ResultSet resultado = sentencia.executeQuery();
            // Asigna los resultados a una lista de los mismos y recorre campo por campo según el registro
            while (resultado.next()) {
                FacturaCliente facturaCliente = new FacturaCliente();
                facturaCliente.setIdFacturaCliente(resultado.getInt("id_factura_cliente"));
                facturaCliente.setDireccion(resultado.getString("direccion"));
                facturaCliente.setTelefono(resultado.getString("telefono"));
                facturaCliente.setCorreoElectronico(resultado.getString("correo_electronico"));
                facturaCliente.setFechaFactura(resultado.getTimestamp("fecha_factura"));
                facturaCliente.setTotal(resultado.getInt("total"));
                facturaCliente.setCantidadProducto(resultado.getInt("cantidad_producto"));
                //Buscar el cliente de esa factura
                Cliente c = new Cliente();
                c.setIdCliente(resultado.getInt("id_cliente"));
                Cliente cliente = c.buscarClientes(c,conexion).get(0);
                //Asignar el cliente
                facturaCliente.setCliente(cliente);
                // Agrega un registro - factura
                listaFacturasCliente.add(facturaCliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista con los productos
        return listaFacturasCliente;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}