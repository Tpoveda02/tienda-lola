package modelo;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Categoria {
    //--------------DECLARACIÓN DE VARIABLES--------------
    private Integer idCategoria;
    private String nombre;
    private String descripcion;
    private Timestamp fechaModificacion;

    //---------------METODO CONSTRUCTOR------------------
    public Categoria(int idCategoria, String nombre, String descripcion) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Categoria() {
    }

    public List<Categoria> listarCategorias(Connection conexion) {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria";

        try {
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id_categoria");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");

                Categoria categoria = new Categoria(id, nombre, descripcion);
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categorias;
    }

    //MÉTODO DE BUSQUEDA ESPECÍFICA
    public ArrayList<Categoria> buscarCategorias(Categoria categoria, Connection conexion) {
        ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM categoria WHERE " +
                    "id_categoria LIKE ? AND nombre LIKE ? AND descripcion LIKE ?");
            if (categoria.getIdCategoria() != null) {
                sentencia.setString(1, "%" + categoria.getIdCategoria() + "%");
            } else {
                sentencia.setString(1, "%%");
            }
            sentencia.setString(2, "%" + categoria.getNombre() + "%");
            sentencia.setString(3, "%" + categoria.getDescripcion() + "%");
            // Ejecuta la sentencia
            ResultSet resultado = sentencia.executeQuery();
            // Asigna los resultados a una lista de las mismas y recorre campo por campo según el registro
            while (resultado.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(resultado.getInt("id_categoria"));
                c.setNombre(resultado.getString("nombre"));
                c.setDescripcion(resultado.getString("descripcion"));
                // Agrega un registro - categoría
                listaCategorias.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista con las categorías
        return listaCategorias;
    }

    public String agregarCategoria(Categoria categoria, Connection conexion) {
        ArrayList<Categoria> sc = buscarCategorias(categoria, conexion); //Busca el cliente
        if (sc.isEmpty() || sc.get(0).getIdCategoria() != categoria.getIdCategoria()) {//valida quel cliente no exista
            String mensajeError = this.validarCamposCategoria(categoria);//Verifica los campos - no sean null; Si no retorna los erroes
            if (mensajeError.equals("")) {
                try {
                    PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO categoria(nombre, descripcion) VALUES (?, ?)");
                    sentencia.setString(1, categoria.getNombre());
                    sentencia.setString(2, categoria.getDescripcion());
                    //Ejecuta la sentencia
                    sentencia.executeUpdate();
                    //Cierra la conexión - sentencia
                    sentencia.close();
                    conexion.close();
                    return "Categoría creada.";
                } catch (SQLException e) {
                    e.printStackTrace();
                    return "Error creación de categoría, campos invalidos.";
                }
            } else {
                return mensajeError;
            }
        }
        return "Categoría ya registrada.";
    }


    public String actualizarCategoria(Categoria categoria, Connection conexion) {
        String mensajeError = this.validarCamposCategoria(categoria);
        categoria.setFechaModificacion(Timestamp.valueOf(LocalDateTime.now()));
        if (mensajeError.equals("")) {//Verifica los campos - no sean null; Si no retorna los erroes
            String sql = "UPDATE categoria SET nombre = ?, descripcion = ?, fecha_modificacion = ? WHERE id_categoria = ?";

            try {
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                sentencia.setString(1, categoria.getNombre());
                sentencia.setString(2, categoria.getDescripcion());
                sentencia.setTimestamp(3, categoria.getFechaModificacion());
                sentencia.setInt(4, categoria.getIdCategoria());
                //Ejecuta la sentencia
                sentencia.executeUpdate();
                //Cierra la conexión - sentencia
                sentencia.close();
                conexion.close();
                return "Categoria actualizada.";
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            return mensajeError;
        }
        return "Categoría no actualizada, campos invalidos";
    }

    public String eliminarCategoria(Categoria categoria, Connection conexion) {
        try {
            // Sentencia para eliminar la categoría según el ID
            PreparedStatement sentencia = conexion.prepareStatement("DELETE FROM categoria WHERE id_categoria = ?");
            sentencia.setInt(1, categoria.getIdCategoria()); // El ID de la categoría que se desea eliminar
            // Ejecuta la sentencia
            int filasAfectadas = sentencia.executeUpdate();
            // Cierra la conexión - sentencia
            sentencia.close();
            conexion.close();
            if (filasAfectadas > 0) {
                // Retorna el mensaje de éxito
                return "Categoría eliminada con éxito";
            } else {
                // Retorna el mensaje de categoría no encontrada
                return "No se encontró la categoría con el ID especificado";
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            // Retorna el mensaje de error
            return "Error eliminando categoría";
        }
    }
 /*
        METODO VALIDAR CAMPOS DEL CLIENTE
     */

    public String validarCamposCategoria(Categoria c) {
        String mensajeError = "";
        if (c.getNombre().length() < 3) {
            mensajeError += "El campo de nombre debe tener almenos 3 caracteres.\n";
        }
        if (c.getDescripcion().length() < 3) {
            mensajeError += "El campo de descripción debe tener almenos 3 caracteres.\n";
        }
        //Concatena y devuelve los errores
        return mensajeError;
    }
    /*
     * METODOS GETTERS AND SETTERS
     *
     */

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Timestamp getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Timestamp fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}
