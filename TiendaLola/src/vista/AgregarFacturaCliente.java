package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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

import controlador.CategoriaControlador;
import controlador.ClienteControlador;
import controlador.FacturaClienteControlador;
import controlador.ProductoControlador;
import modelo.Categoria;
import modelo.Cliente;
import modelo.DetalleFacturaCliente;

import javax.swing.JComboBox;

public class AgregarFacturaCliente extends JPanel {

	//Objetod e la clase Controlador
	private FacturaClienteControlador facClienteControlador;
	//Objeto de la clase ProductoControlador
	private ProductoControlador productoControlador;
	//Objeto de la clase BuscarClientes
	private BuscarFacturaCliente panelBuscar;	
	//Objeto de la clase BuscarClientes
	private EscogerProductoCliente panelEscogerProducto;
	//Objeto de la clase ClienteControlador
	private ClienteControlador clienteControl;

	//-------------------Variables-----------------
	public static List<DetalleFacturaCliente> detalleProductos;
	private JLabel lblRegistroCategoria;
	private JLabel lblIdFactura;
	private JLabel lblCorreo; 
	private JLabel lblTelefono; 
	private JLabel lblDireccion;
	private JLabel lblCliente;
	private JTextField txtIdFactura; 
	private JTextField txtCorreo; 
	private JTextField txtTelefono; 
	private JTextField txtDireccion;
	private JComboBox txtCliente;
	private JTable tablaProductos;
	private JScrollPane scrollPane;
	private DefaultTableModel modeloTabla;
	private JButton btnGuardar; 
	private JButton btnSalir; 
	private JButton btnAgregar;

	private String nombreProducto = "";




	/*
	 * METODO CONSTRUCTOR
	 */
	public AgregarFacturaCliente() {
		facClienteControlador = new FacturaClienteControlador();
		productoControlador = new ProductoControlador();
		clienteControl = new ClienteControlador();
		inicializarComponentes();
	}

