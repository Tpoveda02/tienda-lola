package modelo;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {

    //--------------DECLARACIÓN DE VARIABLES--------------
    private Integer idCliente;
    private String tipoIdentificacion;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private Boolean estado;
    private Timestamp fechaModificacion;

    //---------------METODO CONSTRUCTOR------------------


    public Cliente(Integer idCliente, String tipoIdentificacion, String primerNombre, String segundoNombre,
                   String primerApellido, String segundoApellido, String direccion, String telefono, String correoElectronico, Boolean estado) {
        this.idCliente = idCliente;
        this.tipoIdentificacion = tipoIdentificacion;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.estado = estado;
    }

    //VALIDACIÓN ENUM PERMITIDOS
    public enum TipoIdentificacion {
        DNI,
        NIE,
        PASAPORTE,
        CC
    }

    public Cliente() {

    }

    /*
     * --------------------------METODOS CRUD-----------------------
     */
    /*
     * METODO BUSCAR
     */
    public List<Cliente> listarClientes(Connection conexion) {
        List<Cliente> clientes = new ArrayList<>();
        try {
            //Sentecia consultar todos los clientes
            PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM cliente");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(resultado.getInt("id_cliente"));
                cliente.setTipoIdentificacion(resultado.getString("tipo_identificacion"));
                cliente.setPrimerNombre(resultado.getString("primer_nombre"));
                cliente.setSegundoNombre(resultado.getString("segundo_nombre"));
                cliente.setPrimerApellido(resultado.getString("primer_apellido"));
                cliente.setSegundoApellido(resultado.getString("segundo_apellido"));
                cliente.setDireccion(resultado.getString("direccion"));
                cliente.setTelefono(resultado.getString("telefono"));
                cliente.setCorreoElectronico(resultado.getString("correo_electronico"));
                cliente.setEstado(resultado.getBoolean("Estado"));
                //Agregar el cleitne a la lista
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    public ArrayList<Cliente> buscarClientes(Cliente cliente, Connection conexion) {
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        Cliente c = new Cliente();
        try {
            //Sentencia para filtrar por parametros específicos
            PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM CLIENTE WHERE " +
                    "id_cliente LIKE ? AND tipo_identificacion LIKE ? " +
                    "AND primer_nombre LIKE ? AND segundo_nombre LIKE ? AND primer_apellido LIKE ? " +
                    "AND segundo_apellido LIKE ? AND direccion LIKE ? AND telefono LIKE ? " +
                    "AND correo_electronico LIKE ? AND estado LIKE ?");
            if (cliente.getIdCliente() != null) {
                sentencia.setString(1, "%" + cliente.getIdCliente() + "%");
            } else {
                sentencia.setString(1, "%%");
            }
            sentencia.setString(2, "%" + cliente.getTipoIdentificacion() + "%");
            sentencia.setString(3, "%" + cliente.getPrimerNombre() + "%");
            sentencia.setString(4, "%" + cliente.getSegundoNombre() + "%");
            sentencia.setString(5, "%" + cliente.getPrimerApellido() + "%");
            sentencia.setString(6, "%" + cliente.getSegundoApellido() + "%");
            sentencia.setString(7, "%" + cliente.getDireccion() + "%");
            sentencia.setString(8, "%" + cliente.getTelefono() + "%");
            sentencia.setString(9, "%" + cliente.getCorreoElectronico() + "%");
            if (cliente.getEstado() != null) {
                sentencia.setBoolean(10,  cliente.getEstado());
            } else {
                sentencia.setString(10, "%%");
            }
            //Ejecuta la sentencia
            ResultSet resultado = sentencia.executeQuery();
            //Asigna los resultados a una lista de los mismos y recorre campo por campo según el registro
            while (resultado.next()) {
                c.setIdCliente(resultado.getInt("id_cliente"));
                c.setTipoIdentificacion(resultado.getString("tipo_identificacion"));
                c.setPrimerNombre(resultado.getString("primer_nombre"));
                c.setSegundoNombre(resultado.getString("segundo_nombre"));
                c.setPrimerApellido(resultado.getString("primer_apellido"));
                c.setSegundoApellido(resultado.getString("segundo_apellido"));
                c.setDireccion(resultado.getString("direccion"));
                c.setTelefono(resultado.getString("telefono"));
                c.setCorreoElectronico(resultado.getString("correo_electronico"));
                c.setEstado(resultado.getBoolean("estado"));
                //Agrega un registro - cliente
                listaClientes.add(c);
                c = new Cliente();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Retorna la lista con los clientes
        return listaClientes;
    }
    /*
     * METODO CREAR
     */

    public String agregarCliente(Cliente cliente, Connection conexion) {
        ArrayList<Cliente> sc = buscarClientes(cliente, conexion); //Busca el cliente
        if (sc.isEmpty() || sc.get(0).getIdCliente() != cliente.getIdCliente()) {//valida quel cliente no exista
            String mensajeError = this.validarCamposCliente(cliente);//Verifica los campos - no sean null; Si no retorna los erroes
            if (mensajeError.equals("")) {
                try {
                    PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO CLIENTE (id_cliente, tipo_identificacion, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, direccion, telefono, correo_electronico) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");//Sentencia para insertar el cliente en la BD
                    //Remplaza en la sentencia anterior los signos de interrogación en el orden que estan
                    sentencia.setInt(1, cliente.getIdCliente());
                    sentencia.setString(2, cliente.getTipoIdentificacion());
                    sentencia.setString(3, cliente.getPrimerNombre());
                    sentencia.setString(4, cliente.getSegundoNombre());
                    sentencia.setString(5, cliente.getPrimerApellido());
                    sentencia.setString(6, cliente.getSegundoApellido());
                    sentencia.setString(7, cliente.getDireccion());
                    sentencia.setString(8, cliente.getTelefono());
                    sentencia.setString(9, cliente.getCorreoElectronico());

                    //Ejecuta la sentencia
                    sentencia.executeUpdate();
                    //Cierra la conexión - sentencia
                    sentencia.close();
                    conexion.close();
                    return "Cliente creado.";
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                    return "Error creación de cliente, campos invalidos.";
                }
            } else {
                return mensajeError;
            }
        }
        return "Cliente ya registrado.";
    }
    /*
     * METODO ACTUALIZAR
     */

    public String actualizarCliente(Cliente cliente, Connection conexion) {
        String mensajeError = this.validarCamposCliente(cliente);
        cliente.setFechaModificacion(Timestamp.valueOf(LocalDateTime.now()));
        if (mensajeError.equals("")) {//Verifica los campos - no sean null; Si no retorna los erroes
            try {
                //Sentencia para actualizar
                PreparedStatement sentencia = conexion.prepareStatement("UPDATE CLIENTE SET tipo_identificacion = ?, " +
                        "primer_nombre = ?, segundo_nombre = ?, primer_apellido = ?, segundo_apellido = ?, " +
                        "direccion = ?, telefono = ?, correo_electronico = ?," +
                        "fecha_modificacion = ?, estado = ? WHERE id_cliente = ?");
                sentencia.setString(1, cliente.getTipoIdentificacion());
                sentencia.setString(2, cliente.getPrimerNombre());
                sentencia.setString(3, cliente.getSegundoNombre());
                sentencia.setString(4, cliente.getPrimerApellido());
                sentencia.setString(5, cliente.getSegundoApellido());
                sentencia.setString(6, cliente.getDireccion());
                sentencia.setString(7, cliente.getTelefono());
                sentencia.setString(8, cliente.getCorreoElectronico());
                sentencia.setTimestamp(9, cliente.getFechaModificacion());
                sentencia.setBoolean(10, cliente.getEstado());
                sentencia.setInt(11, cliente.getIdCliente());
                //Ejecuta la sentencia
                sentencia.executeUpdate();
                //Cierra la conexión - sentencia
                sentencia.close();
                conexion.close();
                return "Cliente actualizado.";
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } else {
            return mensajeError;
        }
        return "Cliente no actualizado, campos invalidos";
    }

    /*
        METODO VALIDAR CAMPOS DEL CLIENTE
     */

    public String validarCamposCliente(Cliente c) {
        String mensajeError = "";
        if ((c.getIdCliente()+"").length() < 7 || (c.getIdCliente()+"").length() > 10) {
            mensajeError += "El campo de identificación debe tener entre 7 y 10 caracteres.\n";
        }
        try {
            Proveedor.TipoIdentificacion identificacion = Proveedor.TipoIdentificacion.valueOf(getTipoIdentificacion());
            // El valor ingresado es uno de los valores permitidos en el campo ENUM
        } catch (IllegalArgumentException e) {
            mensajeError += " El campo tipo de identifiacion ingresado no es uno de los valores permitidos.";
        }
        if (c.getTelefono().length() < 7 || c.getTelefono().length() > 10) {
            mensajeError += "El campo de teléfono debe tener entre 7 y 10 caracteres.\n";
        }
        if (c.getPrimerNombre().length() < 3) {
            mensajeError += "El campo de primer nombre debe tener almenos 3 caracteres.\n";
        }
        if (c.getSegundoNombre().length() < 3) {
            mensajeError += "El campo de segundo nombre debe tener almenos 3 caracteres.\n";
        }
        if (c.getPrimerApellido().length() < 3) {
            mensajeError += "El campo de primer apellido debe tener almenos 3 caracteres.\n";
        }
        if (c.getSegundoApellido().length() < 3) {
            mensajeError += "El campo de segundo apellido debe tener almenos 3 caracteres.\n";
        }
        if (!this.validarCorreo(c.getCorreoElectronico())) {
            mensajeError += "El campo del correo es invalido, ingrese un correo valido.\n";
        }
        //Concatena y devuelve los errores
        return mensajeError;
    }
    //Metodo para validar el correo
    public boolean validarCorreo(String correo) {
        // Expresión regular para validar correo electrónico
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        // Compilar la expresión regular en un objeto Pattern
        Pattern pattern = Pattern.compile(regex);

        // Verificar si el correo electrónico coincide con la expresión regular
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }
    /*
     * METODOS GETTERS AND SETTERS
     *
     */

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Timestamp getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Timestamp fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}
