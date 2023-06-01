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

import controlador.Controlador;
import controlador.FacturaClienteControlador;
import modelo.Cliente;
import modelo.FacturaCliente;

public class BuscarFacturaCliente extends JPanel {

	// Objeto de la clase Controlador
	private FacturaClienteControlador facClienteControlador;
	// Objeto de la clase PanelAgregar
	private AgregarFacturaCliente panelAgregar;
	// Objeto de la clase VerFacturaCliente
	private VerFacturaCliente panelVer;

	// --------------------Variables-------------------
	private List<FacturaCliente> listaFacturasCliente;
	private JLabel lblIdFactura;
	private JLabel lblIdCliente;
	private JLabel lblNombreCliente;
	private JLabel lblFecha;
	private JLabel lblNumeroProductos;
	private JLabel lblValorTotal;
	private JTextField txtIdFactura;
	private JTextField txtIdCliente;
	private JTextField txtNombreCliente;
	private JTextField txtFecha;
	private JTextField txtNumeroProductos;
	private JTextField txtValorTotal;
	private JTable tablaFacClientes;
	private JScrollPane scrollPane;
	private DefaultTableModel modeloTabla;
	private JButton btnAgregar;
	private JButton btnVer;
	private JButton btnLimpiar;
	private JButton btnBuscar;

	public static int idFactura = 0; // Validar que no ha seleccionado ningun cliente
	public static String idCliente;
	public static String nombreCliente;
	public static String fecha;
	public static String numeroProductos;
	public static String valorTotal;

