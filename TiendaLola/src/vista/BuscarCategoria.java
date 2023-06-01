package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import controlador.CategoriaControlador;
import controlador.Controlador;
import modelo.Categoria;
import modelo.Producto;

public class BuscarCategoria extends JPanel {

    //Objeto de la clase Controlador
    private CategoriaControlador categoriaControlador;
    //Objeto de la clase PanelAgregar
    private AgregarCategoria panelAgregar;
    //Objeto de la clase panelActualizar
    private ActualizarCategoria panelActualizar;

    //--------------------Variables-------------------
    private List<Categoria> listaCategorias;
    private JLabel lblIdCategoria;
    private JLabel lblNombreCategoria;
    private JLabel lblDescripcion;
    private JTextField txtIdCategoria;
    private JTextField txtNombreCategoria;
    private JTextField txtCategoria;
    private JTable tablaCategorias;
    private JScrollPane scrollPane;
    private DefaultTableModel modeloTabla;
    private JButton btnAgregar;
    private JButton btnActualizar;
    private JButton btnInactivar;
    private JButton btnActivar;
    private JButton btnLimpiar;
    private JButton btnBuscar;

    public static int idCategoria = 0;        //Validar que no ha seleccionado ningun cliente
    public static String nombreCategoria;
    public static String descripcion;
    public static Boolean estado;

    /*
     * METODO CONSTRUCTOR
     */
    public BuscarCategoria() {
        categoriaControlador = new CategoriaControlador();
        inicializarComponentes();
        listaCategorias = new ArrayList<Categoria>();
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
        lblIdCategoria = new JLabel("ID Categoría:");
        lblIdCategoria.setBounds(20, 0, 80, 20);
        lblIdCategoria.setForeground(new Color(135, 134, 133));
        panelBuscar.add(lblIdCategoria);

        lblNombreCategoria = new JLabel("Nombre Categoría:");
        lblNombreCategoria.setBounds(180, 0, 120, 20);
        lblNombreCategoria.setForeground(new Color(135, 134, 133));
        panelBuscar.add(lblNombreCategoria);

        lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(340, 0, 120, 20);
        lblDescripcion.setForeground(new Color(135, 134, 133));
        panelBuscar.add(lblDescripcion);


        // TextFields
        txtIdCategoria = new JTextField();
        txtIdCategoria.setBounds(20, 23, 150, 20);
        txtIdCategoria.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
        txtIdCategoria.setText("");
        panelBuscar.add(txtIdCategoria);

        txtNombreCategoria = new JTextField();
        txtNombreCategoria.setBounds(180, 23, 150, 20);
        txtNombreCategoria.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
        txtNombreCategoria.setText("");
        panelBuscar.add(txtNombreCategoria);

        txtCategoria = new JTextField();
        txtCategoria.setBounds(340, 23, 310, 20);
        txtCategoria.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
        txtCategoria.setText("");
        panelBuscar.add(txtCategoria);

        // Tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID Categoria");
        modeloTabla.addColumn("Nombre de la Categoria");
        modeloTabla.addColumn("Descripción");
        modeloTabla.addColumn("Estado");

        tablaCategorias = new JTable(actualizarTabla(modeloTabla, categoriaControlador.listarCategorias()));
        scrollPane = new JScrollPane(tablaCategorias);
        scrollPane.setBorder(new LineBorder(Color.WHITE, 0, true));
        scrollPane.setBounds(10, 70, 940, 300);
        //Color de la tabla
        tablaCategorias.getTableHeader().setBackground(new Color(182, 220, 255));
        tablaCategorias.getTableHeader().setForeground(new Color(135, 134, 133));
        tablaCategorias.setForeground(new Color(133, 111, 69));
        //Accion de quitar o poner lines en la tabla
        tablaCategorias.setShowVerticalLines(false);
        tablaCategorias.setShowHorizontalLines(true);
        //Dar color a las lineas de la tabla
        tablaCategorias.setGridColor(new Color(182, 220, 255));
        //Instancia del objeto del borde de la tabla
        Border borde = BorderFactory.createLineBorder(new Color(182, 220, 255));
        //Asigna el borde instanciado a la tabla
        tablaCategorias.setBorder(borde);
        tablaCategorias.getTableHeader().setBorder(borde);
        //Otras asignaciones de estetica de la tabla
        tablaCategorias.setRowHeight(30); // Establece la altura de las filas
        tablaCategorias.setIntercellSpacing(new Dimension(0, 0)); // Establece el espaciado entre celdas

        panelBuscar.add(scrollPane);

        //		actualizarTabla(modeloTabla,categoriaControlador.listarCategorias());
        tablaCategorias.addMouseListener(new java.awt.event.MouseAdapter() {
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
                if (idCategoria != 0) {
                    btnActualizarActionPerformed(evt, panelBuscar);
                    actualizarTabla(modeloTabla, categoriaControlador.listarCategorias());
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una Categorías");
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
                if (idCategoria != 0) {
                    btnInactivarActionPerformed(evt);
                    actualizarTabla(modeloTabla, categoriaControlador.listarCategorias());
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una Categoria");
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
                if (idCategoria != 0) {
                    btnActivarActionPerformed(evt);
                    actualizarTabla(modeloTabla, categoriaControlador.listarCategorias());
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una Categoria");
                }

            }
        });
        panelBuscar.add(btnActivar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(837, 18, 100, 30);
        btnLimpiar.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
        btnLimpiar.setBackground(new Color(192, 192, 192));
        btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnLimpiar.setForeground(new Color(255, 255, 255));
        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
                actualizarTabla(modeloTabla, categoriaControlador.listarCategorias());
            }
        });
        panelBuscar.add(btnLimpiar);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(700, 18, 100, 30);
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
            panelAgregar = new AgregarCategoria();
            panelAgregar.setSize(960, 440);
            panelAgregar.setLocation(0, 0);

