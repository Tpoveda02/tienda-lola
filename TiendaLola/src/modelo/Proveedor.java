package modelo;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Proveedor {

    //--------------DECLARACIÓN DE VARIABLES--------------
    private Integer idProveedor;
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


    public Proveedor(Integer idProveedor, String tipoIdentificacion, String primerNombre, String segundoNombre, String primerApellido,
                     String segundoApellido, String direccion, String telefono, String correoElectronico, Boolean estado) {
        this.idProveedor = idProveedor;
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
        CC,
        NIT,
        DNI,
        PASAPORTE
    }

    public Proveedor() {

    }

    /*
     * --------------------------METODOS CRUD-----------------------
     */
    /*
     * METODO BUSCAR
     */
    public List<Proveedor> listarProveedores(Connection conexion) {
        List<Proveedor> proveedores = new ArrayList<>();
        try {
            PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM PROVEEDOR");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(resultado.getInt("id_proveedor"));
                proveedor.setTipoIdentificacion(resultado.getString("tipo_identificacion"));
                proveedor.setPrimerNombre(resultado.getString("primer_nombre"));
                proveedor.setSegundoNombre(resultado.getString("segundo_nombre"));
                proveedor.setPrimerApellido(resultado.getString("primer_apellido"));
                proveedor.setSegundoApellido(resultado.getString("segundo_apellido"));
                proveedor.setDireccion(resultado.getString("direccion"));
                proveedor.setTelefono(resultado.getString("telefono"));
                proveedor.setCorreoElectronico(resultado.getString("correo_electronico"));
                proveedor.setEstado(resultado.getBoolean("estado"));
                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proveedores;
    }

    public ArrayList<Proveedor> buscarProveedors(Proveedor proveedor, Connection conexion) {
        ArrayList<Proveedor> listaProveedors = new ArrayList<Proveedor>();
        Proveedor c = new Proveedor();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM PROVEEDOR WHERE " +
                    "id_proveedor LIKE ? AND tipo_identificacion LIKE ? " +
                    "AND primer_nombre LIKE ? AND segundo_nombre LIKE ? AND primer_apellido LIKE ? " +
                    "AND segundo_apellido LIKE ? AND direccion LIKE ? AND telefono LIKE ? " +
                    "AND correo_electronico LIKE ? AND estado LIKE ? ");
            if (proveedor.getIdProveedor() != null) {
                sentencia.setString(1, "%" + proveedor.getIdProveedor() + "%");
            } else {
                sentencia.setString(1, "%%");
            }
            sentencia.setString(2, "%" + proveedor.getTipoIdentificacion() + "%");
            sentencia.setString(3, "%" + proveedor.getPrimerNombre() + "%");
            sentencia.setString(4, "%" + proveedor.getSegundoNombre() + "%");
            sentencia.setString(5, "%" + proveedor.getPrimerApellido() + "%");
            sentencia.setString(6, "%" + proveedor.getSegundoApellido() + "%");
            sentencia.setString(7, "%" + proveedor.getDireccion() + "%");
            sentencia.setString(8, "%" + proveedor.getTelefono() + "%");
            sentencia.setString(9, "%" + proveedor.getCorreoElectronico() + "%");
            if (proveedor.getEstado() != null) {
                sentencia.setBoolean(10, proveedor.getEstado());
            } else {
                sentencia.setString(10, "%%");
            }
            //Ejecuta la sentencia
            ResultSet resultado = sentencia.executeQuery();
            //Asigna los resultados a una lista de los mismos y recorre campo por campo según el registro
            while (resultado.next()) {
                c.setIdProveedor(resultado.getInt("id_proveedor"));
                c.setTipoIdentificacion(resultado.getString("tipo_identificacion"));
                c.setPrimerNombre(resultado.getString("primer_nombre"));
                c.setSegundoNombre(resultado.getString("segundo_nombre"));
                c.setPrimerApellido(resultado.getString("primer_apellido"));
                c.setSegundoApellido(resultado.getString("segundo_apellido"));
                c.setDireccion(resultado.getString("direccion"));
                c.setTelefono(resultado.getString("telefono"));
                c.setCorreoElectronico(resultado.getString("correo_electronico"));
                c.setEstado(resultado.getBoolean("estado"));
                //Agrega un registro - proveedor
                listaProveedors.add(c);
                c = new Proveedor();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Retorna la lista con los proveedors
        return listaProveedors;
    }
    /*
     * METODO CREAR
     */

    public String agregarProveedor(Proveedor proveedor, Connection conexion) {
        ArrayList<Proveedor> sc = buscarProveedors(proveedor, conexion); //Busca el proveedor
        if (sc.isEmpty() || sc.get(0).getIdProveedor() != proveedor.getIdProveedor()) {//valida quel proveedor no exista
            String mensajeError = this.validarCamposProveedor(proveedor);//Verifica los campos - no sean null; Si no retorna los erroes
            if (mensajeError.equals("")) {
                try {
                    PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO PROVEEDOR (id_proveedor, tipo_identificacion, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, direccion, telefono, correo_electronico) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");//Sentencia para insertar el proveedor en la BD
                    //Remplaza en la sentencia anterior los signos de interrogacion en el orden que estan
                    sentencia.setInt(1, proveedor.getIdProveedor());
                    sentencia.setString(2, proveedor.getTipoIdentificacion());
                    sentencia.setString(3, proveedor.getPrimerNombre());
                    sentencia.setString(4, proveedor.getSegundoNombre());
                    sentencia.setString(5, proveedor.getPrimerApellido());
                    sentencia.setString(6, proveedor.getSegundoApellido());
                    sentencia.setString(7, proveedor.getDireccion());
                    sentencia.setString(8, proveedor.getTelefono());
                    sentencia.setString(9, proveedor.getCorreoElectronico());
                    //Ejecuta la sentencia
                    sentencia.executeUpdate();
                    //Cierra la conexion - sentencia
                    sentencia.close();
                    conexion.close();
                    return "Proveedor creado.";
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                    return "Error creacion de proveedor, campos invalidos.";
                }
            } else {
                return mensajeError;
            }
        }
        return "Proveedor ya registrado.";
    }
    /*
     * METODO ACTUALIZAR
     */

    public String actualizarProveedor(Proveedor proveedor, Connection conexion) {
        String mensajeError = this.validarCamposProveedor(proveedor);
        proveedor.setFechaModificacion(Timestamp.valueOf(LocalDateTime.now()));
        if (mensajeError.equals("")) {//Verifica los campos - no sean null; Si no retorna los erroes
            try {
                //Sentencia para actualizar
                PreparedStatement sentencia = conexion.prepareStatement("UPDATE PROVEEDOR SET tipo_identificacion = ?, " +
                        "primer_nombre = ?, segundo_nombre = ?, primer_apellido = ?, segundo_apellido = ?, " +
                        "direccion = ?, telefono = ?, correo_electronico = ?," +
                        "fecha_modificacion = ?, estado = ? WHERE id_proveedor = ?");
                sentencia.setString(1, proveedor.getTipoIdentificacion());
                sentencia.setString(2, proveedor.getPrimerNombre());
                sentencia.setString(3, proveedor.getSegundoNombre());
                sentencia.setString(4, proveedor.getPrimerApellido());
                sentencia.setString(5, proveedor.getSegundoApellido());
                sentencia.setString(6, proveedor.getDireccion());
                sentencia.setString(7, proveedor.getTelefono());
                sentencia.setString(8, proveedor.getCorreoElectronico());
                sentencia.setTimestamp(9, proveedor.getFechaModificacion());
                sentencia.setBoolean(10, proveedor.getEstado());
                sentencia.setInt(11, proveedor.getIdProveedor());
                //Ejecuta la sentencia
                sentencia.executeUpdate();
                //Cierra la conexion - sentencia
                sentencia.close();
                conexion.close();
                return "Proveedor actualizado.";
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } else {
            return mensajeError;
        }
        return "Proveedor no actualizado, campos invalidos";
    }

    /*
        METODO VALIDAR CAMPOS DEL CLIENTE
     */

    public String validarCamposProveedor(Proveedor p) {
        String mensajeError = "";
        if ((p.getIdProveedor()+"").length() < 7 || (p.getIdProveedor()+"").length() > 10) {
            mensajeError += "El campo de identificacion debe tener entre 7 y 10 caracteres.\n";
        }
        try {
            TipoIdentificacion identificacion = TipoIdentificacion.valueOf(getTipoIdentificacion());
            // El valor ingresado es uno de los valores permitidos en el campo ENUM
        } catch (IllegalArgumentException e) {
            mensajeError += " El campo tipo de identifiacion ingresado no es uno de los valores permitidos.";
        }
        if (p.getTelefono().length() < 7 || p.getTelefono().length() > 10) {
            mensajeError += "El campo de teléfono debe tener entre 7 y 10 caracteres.\n";
        }
        if (p.getPrimerNombre().length() < 3) {
            mensajeError += "El campo de primer nombre debe tener almenos 3 caracteres.\n";
        }
        if (p.getSegundoNombre().length() < 3) {
            mensajeError += "El campo de segundo nombre debe tener almenos 3 caracteres.\n";
        }
        if (p.getPrimerApellido().length() < 3) {
            mensajeError += "El campo de primer apellido debe tener almenos 3 caracteres.\n";
        }
        if (p.getSegundoApellido().length() < 3) {
            mensajeError += "El campo de segundo apellido debe tener almenos 3 caracteres.\n";
        }
        if (!this.validarCorreo(p.getCorreoElectronico())) {
            mensajeError += "El campo del correo es invalido, ingrese un correo valido.\n";
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

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;;
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

    public Timestamp getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Timestamp fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}
