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

import controlador.FacturaProveedorControlador;
import modelo.FacturaCliente;
import modelo.FacturaProveedor;

public class BuscarFacturaProveedor extends JPanel {

	// Objeto de la clase Controlador
	private FacturaProveedorControlador facProveedorControlador;
	// Objeto de la clase PanelAgregar
	private AgregarFacturaProveedor panelAgregarFac;
	// Objeto de la clase panelActualizar
	private VerFacturaProveedor panelVer;

	// --------------------Variables-------------------
	private List<FacturaProveedor> listaFacturasProveedor;
	private JLabel lblIdFactura;
	private JLabel lblIdProveedor;
	private JLabel lblNombreProveedor;
	private JLabel lblFecha;
	private JLabel lblNumeroProductos;
	private JLabel lblValorTotal;
	private JTextField txtIdFactura;
	private JTextField txtIdProveedor;
	private JTextField txtNombreProveedor;
	private JTextField txtFecha;
	private JTextField txtNumeroProductos;
	private JTextField txtValorTotal;
	private JTable tablaFacProveedores;
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
	public BuscarFacturaProveedor() {
		facProveedorControlador = new FacturaProveedorControlador();
		listaFacturasProveedor = new ArrayList<FacturaProveedor>();
		inicializarComponentes();
	}
	
