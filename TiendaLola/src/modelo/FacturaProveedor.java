package modelo;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FacturaProveedor {
    //--------------DECLARACIÓN DE VARIABLES--------------
    private Integer idFacturaProveedor;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private Timestamp fechaFactura;
    private int cantidadProducto;
    private Double total;
    private Proveedor proveedor;

    private Timestamp fechaModificacion;

    private List<DetalleFacturaProveedor> detalleProductosFacturaProveedor;

    private DetalleFacturaProveedor detalleFacturaProveedor;

    // Constructor vacío
    public FacturaProveedor() {
    }

    // Constructor con parámetros
    public FacturaProveedor(Integer idFacturaProveedor, String direccion, String telefono, String correoElectronico,
                            Timestamp fechaFactura, int cantidadProducto, Double total, Proveedor proveedor, List<DetalleFacturaProveedor> detalleProductosFacturaProveedor) {
        this.idFacturaProveedor = idFacturaProveedor;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.fechaFactura = fechaFactura;
        this.cantidadProducto = cantidadProducto;
        this.total = total;
        this.proveedor = proveedor;
        this.detalleProductosFacturaProveedor = detalleProductosFacturaProveedor;
        this.detalleFacturaProveedor = new DetalleFacturaProveedor();
    }

    // Método para insertar una factura de proveedor en la base de datos
    public String insertarFacturaProveedor(FacturaProveedor factura, Connection conexion) {
        String mensajeError = this.validarCamposFacturaProveedor(factura);//Verifica los campos - no sean null; Si no retorna los erroes
        if (mensajeError.equals("")) {
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            try {
                // Insertar la factura
                String sql = "INSERT INTO factura_proveedor (direccion, telefono, correo_electronico, cantidad_producto,total, id_proveedor) VALUES (?, ?, ?, ?, ?,?)";
                statement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, factura.getDireccion());
                statement.setString(2, factura.getTelefono());
                statement.setString(3, factura.getCorreoElectronico());
                statement.setInt(4, factura.getCantidadProducto());
                statement.setDouble(5, factura.getTotal());
                statement.setInt(6, factura.getProveedor().getIdProveedor());
                statement.executeUpdate();

                // Obtener el id generado por la base de datos
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    factura.setIdFacturaProveedor(resultSet.getInt(1));
                }

                // Insertar los productos en la factura
                for (DetalleFacturaProveedor producto : factura.getProductos()) {
                    detalleFacturaProveedor.agregarDetalleFactura(producto, factura, conexion);
                }

                return "Factura creada";
            } catch (SQLException ex) {
                System.out.println("Ocurrió un error al buscar registros por id_factura_proveedor en la tabla factura_proveedor: " + ex.getMessage());
                return "Error creando la factura";
            }
        }else{
            return mensajeError;
        }

    }


    // METODO PARA LISTAR TODAS LAS FACTURAS
    public List<FacturaProveedor> listarFacturasProveedor(Connection conexion) {
        List<FacturaProveedor> facturaProveedors = new ArrayList<>();
        DetalleFacturaProveedor detalleFacturaProductosProveedor = new DetalleFacturaProveedor();
        try {
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM factura_proveedor");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                FacturaProveedor facturaProveedor = new FacturaProveedor();
                facturaProveedor.setIdFacturaProveedor(result.getInt("id_factura_proveedor"));
                facturaProveedor.setDireccion(result.getString("direccion"));
                facturaProveedor.setTelefono(result.getString("telefono"));
                facturaProveedor.setCorreoElectronico(result.getString("correo_electronico"));
                facturaProveedor.setFechaFactura(result.getTimestamp("fecha_factura_proveedor"));
                facturaProveedor.setTotal(result.getDouble("total"));
                facturaProveedor.setCantidadProducto(result.getInt("cantidad_producto"));
                Proveedor proveedor = new Proveedor(result.getInt("id_proveedor"), "", "", "", "", "", "", "", "", true);
                facturaProveedor.setProveedor(proveedor.buscarProveedors(proveedor, conexion).get(0));
                facturaProveedor.setProductos(detalleFacturaProductosProveedor.buscarPorIdFactura(facturaProveedor.getIdFacturaProveedor(), conexion));
                facturaProveedors.add(facturaProveedor);
            }
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Ocurrió un error al buscar registros por id_factura_proveedor en la tabla factura_proveedor: " + ex.getMessage());
        }
        return facturaProveedors;
    }

    // METODO PAR BUSCAR UNA FACTURA POR UN CAMPO ESPECÍFICO
    public ArrayList<FacturaProveedor> buscarFacturaProveedor(Integer idFacturaProveedor, String direccion, String telefono, String correoElectronico, String fechaFactura, String total, String cantidadProducto, String idProveedor, Connection conexion) {
        ArrayList<FacturaProveedor> listaFacturasProveedor = new ArrayList<FacturaProveedor>();
        DetalleFacturaProveedor detalleFacturaProductosProveedor = new DetalleFacturaProveedor();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM FACTURA_PROVEEDOR WHERE " +
                    "id_factura_proveedor LIKE ? AND direccion LIKE ? AND correo_electronico LIKE ?  AND fecha_factura_proveedor LIKE ? " +
                    "AND telefono LIKE ? AND cantidad_producto LIKE ? AND total LIKE ? " + "AND id_proveedor LIKE ? ");
            if (idFacturaProveedor != null) {
                sentencia.setString(1, "%" + idFacturaProveedor + "%");
            } else {
                sentencia.setString(1, "%%");
            }
            sentencia.setString(2, "%" + direccion + "%");
            sentencia.setString(3, "%" + correoElectronico + "%");
            sentencia.setString(4, "%" + fechaFactura + "%");
            sentencia.setString(5, "%" + telefono + "%");
            sentencia.setString(6, "%" + cantidadProducto + "%");
            sentencia.setString(7, "%" + total + "%");
            sentencia.setString(8, "%" + idProveedor + "%");
            // Ejecuta la sentencia
            ResultSet resultado = sentencia.executeQuery();
            // Asigna los resultados a una lista de los mismos y recorre campo por campo según el registro
            while (resultado.next()) {
                FacturaProveedor facturaProveedor = new FacturaProveedor();
                facturaProveedor.setIdFacturaProveedor(resultado.getInt("id_factura_proveedor"));
                facturaProveedor.setDireccion(resultado.getString("direccion"));
                facturaProveedor.setTelefono(resultado.getString("telefono"));
                facturaProveedor.setCorreoElectronico(resultado.getString("correo_electronico"));
                facturaProveedor.setFechaFactura(resultado.getTimestamp("fecha_factura_proveedor"));
                facturaProveedor.setTotal(resultado.getDouble("total"));
                facturaProveedor.setCantidadProducto(resultado.getInt("cantidad_producto"));
                //Buscar el proveedor de esa factura
                Proveedor proveedor = new Proveedor(resultado.getInt("id_proveedor"), "", "", "", "", "", "", "", "", true);
                facturaProveedor.setProveedor(proveedor.buscarProveedors(proveedor, conexion).get(0));
                System.out.println(detalleFacturaProductosProveedor.buscarPorIdFactura(facturaProveedor.getIdFacturaProveedor(), conexion));
                facturaProveedor.setProductos(detalleFacturaProductosProveedor.buscarPorIdFactura(facturaProveedor.getIdFacturaProveedor(), conexion));
                // Agrega un registro - factura
                listaFacturasProveedor.add(facturaProveedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista con los productos
        return listaFacturasProveedor;
    }
         /*
        METODO VALIDAR CAMPOS DEL CLIENTE
     */

    public String validarCamposFacturaProveedor(FacturaProveedor fp) {
        String mensajeError = "";
        if (fp.getTelefono().length() < 7 || fp.getTelefono().length() > 10) {
            mensajeError += "El campo de teléfono debe tener entre 7 y 10 caracteres.\n";
        }
        if (fp.getDireccion().length() < 3) {
            mensajeError += "El campo de dirección debe tener almenos 3 caracteres.\n";
        }
        if (!this.validarCorreo(fp.getCorreoElectronico())) {
            mensajeError += "El campo del correo es invalido, ingrese un correo valido.\n";
        }
        if (fp.getProductos().size() < 1) {
            mensajeError += "No se han agregado productos\n";
        }
        //Concatena y devuelve los errores
        return mensajeError;
    }
    //Metodo para validar el correo
    public boolean validarCorreo(String correo) {
        // Expresion regular para validar correo electionico
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        // Compilar la expresion regular en un objeto Pattern
        Pattern pattern = Pattern.compile(regex);

        // Verificar si el correo electionico coincide con la expresion regular
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }
    /*
     * METODOS GETTERS AND SETTERS
     *
     */

    public int getIdFacturaProveedor() {
        return idFacturaProveedor;
    }

    public void setIdFacturaProveedor(int idFacturaProveedor) {
        this.idFacturaProveedor = idFacturaProveedor;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Timestamp getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Timestamp fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public List<DetalleFacturaProveedor> getProductos() {
        return detalleProductosFacturaProveedor;
    }

    public void setProductos(List<DetalleFacturaProveedor> productos) {
        this.detalleProductosFacturaProveedor = productos;
    }
}