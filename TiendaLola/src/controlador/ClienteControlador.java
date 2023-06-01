package controlador;

import modelo.Cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ClienteControlador {
    //Declaración de variables
   Controlador controlador;

   public ClienteControlador(){
       controlador = new Controlador();
   }

    //----CRUD CLIENTE----
    //Metodo obtener cliente(s) - recibe los valores de los JText
    public List<Cliente> listarClientes() {
        Cliente cliente = new Cliente();
        //Llama el metodo de listar clientes
        return cliente.listarClientes(controlador.getConexion());
    }

    //Metodo obtener cliente(s) - recibe los valores de los JText
    public List<Cliente> buscarClientes(Integer idCliente, String tipoIdentificacion, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String direccion, String telefono, String correoElectronico){
        //Instacia el cliente con los respectivos valores
        Cliente cliente = new Cliente(idCliente, tipoIdentificacion, primerNombre, segundoNombre, primerApellido, segundoApellido, direccion, telefono, correoElectronico,null);
        //Llama el metodo de buscar clientes
        return cliente.buscarClientes(cliente,controlador.getConexion());
    }
    //Metodo agregar cliente - recibe los valores de los JText
    public String crearCliente(int idCliente, String tipoIdentificacion, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String direccion, String telefono, String correoElectronico){
        //Instacia el cliente con los respectivos valores
        Cliente cliente = new Cliente(idCliente, tipoIdentificacion, primerNombre, segundoNombre, primerApellido, segundoApellido, direccion, telefono, correoElectronico,null);
        //Llama el metodo de crear cliente
        return cliente.agregarCliente(cliente,controlador.getConexion());
    }
    //Metodo actualizar cliente - recibe los valores de los JText
    public String modificarCliente(int idCliente, String tipoIdentificacion, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String direccion, String telefono, String correoElectronico){
        //Instacia el cliente con los respectivos valores
        Cliente cliente = new Cliente(idCliente, tipoIdentificacion, primerNombre, segundoNombre, primerApellido, segundoApellido, direccion, telefono, correoElectronico,null);
        //Llama el metodo de actualizar cliente
        return cliente.actualizarCliente(cliente,controlador.getConexion());
    }
    //Metodo eliminar cliente
    public String eliminarCliente(int id){
        //Instacia el cliente con el ID para eliminarlo
        Cliente cliente = new Cliente();
        cliente.setIdCliente(id);
        //Llama el metodo de eliminar cliente
        return cliente.eliminarCliente(cliente,controlador.getConexion());
    }
}
