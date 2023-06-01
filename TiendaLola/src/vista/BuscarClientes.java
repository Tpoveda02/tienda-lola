package vista;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controlador.ClienteControlador;
import controlador.Controlador;
import modelo.Cliente;
import modelo.Proveedor;

import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;

public class BuscarClientes extends JPanel {

    //Objeto de la clase Controlador
    private ClienteControlador clienteControlador;
    //Objeto de la clase PanelAgregar
    private AgregarCliente panelAgregar;
    //Objeto de la clase panelActualizar
    private ActualizarCliente panelActualizar;

    //--------------------Variables-------------------
    private List<Cliente> listaClientes;
    private JLabel lblIdCliente;
    private JLabel lblTipoIdentificacion;
    private JLabel lblPrimerNombre;
    private JLabel lblSegundoNombre;
    private JLabel lblPrimerApellido;
    private JLabel lblSegundoApellido;
    private JLabel lblDireccion;
    private JLabel lblTelefono;
    private JLabel lblCorreoElectronico;
    private JTextField txtIdCliente;
    private JComboBox txtTipoIdentificacion;
    private JTextField txtPrimerNombre;
    private JTextField txtSegundoNombre;
    private JTextField txtPrimerApellido;
    private JTextField txtSegundoApellido;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JTextField txtCorreoElectronico;
    private JTable tablaClientes;
    private JScrollPane scrollPane;
    private DefaultTableModel modeloTabla;
    private JButton btnAgregar;
    private JButton btnActualizar;
    private JButton btnInactivar;
    private JButton btnActivar;
    private JButton btnLimpiar;
    private JButton btnBuscar;

    public static int idCliente = 0;        //Validar que no ha seleccionado ningun cliente
    public static String tipoIdentificacion;
    public static String primerNombre;
    public static String segundoNombre;
    public static String primerApellido;
    public static String segundoApellido;
    public static String direccion;
    public static String telefono;
    public static String correoElectronico;
    public static Boolean estado;


    /*
     * METODO CONSTRUCTOR
     */
    public BuscarClientes() {
        clienteControlador = new ClienteControlador();
        inicializarComponentes();
        listaClientes = new ArrayList<Cliente>();
    }

