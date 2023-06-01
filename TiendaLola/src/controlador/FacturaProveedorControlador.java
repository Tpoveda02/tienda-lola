package controlador;

import modelo.Proveedor;
import modelo.DetalleFacturaProveedor;
import modelo.FacturaProveedor;


import java.sql.Timestamp;
import java.util.List;

public class FacturaProveedorControlador {

    //Declaración de variables
    Controlador controlador;

    public FacturaProveedorControlador() {
        controlador = new Controlador();
    }

    //----CRUD FACRUTAS----
    //Metodo obtener factura(s) - recibe los valores de los JText
    public List<FacturaProveedor> listarFacturasProveedor() {
        FacturaProveedor facturaProveedor = new FacturaProveedor();
        //Llama el metodo de listar facturas
        return facturaProveedor.listarFacturasProveedor(controlador.getConexion());
    }

    //Metodo obtener factura(s) - recibe los valores de los JText
    public List<FacturaProveedor> buscarFacturasProveedor(Integer idFacturaProveedor, String direccion, String telefono, String correoElectronico, String fechaFactura, String total, String cantidadProducto, String idProveedor) {
        //Instacia la factura con los respectivos valores
        FacturaProveedor facturaProveedor = new FacturaProveedor();
        //Llama el metodo de buscar facturasProveedor
        return facturaProveedor.buscarFacturaProveedor(idFacturaProveedor, direccion, telefono, correoElectronico, fechaFactura, total, cantidadProducto, idProveedor, controlador.getConexion());
    }

    //Metodo agregar factura proveedor - recibe los valores de los JText
    public String crearFacturaProveedor(int idFacturaProveedor, String direccion, String telefono, String correoElectronico, Timestamp fechaFactura, Integer total, Proveedor proveedor, List<DetalleFacturaProveedor> detalleProductosFacturaProveedor) {
        //Instacia la factura con los respectivos valores
        FacturaProveedor facturaProveedor = new FacturaProveedor(idFacturaProveedor,direccion,telefono,correoElectronico,fechaFactura,total,proveedor,detalleProductosFacturaProveedor);
        //Llama el metodo de buscar facturasProveedor
        return facturaProveedor.insertarFacturaProveedor(facturaProveedor, controlador.getConexion());
    }

}