	/*
	 * METODO PARA INICIALIZAR Y DAR ESTILO A TODOS LOS COMPONETES VISUALES DE LA
	 * VISTA DE LA CLASE BuscarFacturaCliente
	 */
	private void inicializarComponentes() {
		setLayout(null);
		JPanel pnlBuscarFacProveedor = new JPanel();
		pnlBuscarFacProveedor.setBounds(0, 0, 960, 440);
		add(pnlBuscarFacProveedor);
		pnlBuscarFacProveedor.setLayout(null);

		//------------------------------------------------Labels-------------------------------------------
		lblIdFactura = new JLabel("ID Factura:");
		lblIdFactura.setBounds(20, 0, 80, 20);
		lblIdFactura.setForeground(new Color(135, 134, 133));
		pnlBuscarFacProveedor.add(lblIdFactura);

		lblIdProveedor = new JLabel("ID Proveedor:");
		lblIdProveedor.setBounds(180, 0, 120, 20);
		lblIdProveedor.setForeground(new Color(135, 134, 133));
		pnlBuscarFacProveedor.add(lblIdProveedor);

		lblNombreProveedor = new JLabel("Nombre Proveedor:");
		lblNombreProveedor.setBounds(340, 0, 120, 20);
		lblNombreProveedor.setForeground(new Color(135, 134, 133));
		pnlBuscarFacProveedor.add(lblNombreProveedor);

		lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(660, 0, 120, 20);
		lblFecha.setForeground(new Color(135, 134, 133));
		pnlBuscarFacProveedor.add(lblFecha);

		lblNumeroProductos = new JLabel("Numero de Productos:");
		lblNumeroProductos.setBounds(20, 47, 120, 20);
		lblNumeroProductos.setForeground(new Color(135, 134, 133));
		pnlBuscarFacProveedor.add(lblNumeroProductos);

		lblValorTotal = new JLabel("Valor Total:");
		lblValorTotal.setBounds(180, 49, 120, 20);
		lblValorTotal.setForeground(new Color(135, 134, 133));
		pnlBuscarFacProveedor.add(lblValorTotal);
		

		//---------------------------------------TextFields---------------------------------------------
		txtIdFactura = new JTextField();
		txtIdFactura.setBounds(20, 23, 150, 20);
		txtIdFactura.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtIdFactura.setText("");
		pnlBuscarFacProveedor.add(txtIdFactura);

		txtIdProveedor = new JTextField();
		txtIdProveedor.setBounds(180, 23, 150, 20);
		txtIdProveedor.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtIdProveedor.setText("");
		pnlBuscarFacProveedor.add(txtIdProveedor);

		txtNombreProveedor = new JTextField();
		txtNombreProveedor.setBounds(340, 23, 310, 20);
		txtNombreProveedor.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtNombreProveedor.setText("");
		pnlBuscarFacProveedor.add(txtNombreProveedor);

		txtFecha = new JTextField();
		txtFecha.setBounds(660, 23, 150, 20);
		txtFecha.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtFecha.setText("");
		pnlBuscarFacProveedor.add(txtFecha);

		txtNumeroProductos = new JTextField();
		txtNumeroProductos.setBounds(20, 70, 150, 20);
		txtNumeroProductos.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtNumeroProductos.setText("");
		pnlBuscarFacProveedor.add(txtNumeroProductos);

		txtValorTotal = new JTextField();
		txtValorTotal.setBounds(180, 70, 150, 20);
		txtValorTotal.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtValorTotal.setText("");
		pnlBuscarFacProveedor.add(txtValorTotal);
		
		

		// ------------------------------Tabla--------------------------------------------------------
		modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("ID factura");
		modeloTabla.addColumn("ID Proveedor");
		modeloTabla.addColumn("Nombre del proveedor");
		modeloTabla.addColumn("Fecha");
		modeloTabla.addColumn("Número de productos");
		modeloTabla.addColumn("Valor total");

		tablaFacProveedores = new JTable(actualizarTabla(modeloTabla, facProveedorControlador.listarFacturasProveedor()));
		scrollPane = new JScrollPane(tablaFacProveedores);
		scrollPane.setBorder(new LineBorder(Color.WHITE, 0, true));
		scrollPane.setBounds(10, 106, 940, 262);
		// Color de la tabla
		tablaFacProveedores.getTableHeader().setBackground(new Color(182, 220, 255));
		tablaFacProveedores.getTableHeader().setForeground(new Color(135, 134, 133));
		tablaFacProveedores.setForeground(new Color(133, 111, 69));
		// Accion de quitar o poner lines en la tabla
		tablaFacProveedores.setShowVerticalLines(false);
		tablaFacProveedores.setShowHorizontalLines(true);
		// Dar color a las lineas de la tabla
		tablaFacProveedores.setGridColor(new Color(182, 220, 255));
		// Instancia del objeto del borde de la tabla
		Border borde = BorderFactory.createLineBorder(new Color(182, 220, 255));
		// Asigna el borde instanciado a la tabla
		tablaFacProveedores.setBorder(borde);
		tablaFacProveedores.getTableHeader().setBorder(borde);
		// Otras asignaciones de estetica de la tabla
		tablaFacProveedores.setRowHeight(30); // Establece la altura de las filas
		tablaFacProveedores.setIntercellSpacing(new Dimension(0, 0)); // Establece el espaciado entre celdas

		pnlBuscarFacProveedor.add(scrollPane);

		tablaFacProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
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
				btnAgregarActionPerformed(evt, pnlBuscarFacProveedor);
			}
		});
		pnlBuscarFacProveedor.add(btnAgregar);

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
					btnVerActionPerformed(evt, pnlBuscarFacProveedor);
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una Factura");
				}

			}
		});
		pnlBuscarFacProveedor.add(btnVer);


		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(837, 65, 100, 30);
		btnLimpiar.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		btnLimpiar.setBackground(new Color(192, 192, 192));
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLimpiar.setForeground(new Color(255, 255, 255));
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnLimpiarActionPerformed(evt);
//				actualizarTabla(modeloTabla, controlador.listarClientes());
			}
		});
		pnlBuscarFacProveedor.add(btnLimpiar);

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
		pnlBuscarFacProveedor.add(btnBuscar);

	}
	
	
	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Agregar
	 * Dentro de este metodo quitamos el panel BuscarFacturaProveedor y ponemos el panel de la clase AgregarFacturaProveedor
	 */	
	private void btnAgregarActionPerformed(ActionEvent evt, JPanel pnlBuscarFacProveedor) {
		try {
			panelAgregarFac = new AgregarFacturaProveedor();
			panelAgregarFac.setSize(960, 440);
			panelAgregarFac.setLocation(0, 0);

			pnlBuscarFacProveedor.removeAll();
			pnlBuscarFacProveedor.add(panelAgregarFac, BorderLayout.CENTER);
			pnlBuscarFacProveedor.revalidate();
			pnlBuscarFacProveedor.repaint();
		} catch (NumberFormatException ex) {
//			JOptionPane.showMessageDialog(this, "El ID Cliente debe ser un número entero.");
		}
	}
	
	
	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Ver
	 * Dentro de este metodo quitamos el panel BuscarFacturaCliente y ponemos el panel de la clase VerFacturaCliente
	 */
	private void btnVerActionPerformed(ActionEvent evt, JPanel pnlBuscarFacCliente) {
		try {
			panelVer = new VerFacturaProveedor();
			panelVer.setSize(960, 440);
			panelVer.setLocation(0, 0);

			pnlBuscarFacCliente.removeAll();
			pnlBuscarFacCliente.add(panelVer, BorderLayout.CENTER);
			pnlBuscarFacCliente.revalidate();
			pnlBuscarFacCliente.repaint();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "El ID factura debe ser un número entero.");
		}
	}
	
	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Limpiar
	 */
	private void btnLimpiarActionPerformed(ActionEvent evt) {
		txtIdFactura.setText("");
		txtIdProveedor.setText("");
		txtNombreProveedor.setText("");
		txtFecha.setText("");
		txtNumeroProductos.setText("");
		txtValorTotal.setText("");
	}
	
	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Buscar
	 */
	private void btnBuscarActionPerformed(ActionEvent evt) {
		try {
//			Integer idCliente = null;
//			if(!txtIdFactura.getText().equals(""))
//				idCliente = Integer.parseInt(txtIdFactura.getText());
//			String tipoId = txtIdCliente.getSelectedItem().toString();
//			String primerNombre = txtNombreCliente.getText();
//			String segundoNombre = txtFecha.getText();
//			String primerApellido = txtNumeroProductos.getText();
//			String segundoApellido = txtValorTotal.getText();
//			String direccion = txtDireccion.getText();
//			String telefono = txtTelefono.getText();
//			String correo = txtCorreoElectronico.getText();
//
//			// Buscar el cliente en la base de datos
//			List<Cliente> clientes = controlador.buscarClientes(idCliente,tipoId,primerNombre,segundoNombre,primerApellido,
//					segundoApellido,direccion,telefono,correo);
//			if (clientes != null) {
//				actualizarTabla(modeloTabla,clientes);
//			} else {
//				JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
//			}


		} catch (NumberFormatException ex) {
			System.err.println(ex.getMessage());
//			JOptionPane.showMessageDialog(this, "El ID Cliente debe ser un número entero.");
		}

	}
	
	/*
	 * METODO PARA ACTUALIZAR LOS REGISTROS DE LA TABLA
	 */
	private DefaultTableModel actualizarTabla(DefaultTableModel modeloTabla,List<FacturaProveedor> listaFacturasProveedor) {
		// Limpiar la tabla
		int rowCount = modeloTabla.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			modeloTabla.removeRow(i); // eliminar cada fila una por una
		}

		// Agregar cada cliente a la tabla
		for (FacturaProveedor facturaProveedor : listaFacturasProveedor) {
			Object[] fila = new Object[6];
			fila[0] = facturaProveedor.getIdFacturaProveedor();
			fila[1] = facturaProveedor.getProveedor().getIdProveedor();
			fila[2] = facturaProveedor.getProveedor().getPrimerNombre() + "" + facturaProveedor.getProveedor().getPrimerApellido();
			fila[3] = facturaProveedor.getFechaFactura();
			fila[4] = facturaProveedor.getCantidadProducto();
			fila[5] = facturaProveedor.getTotal();
			modeloTabla.addRow(fila);
		}

		return modeloTabla;
	}

	/*
	 * METODO PARA REALIZAR LA ACCIÓN DE SELECCIONAR UN REGISTRO DE LA TABLA Y OBTENER ESOS VALORES
	 */
	private void tabla_MouseClicked(java.awt.event.MouseEvent evt) {
		int i = tablaFacProveedores.getSelectedRow();

		TableModel modeloTabla = tablaFacProveedores.getModel();

		idFactura = Integer.parseInt(modeloTabla.getValueAt(i, 0).toString());
		idCliente = modeloTabla.getValueAt(i, 1).toString();
		nombreCliente = modeloTabla.getValueAt(i, 2).toString();
		fecha = modeloTabla.getValueAt(i, 3).toString();
		numeroProductos = modeloTabla.getValueAt(i, 4).toString();
		valorTotal = modeloTabla.getValueAt(i, 5).toString();

	}


}