    /*
     * METODO PARA INICIALIZAR Y DAR ESTILO A TODOS LOS COMPONETES VISUALES DE LA VISTA DE LA CLASE BuscarClientes
     */
    private void inicializarComponentes() {
        setLayout(null);
        JPanel panelBuscar = new JPanel();
        panelBuscar.setBounds(0, 0, 960, 440);
        add(panelBuscar);
        panelBuscar.setLayout(null);

        // Labels
        lblIdCliente = new JLabel("ID Cliente:");
        lblIdCliente.setBounds(20, 0, 80, 20);
        lblIdCliente.setForeground(new Color(135, 134, 133));
        panelBuscar.add(lblIdCliente);

        lblTipoIdentificacion = new JLabel("Tipo Identificación:");
        lblTipoIdentificacion.setBounds(180, 0, 120, 20);
        lblTipoIdentificacion.setForeground(new Color(135, 134, 133));
        panelBuscar.add(lblTipoIdentificacion);

        lblPrimerNombre = new JLabel("Primer Nombre:");
        lblPrimerNombre.setBounds(340, 0, 120, 20);
        lblPrimerNombre.setForeground(new Color(135, 134, 133));
        panelBuscar.add(lblPrimerNombre);

        lblSegundoNombre = new JLabel("Segundo Nombre:");
        lblSegundoNombre.setBounds(500, 0, 120, 20);
        lblSegundoNombre.setForeground(new Color(135, 134, 133));
        panelBuscar.add(lblSegundoNombre);

        lblPrimerApellido = new JLabel("Primer Apellido:");
        lblPrimerApellido.setBounds(660, 0, 120, 20);
        lblPrimerApellido.setForeground(new Color(135, 134, 133));
        panelBuscar.add(lblPrimerApellido);

        lblSegundoApellido = new JLabel("Segundo Apellido:");
        lblSegundoApellido.setBounds(20, 49, 120, 20);
        lblSegundoApellido.setForeground(new Color(135, 134, 133));
        panelBuscar.add(lblSegundoApellido);

        lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(180, 49, 80, 20);
        lblDireccion.setForeground(new Color(135, 134, 133));
        panelBuscar.add(lblDireccion);

        lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(340, 49, 80, 20);
        lblTelefono.setForeground(new Color(135, 134, 133));
        panelBuscar.add(lblTelefono);

        lblCorreoElectronico = new JLabel("Correo Electrónico:");
        lblCorreoElectronico.setBounds(500, 49, 120, 20);
        lblCorreoElectronico.setForeground(new Color(135, 134, 133));
        panelBuscar.add(lblCorreoElectronico);


        // TextFields
        txtIdCliente = new JTextField();
        txtIdCliente.setBounds(20, 23, 150, 20);
        txtIdCliente.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
        txtIdCliente.setText("");
        panelBuscar.add(txtIdCliente);


        txtTipoIdentificacion = new JComboBox();
        txtTipoIdentificacion.setModel(new DefaultComboBoxModel(new String[]{"", "CC", "NIT", "DNI", "PASAPORTE"}));
        txtTipoIdentificacion.setBounds(180, 23, 150, 20);
        txtTipoIdentificacion.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
        panelBuscar.add(txtTipoIdentificacion);

        txtPrimerNombre = new JTextField();
        txtPrimerNombre.setBounds(340, 23, 150, 20);
        txtPrimerNombre.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
        txtPrimerNombre.setText("");
        panelBuscar.add(txtPrimerNombre);

        txtSegundoNombre = new JTextField();
        txtSegundoNombre.setBounds(500, 23, 150, 20);
        txtSegundoNombre.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
        txtSegundoNombre.setText("");
        panelBuscar.add(txtSegundoNombre);

        txtPrimerApellido = new JTextField();
        txtPrimerApellido.setBounds(660, 23, 150, 20);
        txtPrimerApellido.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
        txtPrimerApellido.setText("");
        panelBuscar.add(txtPrimerApellido);

        txtSegundoApellido = new JTextField();
        txtSegundoApellido.setBounds(21, 70, 150, 20);
        txtSegundoApellido.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
        txtSegundoApellido.setText("");
        panelBuscar.add(txtSegundoApellido);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(180, 70, 150, 20);
        txtDireccion.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
        txtDireccion.setText("");
        panelBuscar.add(txtDireccion);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(340, 70, 150, 20);
        txtTelefono.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
        txtTelefono.setText("");
        panelBuscar.add(txtTelefono);

        txtCorreoElectronico = new JTextField();
        txtCorreoElectronico.setBounds(500, 70, 310, 20);
        txtCorreoElectronico.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
        txtCorreoElectronico.setText("");
        panelBuscar.add(txtCorreoElectronico);


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
        modeloTabla.addColumn("Estado");

        tablaClientes = new JTable(actualizarTabla(modeloTabla, clienteControlador.listarClientes()));
        scrollPane = new JScrollPane(tablaClientes);
        scrollPane.setBorder(new LineBorder(Color.WHITE, 0, true));
        scrollPane.setBounds(10, 106, 940, 262);
        //Color de la tabla
        tablaClientes.getTableHeader().setBackground(new Color(182, 220, 255));
        tablaClientes.getTableHeader().setForeground(new Color(135, 134, 133));
        tablaClientes.setForeground(new Color(133, 111, 69));
        //Accion de quitar o poner lines en la tabla
        tablaClientes.setShowVerticalLines(false);
        tablaClientes.setShowHorizontalLines(true);
        //Dar color a las lineas de la tabla
        tablaClientes.setGridColor(new Color(182, 220, 255));
        //Instancia del objeto del borde de la tabla
        Border borde = BorderFactory.createLineBorder(new Color(182, 220, 255));
        //Asigna el borde instanciado a la tabla
        tablaClientes.setBorder(borde);
        tablaClientes.getTableHeader().setBorder(borde);
        //Otras asignaciones de estetica de la tabla
        tablaClientes.setRowHeight(30); // Establece la altura de las filas
        tablaClientes.setIntercellSpacing(new Dimension(0, 0)); // Establece el espaciado entre celdas

        panelBuscar.add(scrollPane);

        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_MouseClicked(evt);
            }
        });


        // Botones
        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(837, 399, 100, 30);
        btnAgregar.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
        btnAgregar.setBackground(new Color(192, 192, 192));
        btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnAgregar.setForeground(new Color(255, 255, 255));
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnAgregarActionPerformed(evt, panelBuscar);
            }
        });
        panelBuscar.add(btnAgregar);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(725, 399, 100, 30);
        btnActualizar.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
        btnActualizar.setBackground(new Color(192, 192, 192));
        btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnActualizar.setForeground(new Color(255, 255, 255));
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //ValidaridCliente que no ha seleccionado ningun cliente//Si no mensaje
                if (idCliente != 0) {
                    btnActualizarActionPerformed(evt, panelBuscar);
                    actualizarTabla(modeloTabla, clienteControlador.listarClientes());
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un Cliente");
                }

            }
        });
        panelBuscar.add(btnActualizar);


        btnInactivar = new JButton("Inactivar");
        btnInactivar.setBounds(615, 399, 100, 30);
        btnInactivar.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
        btnInactivar.setBackground(new Color(192, 192, 192));
        btnInactivar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnInactivar.setForeground(new Color(255, 255, 255));
        btnInactivar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (idCliente != 0) {
                    btnInactivarActionPerformed(evt);
                    actualizarTabla(modeloTabla, clienteControlador.listarClientes());
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un Cliente");
                }

            }
        });
        panelBuscar.add(btnInactivar);

        btnActivar = new JButton("Activar");
        btnActivar.setBounds(505, 399, 100, 30);
        btnActivar.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
        btnActivar.setBackground(new Color(192, 192, 192));
        btnActivar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnActivar.setForeground(new Color(255, 255, 255));
        btnActivar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (idCliente != 0) {
                    btnActivarActionPerformed(evt);
                    actualizarTabla(modeloTabla, clienteControlador.listarClientes());
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un Cliente");
                }

            }
        });
        panelBuscar.add(btnActivar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(837, 65, 100, 30);
        btnLimpiar.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
        btnLimpiar.setBackground(new Color(192, 192, 192));
        btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnLimpiar.setForeground(new Color(255, 255, 255));
        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
                actualizarTabla(modeloTabla, clienteControlador.listarClientes());
            }
        });
        panelBuscar.add(btnLimpiar);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(837, 18, 100, 30);
        btnBuscar.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
        btnBuscar.setBackground(new Color(192, 192, 192));
        btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnBuscar.setForeground(new Color(255, 255, 255));
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        panelBuscar.add(btnBuscar);


    }

    /*
     * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Agregar
     * Dentro de este metodo quitamos el panel BuscarClientes y ponemos el panel de la clase AgregarCliente
     */
    private void btnAgregarActionPerformed(ActionEvent evt, JPanel panelBuscar) {
        try {
            panelAgregar = new AgregarCliente();
            panelAgregar.setSize(960, 440);
            panelAgregar.setLocation(0, 0);

            panelBuscar.removeAll();
            panelBuscar.add(panelAgregar, BorderLayout.CENTER);
            panelBuscar.revalidate();
            panelBuscar.repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID Cliente debe ser un número entero.");
        }
    }

    /*
     * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Actualizar
     * Dentro de este metodo quitamos el panel BuscarClientes y ponemos el panel de la clase ActualizarCliente
     */
    private void btnActualizarActionPerformed(ActionEvent evt, JPanel panelBuscar) {
        try {
            if (estado == true) {
                panelActualizar = new ActualizarCliente();
                panelActualizar.setSize(960, 440);
                panelActualizar.setLocation(0, 0);

                panelBuscar.removeAll();
                panelBuscar.add(panelActualizar, BorderLayout.CENTER);
                panelBuscar.revalidate();
                panelBuscar.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "El cliente está inactivo");
            }


        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID Cliente debe ser un número entero.");
        }
    }

    /*
     * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Inactivar
     */
    private void btnInactivarActionPerformed(ActionEvent evt) {
        try {
            if (estado == true) {
                // Actualizar el estado
                JOptionPane.showMessageDialog(this,
                        clienteControlador.modificarCliente(idCliente, tipoIdentificacion, primerNombre, segundoNombre,
                                primerApellido, segundoApellido, direccion, telefono, correoElectronico, false));
                actualizarTabla(modeloTabla, clienteControlador.listarClientes());
            } else {
                JOptionPane.showMessageDialog(this, "El cliente ya está inactivo");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID Cliente debe ser un número entero.");
        }
    }

    /*
     * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Activar
     */
    private void btnActivarActionPerformed(ActionEvent evt) {
        try {
            if (estado == false) {
                // Actualizar el estado
                JOptionPane.showMessageDialog(this,
                        clienteControlador.modificarCliente(idCliente, tipoIdentificacion, primerNombre, segundoNombre,
                                primerApellido, segundoApellido, direccion, telefono, correoElectronico, true));
                actualizarTabla(modeloTabla, clienteControlador.listarClientes());
            } else {
                JOptionPane.showMessageDialog(this, "El cliente ya está activo");
            }


        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID Cliente debe ser un número entero.");
        }
    }

    /*
     * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Limpiar
     */
    private void btnLimpiarActionPerformed(ActionEvent evt) {
        txtIdCliente.setText("");
        txtTipoIdentificacion.setSelectedItem("");
        txtPrimerNombre.setText("");
        txtSegundoNombre.setText("");
        txtPrimerApellido.setText("");
        txtSegundoApellido.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCorreoElectronico.setText("");
    }

    /*
     * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Buscar
     */
    private void btnBuscarActionPerformed(ActionEvent evt) {
        try {
            Integer idCliente = null;
            if (!txtIdCliente.getText().equals(""))
                idCliente = Integer.parseInt(txtIdCliente.getText());
            String tipoId = txtTipoIdentificacion.getSelectedItem().toString();
            String primerNombre = txtPrimerNombre.getText();
            String segundoNombre = txtSegundoNombre.getText();
            String primerApellido = txtPrimerApellido.getText();
            String segundoApellido = txtSegundoApellido.getText();
            String direccion = txtDireccion.getText();
            String telefono = txtTelefono.getText();
            String correo = txtCorreoElectronico.getText();

            // Buscar el cliente en la base de datos
            List<Cliente> clientes = clienteControlador.buscarClientes(idCliente, tipoId, primerNombre, segundoNombre, primerApellido,
                    segundoApellido, direccion, telefono, correo, null);
            if (clientes != null) {
                actualizarTabla(modeloTabla, clientes);
            } else {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
            }


        } catch (NumberFormatException ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(this, "El ID Cliente debe ser un número entero.");
        }

    }

    /*
     * METODO PARA ACTUALIZAR LOS REGISTROS DE LA TABLA
     */
    private DefaultTableModel actualizarTabla(DefaultTableModel modeloTabla, List<Cliente> listaClientes) {
        // Limpiar la tabla
        int rowCount = modeloTabla.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            modeloTabla.removeRow(i); // eliminar cada fila una por una
        }

        // Agregar cada cliente a la tabla
        for (Cliente cliente : listaClientes) {
            Object[] fila = new Object[10];
            fila[0] = cliente.getIdCliente();
            fila[1] = cliente.getTipoIdentificacion();
            fila[2] = cliente.getPrimerNombre();
            fila[3] = cliente.getSegundoNombre();
            fila[4] = cliente.getPrimerApellido();
            fila[5] = cliente.getSegundoApellido();
            fila[6] = cliente.getDireccion();
            fila[7] = cliente.getTelefono();
            fila[8] = cliente.getCorreoElectronico();
            if (cliente.getEstado() == true) {
                fila[9] = "Activo";
            } else {
                fila[9] = "Inactivo";
            }

            modeloTabla.addRow(fila);
        }

        return modeloTabla;
    }

    /*
     * METODO PARA REALIZAR LA ACCIÓN DE SELECCIONAR UN REGISTRO DE LA TABLA Y OBTENER ESOS VALORES
     */
    private void tabla_MouseClicked(java.awt.event.MouseEvent evt) {
        int i = tablaClientes.getSelectedRow();

        TableModel modeloTabla = tablaClientes.getModel();

        idCliente = Integer.parseInt(modeloTabla.getValueAt(i, 0).toString());
        tipoIdentificacion = modeloTabla.getValueAt(i, 1).toString();
        primerNombre = modeloTabla.getValueAt(i, 2).toString();
        segundoNombre = modeloTabla.getValueAt(i, 3).toString();
        primerApellido = modeloTabla.getValueAt(i, 4).toString();
        segundoApellido = modeloTabla.getValueAt(i, 5).toString();
        direccion = modeloTabla.getValueAt(i, 6).toString();
        telefono = modeloTabla.getValueAt(i, 7).toString();
        correoElectronico = modeloTabla.getValueAt(i, 8).toString();
        Cliente cliente = clienteControlador.buscarClientes(idCliente,"","","","","","","","",null).get(0);
        estado = cliente.getEstado();

    }

}