            panelBuscar.removeAll();
            panelBuscar.add(panelAgregar, BorderLayout.CENTER);
            panelBuscar.revalidate();
            panelBuscar.repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID Categoria debe ser un número entero.");
        }
    }

    /*
     * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Actualizar
     * Dentro de este metodo quitamos el panel BuscarClientes y ponemos el panel de la clase ActualizarCliente
     */
    private void btnActualizarActionPerformed(ActionEvent evt, JPanel panelBuscar) {
        try {
            if (estado == true) {
                // Abrir el panel de actualizar
                panelActualizar = new ActualizarCategoria();
                panelActualizar.setSize(960, 440);
                panelActualizar.setLocation(0, 0);

                panelBuscar.removeAll();
                panelBuscar.add(panelActualizar, BorderLayout.CENTER);
                panelBuscar.revalidate();
                panelBuscar.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "La categoría está inactiva");
            }


        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID Categoria debe ser un número entero.");
        }
    }

    /*
     * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Inactiva
     */
    private void btnInactivarActionPerformed(ActionEvent evt) {
        try {
            if (estado == true) {
                // Modificar el estado
                JOptionPane.showMessageDialog(this,
                        categoriaControlador.modificarCategoria(idCategoria, nombreCategoria, descripcion, false));
                actualizarTabla(modeloTabla, categoriaControlador.listarCategorias());
            } else {
                JOptionPane.showMessageDialog(this, "La categoría ya está inactiva");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID Categoria debe ser un número entero.");
        }
    }

    /*
     * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Activar
     */
    private void btnActivarActionPerformed(ActionEvent evt) {
        try {
            if (estado == false) {
                // Modificar el estado
                JOptionPane.showMessageDialog(this,
                        categoriaControlador.modificarCategoria(idCategoria, nombreCategoria, descripcion, true));
                actualizarTabla(modeloTabla, categoriaControlador.listarCategorias());
            } else {
                JOptionPane.showMessageDialog(this, "La categoría ya está activa");
            }


        } catch (NumberFormatException ex) {
        }
    }

    /*
     * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Limpiar
     */
    private void btnLimpiarActionPerformed(ActionEvent evt) {
        txtIdCategoria.setText("");
        txtNombreCategoria.setText("");
        txtCategoria.setText("");
    }

    /*
     * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Buscar
     */
    private void btnBuscarActionPerformed(ActionEvent evt) {
        try {
            Integer idCategoria = null;
            if (!txtIdCategoria.getText().equals(""))
                idCategoria = Integer.parseInt(txtIdCategoria.getText());
            String nombre = txtNombreCategoria.getText();
            String descripcion = txtCategoria.getText();

            // Buscar el categoria en la base de datos
            List<Categoria> categorias = categoriaControlador.buscarCategorias(idCategoria, nombre, descripcion, true);
            if (categorias != null) {
                actualizarTabla(modeloTabla, categorias);
            } else {
                JOptionPane.showMessageDialog(this, "Categoria no encontrada.");
            }


        } catch (NumberFormatException ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(this, "El ID Categoria debe ser un número entero.");
        }

    }

    /*
     * METODO PARA ACTUALIZAR LOS REGISTROS DE LA TABLA
     */
    private DefaultTableModel actualizarTabla(DefaultTableModel modeloTabla, List<Categoria> listaCategorias) {
        // Limpiar la tabla
        int rowCount = modeloTabla.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            modeloTabla.removeRow(i); // eliminar cada fila una por una
        }

        // Agregar cada cliente a la tabla
        for (Categoria categoria : listaCategorias) {
            Object[] fila = new Object[4];
            fila[0] = categoria.getIdCategoria();
            fila[1] = categoria.getNombre();
            fila[2] = categoria.getDescripcion();
            if (categoria.getEstado() == true) {
                fila[3] = "Activo";
            } else {
                fila[3] = "Inactivo";
            }
            modeloTabla.addRow(fila);
        }

        return modeloTabla;
    }

    /*
     * METODO PARA REALIZAR LA ACCIÓN DE SELECCIONAR UN REGISTRO DE LA TABLA Y OBTENER ESOS VALORES
     */
    private void tabla_MouseClicked(java.awt.event.MouseEvent evt) {
        int i = tablaCategorias.getSelectedRow();

        TableModel modeloTabla = tablaCategorias.getModel();

        idCategoria = Integer.parseInt(modeloTabla.getValueAt(i, 0).toString());
        nombreCategoria = modeloTabla.getValueAt(i, 1).toString();
        descripcion = modeloTabla.getValueAt(i, 2).toString();
        Categoria categoria = categoriaControlador.buscarCategorias(idCategoria,nombreCategoria,descripcion,null).get(0);
        estado = categoria.getEstado();

    }

}
