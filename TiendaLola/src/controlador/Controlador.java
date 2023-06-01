package controlador;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import modelo.*;
import vista.Login;

public class Controlador {

    //Declaración de variables
    static Connection conect;

    // Conexión a la base de datos
    public Connection getConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/TIENDA_LOLA?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String usuario = "root";
            String contraseña = "root";
            conect = DriverManager.getConnection(url, usuario, contraseña);
            if(conect!=null) {
                System.out.println("Conexión establecida con Mysql");
            }
        }catch(ClassNotFoundException e) {
            System.out.println("Error al cargar el Driver");
            e.printStackTrace();
        }catch(SQLException e) {
            System.out.println("Error con la BD");
            e.printStackTrace();
        }

        return conect;
    }
    //Login - acceso al sistema
    public Boolean login(String usuario, String contrasenia) {
        Usuario us = new Usuario(1,usuario,contrasenia);
        //Llama el metodo que valida el acceso
        return us.login(us,getConexion());
    }


    public static void main(String args[]) {
        //PROBAR PROVEEDOR
        /*
        ProveedorControlador init = new ProveedorControlador();
        System.out.println(init.crearProveedor(1000034812, "PASAPORTE", "Juan", "Carlos", "García", "Pérez", "Calle Mayor 1", "1234567", "juan.carlos@gmail.com"));
        System.out.println(init.modificarProveedor(1000034812, "CC", "Juan", "Carlos", "García", "Pérez", "Calle Mayor 1", "1234567", "juan.carlos@gmail.com"));
        System.out.println(init.eliminarProveedor(1000034812));
        System.out.println(init.buscarProveedors(1000034812, "CC", "Juan", "Carlos", "García", "Pérez", "Calle Mayor 1", "1234567", "juan.carlos@gmail.com"));
         */
        //PROBAR CATEGORIA
/*
        CategoriaControlador init = new CategoriaControlador();
        System.out.println(init.crearCategoria(0, "Bebidas", "Estante A - Refrigeradores"));

        System.out.println(init.buscarCategorias(null, "Bebidas",""));

        System.out.println(init.buscarCategorias(null, "Bebidas","").get(0).getIdCategoria());


 */

/*
        ProductoControlador init = new ProductoControlador();
        Categoria c = new Categoria(1,"","");
        System.out.println(init.crearProducto( 1, "Café colombiano", "gr", new Integer("250"), new Integer("10000"),
                "Bolsa", "Aluminio", "Café tostado y molido 100% arábica", "Mantener en lugar fresco y seco",
                new Integer("8000"), new Integer("19"), new Integer("8403.36"), new Integer("10000"),
                new Date(2024, 12, 31), 100, c));
        System.out.println(init.modificarProducto(1, "Café colombiano", "gr", new Integer("250"), new Integer("10000"),
                "Bolsa", "Aluminio", "Café tostado y molido 100% arábica", "Mantener en lugar fresco y seco",
                new Integer("8000"), new Integer("19"), new Integer("8403.36"), new Integer("10000"),
                new Date(2024, 12, 31), 100, c));
        System.out.println(init.eliminarProducto(1));

 */

        Controlador init = new Controlador();
        Login login = new Login();
        login.setVisible(true);

    }
}