	/*
	 * METODO PARA INICIALIZAR Y DAR ESTILO A TODOS LOS COMPONETES VISUALES DE LA VISTA DE LA CLASE BuscarClientes
	 */
	private void inicializarComponentes() {
		setLayout(null);
		JPanel panelAgregar = new JPanel();
		panelAgregar.setBounds(0, 0, 960, 440);
		add(panelAgregar);
		panelAgregar.setLayout(null);


		// Labels
		lblRegistroCategoria = new JLabel("Registro de Factura Cliente");
		lblRegistroCategoria.setForeground(new Color(135, 134, 133));
		lblRegistroCategoria.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRegistroCategoria.setBounds(65, 34, 216, 20);
		panelAgregar.add(lblRegistroCategoria);

		lblCorreo = new JLabel("Correo Electrónico:");
		lblCorreo.setBounds(80, 135, 120, 20);
		lblCorreo.setForeground(new Color(135, 134, 133));
		panelAgregar.add(lblCorreo);

		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(323, 81, 120, 20);
		lblTelefono.setForeground(new Color(135, 134, 133));
		panelAgregar.add(lblTelefono);

		lblIdFactura = new JLabel("ID Factura:");
		lblIdFactura.setBounds(80, 81, 80, 20);
		lblIdFactura.setForeground(new Color(135, 134, 133));
		panelAgregar.add(lblIdFactura);

		lblDireccion = new JLabel("Dirección:");
		lblDireccion.setForeground(new Color(135, 134, 133));
		lblDireccion.setBounds(80, 189, 80, 20);
		panelAgregar.add(lblDireccion);

		lblCliente = new JLabel("Cliente:");
		lblCliente.setForeground(new Color(135, 134, 133));
		lblCliente.setBounds(323, 189, 80, 20);
		panelAgregar.add(lblCliente);


		// TextFields
		txtIdFactura = new JTextField();
		txtIdFactura.setBounds(80, 104, 200, 20);
		txtIdFactura.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelAgregar.add(txtIdFactura);

		txtCorreo = new JTextField();
		txtCorreo.setBounds(80, 158, 443, 20);
		txtCorreo.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelAgregar.add(txtCorreo);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(323, 104, 200, 20);
		txtTelefono.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelAgregar.add(txtTelefono);

		txtCliente = new JComboBox();
		txtCliente.setModel(new DefaultComboBoxModel(obtenerClientes()));
		txtCliente.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtCliente.setBounds(323, 212, 200, 20);
		panelAgregar.add(txtCliente);

		txtDireccion = new JTextField();
		txtDireccion.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtDireccion.setBounds(80, 212, 200, 20);
		panelAgregar.add(txtDireccion);

		// Botones
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(470, 320, 100, 30);
		btnGuardar.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		btnGuardar.setBackground(new Color(192, 192, 192));
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGuardar.setForeground(new Color(255, 255, 255));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnGuardarActionPerformed(evt, panelAgregar);
			}
		});
		panelAgregar.add(btnGuardar);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(330, 320, 100, 30);
		btnSalir.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		btnSalir.setBackground(new Color(192, 192, 192));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnSalirActionPerformed(evt, panelAgregar);
			}
		});
		panelAgregar.add(btnSalir);



		// ---------------------------------- Panel de Productos --------------------------------------------------
		// Labels
		lblRegistroCategoria = new JLabel("Lista de Productos");
		lblRegistroCategoria.setForeground(new Color(135, 134, 133));
		lblRegistroCategoria.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRegistroCategoria.setBounds(585, 34, 216, 20);
		panelAgregar.add(lblRegistroCategoria);

		// Tabla
		modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("Nombre");
		modeloTabla.addColumn("Cantidad");
		modeloTabla.addColumn("Total");

		//		detalleProductos = new ArrayList<DetalleFacturaCliente>();
		if(detalleProductos == null) {
			tablaProductos = new JTable(actualizarTabla(modeloTabla,new ArrayList<DetalleFacturaCliente>()));
		}else {
			tablaProductos = new JTable(actualizarTabla(modeloTabla,detalleProductos));

		}


		scrollPane = new JScrollPane(tablaProductos);
		scrollPane.setBorder(new LineBorder(Color.WHITE, 0, true));
		scrollPane.setBounds(580, 70, 310, 200);
		//Color de la tabla
		tablaProductos.getTableHeader().setBackground(new Color(182, 220, 255));
		tablaProductos.getTableHeader().setForeground(new Color(135, 134, 133));
		tablaProductos.setForeground(new Color(133, 111, 69));
		//Accion de quitar o poner lines en la tabla 
		tablaProductos.setShowVerticalLines(false);
		tablaProductos.setShowHorizontalLines(true);
		//Dar color a las lineas de la tabla
		tablaProductos.setGridColor(new Color(182,220,255));
		//Instancia del objeto del borde de la tabla
		Border borde = BorderFactory.createLineBorder(new Color(182,220,255));
		//Asigna el borde instanciado a la tabla
		tablaProductos.setBorder(borde);
		tablaProductos.getTableHeader().setBorder(borde);
		//Otras asignaciones de estetica de la tabla
		tablaProductos.setRowHeight(30); // Establece la altura de las filas
		tablaProductos.setIntercellSpacing(new Dimension(0, 0)); // Establece el espaciado entre celdas

		panelAgregar.add(scrollPane);

		//		actualizarTabla(modeloTabla,categoriaControlador.listarCategorias());
		tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tabla_MouseClicked(evt);
			}
		});


		//-----------------------BOTONES--------------------------------
		// Botones
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(680, 290, 100, 30);
		btnAgregar.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		btnAgregar.setBackground(new Color(192, 192, 192));
		btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAgregar.setForeground(new Color(255, 255, 255));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnAgregarProductoActionPerformed(evt, panelAgregar);
			}
		});
		panelAgregar.add(btnAgregar);


		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panel.setBackground(new Color(164, 255, 255));
		panel.setBounds(50, 23, 862, 341);
		panelAgregar.add(panel);

	}


	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Agregar
	 * Dentro de este metodo quitamos el panel AgregarFacturaCliente y ponemos el panel de la clase EscogerProductoCliente
	 */	
	private void btnAgregarProductoActionPerformed(ActionEvent evt, JPanel panelAgregarFac) {
		try {
			panelEscogerProducto = new EscogerProductoCliente();
			panelEscogerProducto.setSize(960, 440);
			panelEscogerProducto.setLocation(0, 0);

			panelAgregarFac.removeAll();
			panelAgregarFac.add(panelEscogerProducto, BorderLayout.CENTER);
			panelAgregarFac.revalidate();
			panelAgregarFac.repaint();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Campos invalidos.");
		}
	}

	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Guardar
	 */	
	private void btnGuardarActionPerformed(ActionEvent evt, JPanel panelAgregar) {
		try {
			Double totalFactura = 0.0;
			for (int i = 0; i < detalleProductos.size(); i++) {
				totalFactura += detalleProductos.get(i).getPrecio();
			}
			
			int idCliente = Integer.parseInt((String)txtCliente.getSelectedItem());
			Cliente cliente = clienteControl.buscarClientes(idCliente,"", "", "", "", "", "", "", "", null).get(0);
			
			String mensaje = facClienteControlador.crearFacturaCliente(null, lblDireccion.getText(), lblTelefono.getText(), 
					lblCorreo.getText(), Timestamp.valueOf(LocalDateTime.now()), detalleProductos.size(), totalFactura, cliente, detalleProductos);
			
			System.out.println(mensaje);
			
			irPanelBuscar(evt, panelAgregar);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Campos invalidos.");
		}
	}

	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Salir
	 */	
	private void btnSalirActionPerformed(ActionEvent evt, JPanel panelAgregar) {
		try {
			irPanelBuscar(evt, panelAgregar);

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Error.");
		}
	}


	/*
	 * METODO PARA REALIZAR LA ACCIÓN VOLVER AL PANEL DE LA CLASE BuscarFacturaCliente
	 */	
	private void irPanelBuscar(ActionEvent evt, JPanel panelAgregar) {
		try {
			panelBuscar = new BuscarFacturaCliente();
			panelBuscar.setSize(960, 440);
			panelBuscar.setLocation(0, 0);

			panelAgregar.removeAll();
			panelAgregar.add(panelBuscar, BorderLayout.CENTER);
			panelAgregar.revalidate();
			panelAgregar.repaint();

			// Actualizar la tabla
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Campos invalidos.");
		}
	}
	
	private String[] obtenerClientes() {
		clienteControl = new ClienteControlador();
		List<Cliente> listaClientes = clienteControl.buscarClientes(null,"","","","","","","","",true);
		String[] listaIdsClientes = new String[listaClientes.size()+1];
		listaIdsClientes[0] = "";
		for(int i = 0; i<listaClientes.size(); i++) {
			listaIdsClientes[i+1] = listaClientes.get(i).getIdCliente().toString();
		}
		return listaIdsClientes;
	}

	/*
	 * METODO PARA ACTUALIZAR LOS REGISTROS DE LA TABLA
	 */
	private DefaultTableModel actualizarTabla(DefaultTableModel modeloTabla,List<DetalleFacturaCliente> detalleProductos) {
		// Limpiar la tabla
		int rowCount = modeloTabla.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			modeloTabla.removeRow(i); // eliminar cada fila una por una
		}

		// Agregar cada producto a la tabla
		for (DetalleFacturaCliente lista : detalleProductos) {
			Object[] fila = new Object[3];
			fila[0] = lista.getProducto().getNombre();
			fila[1] = lista.getCantidad();
			fila[2] = lista.getPrecio();
			modeloTabla.addRow(fila);
		}

		return modeloTabla;
	}

	/*
	 * METODO PARA REALIZAR LA ACCIÓN DE SELECCIONAR UN REGISTRO DE LA TABLA Y OBTENER ESOS VALORES
	 */
	private void tabla_MouseClicked(java.awt.event.MouseEvent evt) {
		int i = tablaProductos.getSelectedRow();

		TableModel modeloTabla = tablaProductos.getModel();

		nombreProducto = modeloTabla.getValueAt(i, 0).toString();

	}


}
