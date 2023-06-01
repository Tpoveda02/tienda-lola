package controlador;

import modelo.Proveedor;

import java.util.List;

public class ProveedorControlador {
    //Declaración de variables
   Controlador controlador;

   public ProveedorControlador(){
       controlador = new Controlador();
   }

    //----CRUD PROVEEDOR----
    //Metodo obtener proveedor(s) - recibe los valores de los JText
    public List<Proveedor> listarProveedors() {
        Proveedor proveedor = new Proveedor();
        //Llama el metodo de listar proveedors
        return proveedor.listarProveedores(controlador.getConexion());
    }

    //Metodo obtener proveedor(s) - recibe los valores de los JText
    public List<Proveedor> buscarProveedors(Integer idProveedor, String tipoIdentificacion, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String direccion, String telefono, String correoElectronico, Boolean estado){
        //Instacia el proveedor con los respectivos valores
        Proveedor proveedor = new Proveedor(idProveedor, tipoIdentificacion, primerNombre, segundoNombre, primerApellido, segundoApellido, direccion, telefono, correoElectronico,estado);
        //Llama el metodo de buscar proveedors
        return proveedor.buscarProveedors(proveedor,controlador.getConexion());
    }
    //Metodo agregar proveedor - recibe los valores de los JText
    public String crearProveedor(int idProveedor, String tipoIdentificacion, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String direccion, String telefono, String correoElectronico, Boolean estado){
        //Instacia el proveedor con los respectivos valores
        Proveedor proveedor = new Proveedor(idProveedor, tipoIdentificacion, primerNombre, segundoNombre, primerApellido, segundoApellido, direccion, telefono, correoElectronico,estado);
        //Llama el metodo de crear proveedor
        return proveedor.agregarProveedor(proveedor,controlador.getConexion());
    }
    //Metodo actualizar proveedor - recibe los valores de los JText
    public String modificarProveedor(int idProveedor, String tipoIdentificacion, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String direccion, String telefono, String correoElectronico, Boolean estado){
        //Instacia el proveedor con los respectivos valores
        Proveedor proveedor = new Proveedor(idProveedor, tipoIdentificacion, primerNombre, segundoNombre, primerApellido, segundoApellido, direccion, telefono, correoElectronico,estado);
        //Llama el metodo de actualizar proveedor
        return proveedor.actualizarProveedor(proveedor,controlador.getConexion());
    }

}