	/*
	 * METODO CONSTRUCTOR
	 */
	public BuscarFacturaCliente() {
		facClienteControlador = new FacturaClienteControlador();
		inicializarComponentes();
		listaFacturasCliente = new ArrayList<FacturaCliente>();
	}

	
	/*
	 * METODO PARA INICIALIZAR Y DAR ESTILO A TODOS LOS COMPONETES VISUALES DE LA
	 * VISTA DE LA CLASE BuscarFacturaCliente
	 */
	private void inicializarComponentes() {
		setLayout(null);
		JPanel pnlBuscarFacCliente = new JPanel();
		pnlBuscarFacCliente.setBounds(0, 0, 960, 440);
		add(pnlBuscarFacCliente);
		pnlBuscarFacCliente.setLayout(null);

		//------------------------------------------------Labels-------------------------------------------
		lblIdFactura = new JLabel("ID Factura:");
		lblIdFactura.setBounds(20, 0, 80, 20);
		lblIdFactura.setForeground(new Color(135, 134, 133));
		pnlBuscarFacCliente.add(lblIdFactura);

		lblIdCliente = new JLabel("ID Cliente:");
		lblIdCliente.setBounds(180, 0, 120, 20);
		lblIdCliente.setForeground(new Color(135, 134, 133));
		pnlBuscarFacCliente.add(lblIdCliente);

		lblNombreCliente = new JLabel("Nombre Cliente:");
		lblNombreCliente.setBounds(340, 0, 120, 20);
		lblNombreCliente.setForeground(new Color(135, 134, 133));
		pnlBuscarFacCliente.add(lblNombreCliente);

		lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(660, 0, 120, 20);
		lblFecha.setForeground(new Color(135, 134, 133));
		pnlBuscarFacCliente.add(lblFecha);

		lblNumeroProductos = new JLabel("Numero de Productos:");
		lblNumeroProductos.setBounds(20, 47, 139, 20);
		lblNumeroProductos.setForeground(new Color(135, 134, 133));
		pnlBuscarFacCliente.add(lblNumeroProductos);

		lblValorTotal = new JLabel("Valor Total:");
		lblValorTotal.setBounds(180, 49, 120, 20);
		lblValorTotal.setForeground(new Color(135, 134, 133));
		pnlBuscarFacCliente.add(lblValorTotal);
		

		//---------------------------------------TextFields---------------------------------------------
		txtIdFactura = new JTextField();
		txtIdFactura.setBounds(20, 23, 150, 20);
		txtIdFactura.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtIdFactura.setText("");
		pnlBuscarFacCliente.add(txtIdFactura);

		txtIdCliente = new JTextField();
		txtIdCliente.setBounds(180, 23, 150, 20);
		txtIdCliente.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtIdCliente.setText("");
		pnlBuscarFacCliente.add(txtIdCliente);

		txtNombreCliente = new JTextField();
		txtNombreCliente.setBounds(340, 23, 310, 20);
		txtNombreCliente.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtNombreCliente.setText("");
		pnlBuscarFacCliente.add(txtNombreCliente);

		txtFecha = new JTextField();
		txtFecha.setBounds(660, 23, 150, 20);
		txtFecha.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtFecha.setText("");
		pnlBuscarFacCliente.add(txtFecha);

		txtNumeroProductos = new JTextField();
		txtNumeroProductos.setBounds(20, 70, 150, 20);
		txtNumeroProductos.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtNumeroProductos.setText("");
		pnlBuscarFacCliente.add(txtNumeroProductos);

		txtValorTotal = new JTextField();
		txtValorTotal.setBounds(180, 70, 150, 20);
		txtValorTotal.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtValorTotal.setText("");
		pnlBuscarFacCliente.add(txtValorTotal);
		
		

		// ------------------------------Tabla--------------------------------------------------------
		modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("ID factura");
		modeloTabla.addColumn("ID cliente");
		modeloTabla.addColumn("Nombre del cliente");
		modeloTabla.addColumn("Fecha");
		modeloTabla.addColumn("Número de productos");
		modeloTabla.addColumn("Valor total");

		tablaFacClientes = new JTable(actualizarTabla(modeloTabla, facClienteControlador.listarFacturasCliente()));
		scrollPane = new JScrollPane(tablaFacClientes);
		scrollPane.setBorder(new LineBorder(Color.WHITE, 0, true));
		scrollPane.setBounds(10, 106, 940, 262);
		// Color de la tabla
		tablaFacClientes.getTableHeader().setBackground(new Color(182, 220, 255));
		tablaFacClientes.getTableHeader().setForeground(new Color(135, 134, 133));
		tablaFacClientes.setForeground(new Color(133, 111, 69));
		// Accion de quitar o poner lines en la tabla
		tablaFacClientes.setShowVerticalLines(false);
		tablaFacClientes.setShowHorizontalLines(true);
		// Dar color a las lineas de la tabla
		tablaFacClientes.setGridColor(new Color(182, 220, 255));
		// Instancia del objeto del borde de la tabla
		Border borde = BorderFactory.createLineBorder(new Color(182, 220, 255));
		// Asigna el borde instanciado a la tabla
		tablaFacClientes.setBorder(borde);
		tablaFacClientes.getTableHeader().setBorder(borde);
		// Otras asignaciones de estetica de la tabla
		tablaFacClientes.setRowHeight(30); // Establece la altura de las filas
		tablaFacClientes.setIntercellSpacing(new Dimension(0, 0)); // Establece el espaciado entre celdas

		pnlBuscarFacCliente.add(scrollPane);

		tablaFacClientes.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tabla_MouseClicked(evt);
			}
		});

		
		
		// -------------------------Botones-------------------------------------------------------
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(837, 399, 100, 30);
		btnAgregar.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		btnAgregar.setBackground(new Color(192, 192, 192));
		btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAgregar.setForeground(new Color(255, 255, 255));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnAgregarActionPerformed(evt, pnlBuscarFacCliente);
			}
		});
		pnlBuscarFacCliente.add(btnAgregar);

		btnVer = new JButton("Ver");
		btnVer.setBounds(725, 399, 100, 30);
		btnVer.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		btnVer.setBackground(new Color(192, 192, 192));
		btnVer.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVer.setForeground(new Color(255, 255, 255));
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// ValidaridCliente que no ha seleccionado ningun cliente//Si no mensaje
				if (idFactura != 0) {
					btnVerActionPerformed(evt, pnlBuscarFacCliente);
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una Factura");
				}

			}
		});
		pnlBuscarFacCliente.add(btnVer);


		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(837, 65, 100, 30);
		btnLimpiar.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		btnLimpiar.setBackground(new Color(192, 192, 192));
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLimpiar.setForeground(new Color(255, 255, 255));
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnLimpiarActionPerformed(evt);
				actualizarTabla(modeloTabla, facClienteControlador.listarFacturasCliente());			}
		});
		pnlBuscarFacCliente.add(btnLimpiar);

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
		pnlBuscarFacCliente.add(btnBuscar);

	}

	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Agregar
	 * Dentro de este metodo quitamos el panel BuscarClientes y ponemos el panel de la clase AgregarCliente
	 */	
	private void btnAgregarActionPerformed(ActionEvent evt, JPanel pnlBuscarFacCliente) {
		try {
			panelAgregar = new AgregarFacturaCliente();
			panelAgregar.setSize(960, 440);
			panelAgregar.setLocation(0, 0);

			pnlBuscarFacCliente.removeAll();
			pnlBuscarFacCliente.add(panelAgregar, BorderLayout.CENTER);
			pnlBuscarFacCliente.revalidate();
			pnlBuscarFacCliente.repaint();
		} catch (NumberFormatException ex) {
//			JOptionPane.showMessageDialog(this, "Campos invalidos");
		}
	}

	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Actualizar
	 * Dentro de este metodo quitamos el panel BuscarFacturaCliente y ponemos el panel de la clase VerFacturaCliente
	 */
	private void btnVerActionPerformed(ActionEvent evt, JPanel pnlBuscarFacCliente) {
		try {
			panelVer = new VerFacturaCliente();
			panelVer.setSize(960, 440);
			panelVer.setLocation(0, 0);

			pnlBuscarFacCliente.removeAll();
			pnlBuscarFacCliente.add(panelVer, BorderLayout.CENTER);
			pnlBuscarFacCliente.revalidate();
			pnlBuscarFacCliente.repaint();

		} catch (NumberFormatException ex) {
//			JOptionPane.showMessageDialog(this, "Campos invalidos");
		}
	}

	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Limpiar
	 */
	private void btnLimpiarActionPerformed(ActionEvent evt) {
		txtIdFactura.setText("");
		txtIdCliente.setText("");
		txtNombreCliente.setText("");
		txtFecha.setText("");
		txtNumeroProductos.setText("");
		txtValorTotal.setText("");
	}

	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Buscar
	 */
	private void btnBuscarActionPerformed(ActionEvent evt) {
		try {
			Integer idFacturaCliente = null;
			if(!txtIdFactura.getText().equals(""))
				idFacturaCliente = Integer.parseInt(txtIdFactura.getText());
			idCliente = txtIdCliente.getText();
			String fecha = txtFecha.getText();
			String numeroProductos = txtNumeroProductos.getText();
			String valorTotal = txtValorTotal.getText();

			// Buscar el cliente en la base de datos
			List<FacturaCliente> clientes = facClienteControlador.buscarFacturasCliente(idFacturaCliente,"","","",fecha,valorTotal,numeroProductos,idCliente);
			if (clientes != null) {
				actualizarTabla(modeloTabla,clientes);
			} else {
				JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
			}


		} catch (NumberFormatException ex) {
			System.err.println(ex.getMessage());
//			JOptionPane.showMessageDialog(this, "Campos invalidos");
		}

	}

	/*
	 * METODO PARA ACTUALIZAR LOS REGISTROS DE LA TABLA
	 */
	private DefaultTableModel actualizarTabla(DefaultTableModel modeloTabla,List<FacturaCliente> listaFacturasClientes) {
		// Limpiar la tabla
		int rowCount = modeloTabla.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			modeloTabla.removeRow(i); // eliminar cada fila una por una
		}

		// Agregar cada cliente a la tabla
		for (FacturaCliente facturaCliente : listaFacturasClientes) {
			Object[] fila = new Object[6];
			fila[0] = facturaCliente.getIdFacturaCliente();
			fila[1] = facturaCliente.getCliente().getIdCliente();
			fila[2] = facturaCliente.getCliente().getPrimerNombre() + "" + facturaCliente.getCliente().getPrimerApellido();
			fila[3] = facturaCliente.getFechaFactura();
			fila[4] = facturaCliente.getCantidadProducto();
			fila[5] = facturaCliente.getTotal();
			modeloTabla.addRow(fila);
		}

		return modeloTabla;
	}

	/*
	 * METODO PARA REALIZAR LA ACCIÓN DE SELECCIONAR UN REGISTRO DE LA TABLA Y OBTENER ESOS VALORES
	 */
	private void tabla_MouseClicked(java.awt.event.MouseEvent evt) {
		int i = tablaFacClientes.getSelectedRow();

		TableModel modeloTabla = tablaFacClientes.getModel();

		idFactura = Integer.parseInt(modeloTabla.getValueAt(i, 0).toString());
		idCliente = modeloTabla.getValueAt(i, 1).toString();
		nombreCliente = modeloTabla.getValueAt(i, 2).toString();
		fecha = modeloTabla.getValueAt(i, 3).toString();
		numeroProductos = modeloTabla.getValueAt(i, 4).toString();
		valorTotal = modeloTabla.getValueAt(i, 5).toString();

	}

}
