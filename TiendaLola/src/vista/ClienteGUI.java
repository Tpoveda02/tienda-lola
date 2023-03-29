package vista;

import controlador.Controlador;
import modelo.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ClienteGUI extends JFrame{
    private JLabel lblIdCliente, lblTipoIdentificacion, lblPrimerNombre, lblSegundoNombre, lblPrimerApellido,
            lblSegundoApellido, lblDireccion, lblTelefono, lblCorreoElectronico;
    private JTextField txtIdCliente, txtTipoIdentificacion, txtPrimerNombre, txtSegundoNombre, txtPrimerApellido,
            txtSegundoApellido, txtDireccion, txtTelefono, txtCorreoElectronico;
    private JButton btnAgregar, btnActualizar, btnEliminar, btnLimpiar, btnBuscar;
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;
    private List<Cliente> listaClientes;

    Controlador controlador;
    private int filaSeleccionada = -1;

    public ClienteGUI() {
        controlador = new Controlador();
        inicializarComponentes();
        listaClientes = new ArrayList<Cliente>();
    }


    private void inicializarComponentes() {
        this.setTitle("Gestión de Clientes");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLayout(null);

        // Labels
        lblIdCliente = new JLabel("ID Cliente:");
        lblIdCliente.setBounds(20, 20, 80, 20);
        this.add(lblIdCliente);

        lblTipoIdentificacion = new JLabel("Tipo Identificación:");
        lblTipoIdentificacion.setBounds(20, 50, 120, 20);
        this.add(lblTipoIdentificacion);

        lblPrimerNombre = new JLabel("Primer Nombre:");
        lblPrimerNombre.setBounds(20, 80, 120, 20);
        this.add(lblPrimerNombre);

        lblSegundoNombre = new JLabel("Segundo Nombre:");
        lblSegundoNombre.setBounds(20, 110, 120, 20);
        this.add(lblSegundoNombre);

        lblPrimerApellido = new JLabel("Primer Apellido:");
        lblPrimerApellido.setBounds(20, 140, 120, 20);
        this.add(lblPrimerApellido);

        lblSegundoApellido = new JLabel("Segundo Apellido:");
        lblSegundoApellido.setBounds(20, 170, 120, 20);
        this.add(lblSegundoApellido);

        lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(20, 200, 80, 20);
        this.add(lblDireccion);

        lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(20, 230, 80, 20);
        this.add(lblTelefono);

        lblCorreoElectronico = new JLabel("Correo Electrónico:");
        lblCorreoElectronico.setBounds(20, 260, 120, 20);
        this.add(lblCorreoElectronico);

        // TextFields
        txtIdCliente = new JTextField();
        txtIdCliente.setBounds(150, 20, 150, 20);
        this.add(txtIdCliente);

        txtTipoIdentificacion = new JTextField();
        txtTipoIdentificacion.setBounds(150, 50, 150, 20);
        this.add(txtTipoIdentificacion);

        txtPrimerNombre = new JTextField();
        txtPrimerNombre.setBounds(150, 80, 150, 20);
        this.add(txtPrimerNombre);

        txtSegundoNombre = new JTextField();
        txtSegundoNombre.setBounds(150, 110, 150, 20);
        this.add(txtSegundoNombre);
        txtPrimerApellido = new JTextField();
        txtPrimerApellido.setBounds(150, 140, 150, 20);
        this.add(txtPrimerApellido);

        txtSegundoApellido = new JTextField();
        txtSegundoApellido.setBounds(150, 170, 150, 20);
        this.add(txtSegundoApellido);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(150, 200, 150, 20);
        this.add(txtDireccion);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(150, 230, 150, 20);
        this.add(txtTelefono);

        txtCorreoElectronico = new JTextField();
        txtCorreoElectronico.setBounds(150, 260, 150, 20);
        this.add(txtCorreoElectronico);


        // Tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID Cliente");
        modeloTabla.addColumn("Tipo Identificación");
        modeloTabla.addColumn("Primer Nombre");
        modeloTabla.addColumn("Segundo Nombre");
        modeloTabla.addColumn("Primer Apellido");
        modeloTabla.addColumn("Segundo Apellido");
        modeloTabla.addColumn("Dirección");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("Correo Electrónico");

        tablaClientes = new JTable(actualizarTabla(modeloTabla));
        JScrollPane scrollPane = new JScrollPane(tablaClientes);
        scrollPane.setBounds(20, 340, 750, 200);

        this.add(scrollPane);


        // Botones
        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(20, 300, 100, 30);
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        this.add(btnAgregar);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(130, 300, 100, 30);
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnActualizarActionPerformed(evt);
                actualizarTabla(modeloTabla);
            }
        });
        this.add(btnActualizar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(240, 300, 100, 30);
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnEliminarActionPerformed(evt);
                actualizarTabla(modeloTabla);
            }
        });
        this.add(btnEliminar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(350, 300, 100, 30);
        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
                actualizarTabla(modeloTabla);
            }
        });
        this.add(btnLimpiar);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(460, 300, 100, 30);
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnBuscarActionPerformed(evt);
                actualizarTabla(modeloTabla);
            }
        });
        this.add(btnBuscar);

    }

    private void btnAgregarActionPerformed(ActionEvent evt) {
        try {
            int idCliente = Integer.parseInt(txtIdCliente.getText());
            String tipoIdentificacion = txtTipoIdentificacion.getText();
            String primerNombre = txtPrimerNombre.getText();
            String segundoNombre = txtSegundoNombre.getText();
            String primerApellido = txtPrimerApellido.getText();
            String segundoApellido = txtSegundoApellido.getText();
            String direccion = txtDireccion.getText();
            String telefono = txtTelefono.getText();
            String correoElectronico = txtCorreoElectronico.getText();
            // Agregar el cliente a la base de datos
            JOptionPane.showMessageDialog(this,controlador.crearCliente(idCliente, tipoIdentificacion, primerNombre, segundoNombre,
                    primerApellido, segundoApellido, direccion, telefono, correoElectronico));

            // Actualizar la tabla
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID Cliente debe ser un número entero.");
        }
    }

    private void btnActualizarActionPerformed(ActionEvent evt) {
        try {
            int idCliente = Integer.parseInt(txtIdCliente.getText());
            String tipoIdentificacion = txtTipoIdentificacion.getText();
            String primerNombre = txtPrimerNombre.getText();
            String segundoNombre = txtSegundoNombre.getText();
            String primerApellido = txtPrimerApellido.getText();
            String segundoApellido = txtSegundoApellido.getText();
            String direccion = txtDireccion.getText();
            String telefono = txtTelefono.getText();
            String correoElectronico = txtCorreoElectronico.getText();

            // Actualizar el cliente en la base de datos
            JOptionPane.showMessageDialog(this,controlador.modificarCliente(idCliente, tipoIdentificacion, primerNombre, segundoNombre,
                    primerApellido, segundoApellido, direccion, telefono, correoElectronico));

            // Actualizar la tabla
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID Cliente debe ser un número entero.");
        }
    }

    private void btnEliminarActionPerformed(ActionEvent evt) {
        try {
            int idCliente = Integer.parseInt(txtIdCliente.getText());

            // Eliminar el cliente de la base de datos
            JOptionPane.showMessageDialog(this,controlador.eliminarCliente(idCliente));

            // Actualizar la tabla
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID Cliente debe ser un número entero.");
        }
    }

    private void btnLimpiarActionPerformed(ActionEvent evt) {
        txtIdCliente.setText("");
        txtTipoIdentificacion.setText("");
        txtPrimerNombre.setText("");
        txtSegundoNombre.setText("");
        txtPrimerApellido.setText("");
        txtSegundoApellido.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCorreoElectronico.setText("");
    }

    private void btnBuscarActionPerformed(ActionEvent evt) {
        try {
            int idCliente = Integer.parseInt(txtIdCliente.getText());

            // Buscar el cliente en la base de datos
             List<Cliente> clientes = controlador.buscarClientes(idCliente,"","","","","","","","");

            if (clientes != null) {
                txtTipoIdentificacion.setText(clientes.get(0).getTipoIdentificacion());
                txtPrimerNombre.setText(clientes.get(0).getPrimerNombre());
                txtSegundoNombre.setText(clientes.get(0).getSegundoNombre());
                txtPrimerApellido.setText(clientes.get(0).getPrimerApellido());
                txtSegundoApellido.setText(clientes.get(0).getSegundoApellido());
                txtDireccion.setText(clientes.get(0).getDireccion());
                txtTelefono.setText(clientes.get(0).getTelefono());
                txtCorreoElectronico.setText(clientes.get(0).getCorreoElectronico());
            } else {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID Cliente debe ser un número entero.");
        }
    }

    private DefaultTableModel actualizarTabla(DefaultTableModel modeloTabla) {
        // Limpiar la tabla
        modeloTabla.setRowCount(0);
        // Obtener la lista de clientes desde la base de datos
        List<Cliente> listaClientes = controlador.listarClientes();
        // Agregar cada cliente a la tabla
        for (Cliente cliente : listaClientes) {
            Object[] fila = new Object[9];
            fila[0] = cliente.getIdCliente();
            fila[1] = cliente.getTipoIdentificacion();
            fila[2] = cliente.getPrimerNombre();
            fila[3] = cliente.getSegundoNombre();
            fila[4] = cliente.getPrimerApellido();
            fila[5] = cliente.getSegundoApellido();
            fila[6] = cliente.getDireccion();
            fila[7] = cliente.getTelefono();
            fila[8] = cliente.getCorreoElectronico();
            modeloTabla.addRow(fila);
        }


        return modeloTabla;
    }
}
