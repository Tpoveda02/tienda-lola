package controlador;

import modelo.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class FacturaClienteControlador {

    //Declaración de variables
    Controlador controlador;

    public FacturaClienteControlador() {
        controlador = new Controlador();
    }

    //----CRUD FACRUTAS----
    //Metodo obtener factura(s) - recibe los valores de los JText
    public List<FacturaCliente> listarFacturasCliente() {
        FacturaCliente facturaCliente = new FacturaCliente();
        //Llama el metodo de listar facturas
        return facturaCliente.listarFacturasCliente(controlador.getConexion());
    }

    //Metodo obtener factura(s) - recibe los valores de los JText
    public List<FacturaCliente> buscarFacturasCliente(Integer idFacturaCliente, String direccion, String telefono, String correoElectronico, String fechaFactura, String total, String cantidadProducto, String idCliente) {
        //Instacia la factura con los respectivos valores
        FacturaCliente facturaCliente = new FacturaCliente();
        //Llama el metodo de buscar facturasCliente
        return facturaCliente.buscarFacturaCliente(idFacturaCliente, direccion, telefono, correoElectronico, fechaFactura, total, cantidadProducto, idCliente, controlador.getConexion());
    }

    //Metodo agregar factura cliente - recibe los valores de los JText
    public String crearFacturaCliente(int idFacturaCliente, String direccion, String telefono, String correoElectronico, Timestamp fechaFactura, BigDecimal total, Cliente cliente, List<DetalleFacturaCliente> detalleProductosFacturaCliente) {
        //Instacia la factura con los respectivos valores
        FacturaCliente facturaCliente = new FacturaCliente(idFacturaCliente,direccion,telefono,correoElectronico,fechaFactura,total,cliente,detalleProductosFacturaCliente);
        //Llama el metodo de buscar facturasCliente
        return facturaCliente.insertarFacturaCliente(facturaCliente, controlador.getConexion());
    }

    //Metodo eliminar factura cliente
    public String eliminarFacturaCliente(int id) {
        //Instacia la facturaCliente con el ID para eliminarlo
        FacturaCliente facturaCliente = new FacturaCliente();
        //Llama el metodo de eliminar facturaCliente
        return facturaCliente.eliminarFacturaCliente(id, controlador.getConexion());
    }
}
