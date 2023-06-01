package modelo;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Producto {
    //--------------DECLARACIÓN DE VARIABLES--------------
    private Integer idProducto;
    private String nombre;
    private String tipoContenidoNeto;
    private Integer contenidoNeto;
    private Integer valorContenido;
    private String empaqueGeneral;
    private String empaque;
    private String descripcion;
    private String recomendaciones;
    private Integer precioProveedor;
    private Integer porcentajeIva;
    private Integer precioSinIva;
    private Integer precioVenta;
    private Date fechaVencimiento;
    private int cantidad;
    private Categoria categoria;
    private Boolean estado;
    private Timestamp fechaModificacion;

    // Constructor vacío
    public Producto() {}

    // Constructor con parámetros
    public Producto(Integer idProducto, String nombre, String tipoContenidoNeto,
                    Integer contenidoNeto, Integer valorContenido, String empaqueGeneral,
                    String empaque, String descripcion, String recomendaciones, Integer precioProveedor,
                    Integer porcentajeIva, Integer precioSinIva, Integer precioVenta,
                    Date fechaVencimiento, int cantidad, Categoria categoria, Boolean estado) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.tipoContenidoNeto = tipoContenidoNeto;
        this.contenidoNeto = contenidoNeto;
        this.valorContenido = valorContenido;
        this.empaqueGeneral = empaqueGeneral;
        this.empaque = empaque;
        this.descripcion = descripcion;
        this.recomendaciones = recomendaciones;
        this.precioProveedor = precioProveedor;
        this.porcentajeIva = porcentajeIva;
        this.precioSinIva = precioSinIva;
        this.precioVenta = precioVenta;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.estado = estado;
    }


    //VALIDACIÓN ENUM PERMITIDOS
    public enum TipoContenidoNeto {
        gr,
        ml
    }
    /*
     * METODO BUSCAR
     */
    public List<Producto> listarProductos(Connection conexion) {
        List<Producto> productos = new ArrayList<>();
        try {
            PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM producto");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(resultado.getInt("id_producto"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setTipoContenidoNeto(resultado.getString("tipo_contenido_neto"));
                producto.setContenidoNeto(resultado.getInt("contenido_neto"));
                producto.setValorContenido(resultado.getInt("valor_contenido"));
                producto.setEmpaqueGeneral(resultado.getString("empaque_general"));
                producto.setEmpaque(resultado.getString("empaque"));
                producto.setDescripcion(resultado.getString("descripcion"));
                producto.setRecomendaciones(resultado.getString("recomendaciones"));
                producto.setPrecioProveedor(resultado.getInt("precio_proveedor"));
                producto.setPorcentajeIva(resultado.getInt("porcentaje_iva"));
                producto.setPrecioSinIva(resultado.getInt("precio_sin_iva"));
                producto.setPrecioVenta(resultado.getInt("precio_venta"));
                producto.setFechaVencimiento(resultado.getDate("fecha_vencimiento"));
                producto.setCantidad(resultado.getInt("cantidad"));

                //Se obtiene la categoría del producto
                int idCategoria = resultado.getInt("id_categoria");
                Categoria categoria = new Categoria(idCategoria,"","",true);
                categoria = categoria.buscarCategorias(categoria, conexion).get(0);
                producto.setCategoria(categoria);
                producto.setEstado(resultado.getBoolean("estado"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }
    public ArrayList<Producto> buscarProductos(Integer idProducto, String nombre, String tipoContenidoNeto,
                                               String contenidoNeto, String valorContenido,
                                               String empaqueGeneral, String empaque, String descripcion,
                                               String recomendaciones, String precioProveedor,
                                               String porcentajeIva, String precioSinIva,
                                               String precioVenta, String fechaVencimiento,
                                               String cantidad, String idCategoria, Boolean estado, Connection conexion) {
        ArrayList<Producto> listaProductos = new ArrayList<Producto>();
        Producto p = new Producto();
        try {
            PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM PRODUCTO WHERE " +
                    "id_producto LIKE ? AND nombre LIKE ? " +
                    "AND tipo_contenido_neto LIKE ? AND contenido_neto LIKE ? " + "AND valor_contenido LIKE ? " +
                    "AND empaque_general LIKE ? AND empaque LIKE ? AND descripcion LIKE ? " +
                    "AND recomendaciones LIKE ? AND precio_proveedor LIKE ? " +
                    "AND porcentaje_iva LIKE ? AND precio_sin_iva LIKE ? " +
                    "AND precio_venta LIKE ? AND fecha_vencimiento LIKE ? " +
                    "AND cantidad LIKE ? AND id_categoria LIKE ? AND estado LIKE ?");
            if (idProducto != null) {
                sentencia.setString(1, "%" + idProducto + "%");
            } else {
                sentencia.setString(1, "%%");
            }
            sentencia.setString(2, "%" + nombre + "%");
            sentencia.setString(3, "%" + tipoContenidoNeto + "%");
            sentencia.setString(4,"%" + contenidoNeto + "%");
            sentencia.setString(5,"%" + valorContenido + "%");
            sentencia.setString(6, "%" + empaqueGeneral + "%");
            sentencia.setString(7, "%" + empaque + "%");
            sentencia.setString(8, "%" + descripcion + "%");
            sentencia.setString(9, "%" + recomendaciones + "%");
            sentencia.setString(10, "%" + precioProveedor + "%");
            sentencia.setString(11, "%" + porcentajeIva + "%");
            sentencia.setString(12, "%" + precioSinIva + "%");
            sentencia.setString(13, "%" + precioVenta + "%");
            sentencia.setString(14, "%" + fechaVencimiento + "%");
            sentencia.setString(15, "%" + cantidad + "%");
            sentencia.setString(16, "%" + idCategoria + "%");
            if (estado!= null) {
                sentencia.setString(17, "%" + estado + "%");
            }else {
                sentencia.setString(17, "%%");
            }
            // Ejecuta la sentencia
            ResultSet resultado = sentencia.executeQuery();
            // Asigna los resultados a una lista de los mismos y recorre campo por campo según el registro
            while (resultado.next()) {
                p.setIdProducto(resultado.getInt("id_producto"));
                p.setNombre(resultado.getString("nombre"));
                p.setTipoContenidoNeto(resultado.getString("tipo_contenido_neto"));
                p.setContenidoNeto(resultado.getInt("contenido_neto"));
                p.setValorContenido(resultado.getInt("valor_contenido"));
                p.setEmpaqueGeneral(resultado.getString("empaque_general"));
                p.setEmpaque(resultado.getString("empaque"));
                p.setDescripcion(resultado.getString("descripcion"));
                p.setRecomendaciones(resultado.getString("recomendaciones"));
                p.setPrecioProveedor(resultado.getInt("precio_proveedor"));
                p.setPorcentajeIva(resultado.getInt("porcentaje_iva"));
                p.setPrecioSinIva(resultado.getInt("precio_sin_iva"));
                p.setPrecioVenta(resultado.getInt("precio_venta"));
                p.setFechaVencimiento(resultado.getDate("fecha_vencimiento"));
                p.setCantidad(resultado.getInt("cantidad"));
                p.setEstado(resultado.getBoolean("estado"));
                //Buscar la categoria de ese producto
                Categoria c = new Categoria(resultado.getInt("id_categoria"),"","",true);
                Categoria categoria = c.buscarCategorias(c,conexion).get(0);
                //Asignar la categoria
                p.setCategoria(categoria);
                // Agrega un registro - producto
                listaProductos.add(p);
                p = new Producto();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista con los productos
        return listaProductos;
    }
    /*
     * METODO CREAR
     */
    public String agregarProducto(Producto producto, Connection conexion) {
        String mensajeError = validarCamposProducto(producto, conexion);
        if (mensajeError.equals("")) { // Verifica los campos - no sean null; Si no retorna los errores
            try {
                // Sentencia para insertar
                PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO PRODUCTO (nombre, tipo_contenido_neto, contenido_neto," +
                        " valor_contenido, empaque_general, empaque, descripcion, recomendaciones, precio_proveedor, porcentaje_iva, " +
                        "precio_sin_iva, precio_venta, fecha_vencimiento, cantidad, id_categoria, id_producto) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
                sentencia.setString(1, producto.getNombre());
                sentencia.setString(2, producto.getTipoContenidoNeto().toString());
                sentencia.setInt(3, producto.getContenidoNeto());
                sentencia.setInt(4, producto.getValorContenido());
                sentencia.setString(5, producto.getEmpaqueGeneral());
                sentencia.setString(6, producto.getEmpaque());
                sentencia.setString(7, producto.getDescripcion());
                sentencia.setString(8, producto.getRecomendaciones());
                sentencia.setInt(9, producto.getPrecioProveedor());
                sentencia.setInt(10, producto.getPorcentajeIva());
                sentencia.setInt(11, producto.getPrecioSinIva());
                sentencia.setInt(12, producto.getPrecioVenta());
                sentencia.setDate(13, producto.getFechaVencimiento());
                sentencia.setInt(14, producto.getCantidad());
                sentencia.setInt(15, producto.getCategoria().getIdCategoria());
                sentencia.setInt(16, producto.getIdProducto());
                // Ejecuta la sentencia
                sentencia.executeUpdate();
                // Cierra la conexión - sentencia
                sentencia.close();
                conexion.close();
                return "Producto creado.";
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } else {
            return mensajeError;
        }
        return "Producto no creado, campos inválidos";
    }

    /*
     * METODO ACTUALIZAR
     */

    public String actualizarProducto(Producto producto, Connection conexion) {
        String mensajeError = this.validarCamposProducto(producto,conexion);
        producto.setFechaModificacion(Timestamp.valueOf(LocalDateTime.now()));
        if (mensajeError.equals("")) {//Verifica los campos - no sean null; Si no retorna los erroes
            try {
                //Sentencia para actualizar
                PreparedStatement sentencia = conexion.prepareStatement("UPDATE PRODUCTO SET nombre = ?, tipo_contenido_neto = ?, " +
                        "contenido_neto = ?, valor_contenido = ?, empaque_general = ?, empaque = ?, descripcion = ?, " +
                        "recomendaciones = ?, precio_proveedor = ?, porcentaje_iva = ?, precio_sin_iva = ?, precio_venta = ?, " +
                        "fecha_vencimiento = ?, cantidad = ?, id_categoria = ?, fecha_modificacion = ?, estado = ? WHERE id_producto = ?");
                sentencia.setString(1, producto.getNombre());
                sentencia.setString(2, producto.getTipoContenidoNeto().toString());
                sentencia.setInt(3, producto.getContenidoNeto());
                sentencia.setInt(4, producto.getValorContenido());
                sentencia.setString(5, producto.getEmpaqueGeneral());
                sentencia.setString(6, producto.getEmpaque());
                sentencia.setString(7, producto.getDescripcion());
                sentencia.setString(8, producto.getRecomendaciones());
                sentencia.setInt(9, producto.getPrecioProveedor());
                sentencia.setInt(10, producto.getPorcentajeIva());
                sentencia.setInt(11, producto.getPrecioSinIva());
                sentencia.setInt(12, producto.getPrecioVenta());
                sentencia.setDate(13, producto.getFechaVencimiento());
                sentencia.setInt(14, producto.getCantidad());
                sentencia.setInt(15, producto.getCategoria().getIdCategoria());
                sentencia.setTimestamp(16, producto.getFechaModificacion());
                sentencia.setBoolean(17, producto.getEstado());
                sentencia.setInt(18, producto.getIdProducto());
                //Ejecuta la sentencia
                sentencia.executeUpdate();
                //Cierra la conexión - sentencia
                sentencia.close();
                conexion.close();
                return "Producto actualizado.";
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } else {
            return mensajeError;
        }
        return "Producto no actualizado, campos inválidos";
    }
    /*
        METODO VALIDAR CAMPOS DEL PRODUCTO
     */
    public String validarCamposProducto(Producto p, Connection conexion) {
        String mensajeError = "";
        //Buscar la categoria de ese producto
        Categoria c = new Categoria(p.getCategoria().getIdCategoria(),"","", true);
        List<Categoria> categoria = c.buscarCategorias(c,conexion);
        if ((p.getIdProducto()+"").length() < 1 || (p.getIdProducto()+"").length() > 10) {
            mensajeError += "El campo de ID del producto debe tener entre 1 y 10 caracteres.\n";
        }
        if (p.getNombre().length() < 3) {
            mensajeError += "El campo de nombre del producto debe tener al menos 3 caracteres.\n";
        }
        try {
            Producto.TipoContenidoNeto tipo = Producto.TipoContenidoNeto.valueOf(p.getTipoContenidoNeto());
            // El valor ingresado es uno de los valores permitidos en el campo ENUM
        } catch (IllegalArgumentException e) {
            mensajeError += "El campo tipo de contenido neto ingresado no es uno de los valores permitidos.\n";
        }
        if (p.getContenidoNeto() <= 0 ) {
            mensajeError += "El campo de contenido neto del producto debe ser mayor a 0.\n";
        }
        if (p.getEmpaqueGeneral().length() < 3) {
            mensajeError += "El campo de empaque general del producto debe tener al menos 3 caracteres.\n";
        }
        if (p.getEmpaque().length() < 3) {
            mensajeError += "El campo de empaque del producto debe tener al menos 3 caracteres.\n";
        }
        if (p.getDescripcion().length() < 3) {
            mensajeError += "El campo de descripción del producto debe tener al menos 3 caracteres.\n";
        }
        if (p.getRecomendaciones().length() < 3) {
            mensajeError += "El campo de recomendaciones del producto debe tener al menos 3 caracteres.\n";
        }
        if (p.getPrecioProveedor() <= 0) {
            mensajeError += "El campo de precio de proveedor del producto debe ser mayor a 0.\n";
        }
        if (p.getPorcentajeIva() > 100 || p.getPorcentajeIva() < 0) {
            mensajeError += "El campo de porcentaje de IVA del producto debe estar entre 0 y 100.\n";
        }
        if (p.getPrecioSinIva() <= 0) {
            mensajeError += "El campo de precio sin IVA del producto debe ser mayor a 0.\n";
        }
        if (p.getFechaVencimiento() == null) {
            mensajeError += "El campo de fecha de vencimiento del producto es obligatorio.\n";
        }
        if (p.getCantidad() <= 0) {
            mensajeError += "El campo de cantidad del producto debe ser mayor a 0.\n";
        }
        if (categoria==null) {
            mensajeError += "El campo de ID de categoría no existe seleccione uno valido.\n";
        }
        // Concatena y devuelve los errores
        return mensajeError;
    }
    /*
     * METODOS GETTERS AND SETTERS
     *
     */

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoContenidoNeto() {
        return tipoContenidoNeto;
    }

    public void setTipoContenidoNeto(String tipoContenidoNeto) {
        this.tipoContenidoNeto = tipoContenidoNeto;
    }

    public Integer getContenidoNeto() {
        return contenidoNeto;
    }

    public void setContenidoNeto(Integer contenidoNeto) {
        this.contenidoNeto = contenidoNeto;
    }

    public Integer getValorContenido() {
        return valorContenido;
    }

    public void setValorContenido(Integer valorContenido) {
        this.valorContenido = valorContenido;
    }

    public String getEmpaqueGeneral() {
        return empaqueGeneral;
    }

    public void setEmpaqueGeneral(String empaqueGeneral) {
        this.empaqueGeneral = empaqueGeneral;
    }

    public String getEmpaque() {
        return empaque;
    }

    public void setEmpaque(String empaque) {
        this.empaque = empaque;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public Integer getPrecioProveedor() {
        return precioProveedor;
    }

    public void setPrecioProveedor(Integer precioProveedor) {
        this.precioProveedor = precioProveedor;
    }

    public Integer getPorcentajeIva() {
        return porcentajeIva;
    }

    public void setPorcentajeIva(Integer porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

    public Integer getPrecioSinIva() {
        return precioSinIva;
    }

    public void setPrecioSinIva(Integer precioSinIva) {
        this.precioSinIva = precioSinIva;
    }

    public Integer getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Integer precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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
