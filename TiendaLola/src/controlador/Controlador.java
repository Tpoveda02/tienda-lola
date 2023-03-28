package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import modelo.*;
import vista.Login;

public class Controlador {
    static Connection conect;

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
        return us.login(us,getConexion());
    }
    //----CRUD CLIENTE----
    //Metodo obtener cliente(s) - recibe los valores de los JText
    public List<Cliente> buscarClientes(int idCliente, String tipoIdentificacion, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String direccion, String telefono, String correoElectronico){
        //Instacia el cliente con los respectivos valores
        Cliente cliente = new Cliente(idCliente, tipoIdentificacion, primerNombre, segundoNombre, primerApellido, segundoApellido, direccion, telefono, correoElectronico);
        //Llama el metodo de buscar clientes
        return cliente.buscarClientes(cliente,getConexion());
    }
    //Metodo agregar cliente - recibe los valores de los JText
    public String crearCliente(int idCliente, String tipoIdentificacion, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String direccion, String telefono, String correoElectronico){
        //Instacia el cliente con los respectivos valores
        Cliente cliente = new Cliente(idCliente, tipoIdentificacion, primerNombre, segundoNombre, primerApellido, segundoApellido, direccion, telefono, correoElectronico);
        //Llama el metodo de crear cliente
        return cliente.agregarCliente(cliente,getConexion());
    }
    //Metodo actualizar cliente - recibe los valores de los JText
    public String modificarCliente(int idCliente, String tipoIdentificacion, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String direccion, String telefono, String correoElectronico){
        //Instacia el cliente con los respectivos valores
        Cliente cliente = new Cliente(idCliente, tipoIdentificacion, primerNombre, segundoNombre, primerApellido, segundoApellido, direccion, telefono, correoElectronico);
        //Llama el metodo de actualizar cliente
        return cliente.actualizarCliente(cliente,getConexion());
    }
    //Metodo eliminar cliente
    public String eliminarCliente(int id){
        //Instacia el cliente con el ID para eliminarlo
        Cliente cliente = new Cliente();
        cliente.setIdCliente(id);
        //Llama el metodo de eliminar cliente
        return cliente.eliminarCliente(cliente,getConexion());
    }

    public static void main(String args[]) {
        Controlador init = new Controlador();
        System.out.println(init.crearCliente(2, "DNI", "Juan", "Carlos", "García", "Pérez", "Calle Mayor 1", "1234567", "juan.carlos@gmail.com"));
        System.out.println(init.modificarCliente(2, "CC", "Juan", "Carlos", "García", "Pérez", "Calle Mayor 1", "1234567", "juan.carlos@gmail.com"));
        System.out.println(init.eliminarCliente(2));
        System.out.println(init.buscarClientes(2, "CC", "Juan", "Carlos", "García", "Pérez", "Calle Mayor 1", "1234567", "juan.carlos@gmail.com"));
        //Login view = new Login();
        //view.setVisible(true);
    }
}
