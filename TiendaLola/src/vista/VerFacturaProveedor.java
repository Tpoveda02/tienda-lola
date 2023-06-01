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

import modelo.DetalleFacturaCliente;
import modelo.DetalleFacturaProveedor;

public class VerFacturaProveedor extends JPanel {

	//Objeto de la clase BuscarClientes
	private BuscarFacturaProveedor panelBuscar;


	//-------------------Variables-----------------
	private List<DetalleFacturaProveedor> detalleProductos;
	private JLabel lblRegistroCategoria;
	private JLabel lblListaProductos;
	private JLabel lblVistaFactura;
	private JLabel lblIdFactura;
	private JLabel lblNombreCliente; 
	private JLabel lblIdCliente; 
	private JLabel lblFecha;
	private JLabel lblCantidadProductos;
	private JTextField txtIdFactura; 
	private JTextField txtNombreCliente; 
	private JTextField txtIdCliente; 
	private JTextField txtFecha;
	private JTextField txtCantidadProductos;
	private JTable tablaProductos;
	private JScrollPane scrollPane;
	private DefaultTableModel modeloTabla;
	private JButton btnSalir; 

	private JLabel lblIdTotalFac;


	/*
	 * METODO CONSTRUCTOR
	 */
	public VerFacturaProveedor() {
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
		lblVistaFactura = new JLabel("Detalle de Factura Cliente");
		lblVistaFactura.setForeground(new Color(135, 134, 133));
		lblVistaFactura.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblVistaFactura.setBounds(65, 34, 216, 20);
		panelAgregar.add(lblVistaFactura);

		lblNombreCliente = new JLabel("Nombre del Cliente:");
		lblNombreCliente.setBounds(80, 135, 154, 20);
		lblNombreCliente.setForeground(new Color(135, 134, 133));
		panelAgregar.add(lblNombreCliente);

		lblIdCliente = new JLabel("ID Cliente:");
		lblIdCliente.setBounds(323, 81, 120, 20);
		lblIdCliente.setForeground(new Color(135, 134, 133));
		panelAgregar.add(lblIdCliente);

		lblIdFactura = new JLabel("ID Factura:");
		lblIdFactura.setBounds(80, 81, 80, 20);
		lblIdFactura.setForeground(new Color(135, 134, 133));
		panelAgregar.add(lblIdFactura);

		lblFecha = new JLabel("Fecha:");
		lblFecha.setForeground(new Color(135, 134, 133));
		lblFecha.setBounds(80, 189, 80, 20);
		panelAgregar.add(lblFecha);

		lblCantidadProductos = new JLabel("Catidad de Productos: ");
		lblCantidadProductos.setForeground(new Color(135, 134, 133));
		lblCantidadProductos.setBounds(323, 189, 154, 20);
		panelAgregar.add(lblCantidadProductos);

		lblIdTotalFac = new JLabel("Total Factura: $");
		lblIdTotalFac.setForeground(new Color(135, 134, 133));
		lblIdTotalFac.setBounds(219, 265, 154, 20);
		lblIdTotalFac.setText("Total Factura: $" + panelBuscar.valorTotal + ".");
		panelAgregar.add(lblIdTotalFac);

		// TextFields
		txtIdFactura = new JTextField();
		txtIdFactura.setBounds(80, 104, 200, 20);
		txtIdFactura.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtIdFactura.setText(panelBuscar.idFactura + "");
		txtIdFactura.setEditable(false);
		panelAgregar.add(txtIdFactura);

		txtNombreCliente = new JTextField();
		txtNombreCliente.setBounds(80, 158, 443, 20);
		txtNombreCliente.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtNombreCliente.setText(panelBuscar.nombreCliente + "");
		txtNombreCliente.setEditable(false);
		panelAgregar.add(txtNombreCliente);

		txtIdCliente = new JTextField();
		txtIdCliente.setBounds(323, 104, 200, 20);
		txtIdCliente.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtIdCliente.setText(panelBuscar.idCliente + "");
		txtIdCliente.setEditable(false);
		panelAgregar.add(txtIdCliente);

		txtCantidadProductos = new JTextField();
		txtCantidadProductos.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtCantidadProductos.setBounds(323, 212, 200, 20);
		txtCantidadProductos.setText(panelBuscar.numeroProductos + "");
		txtCantidadProductos.setEditable(false);
		panelAgregar.add(txtCantidadProductos);

		txtFecha = new JTextField();
		txtFecha.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtFecha.setBounds(80, 212, 200, 20);
		txtFecha.setText(panelBuscar.fecha + "");
		txtFecha.setEditable(false);
		panelAgregar.add(txtFecha);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(420, 320, 100, 30);
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
		lblListaProductos = new JLabel("Lista de Productos");
		lblListaProductos.setForeground(new Color(135, 134, 133));
		lblListaProductos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblListaProductos.setBounds(585, 34, 216, 20);
		panelAgregar.add(lblListaProductos);


		// Tabla
		modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("Nombre");
		modeloTabla.addColumn("Cantidad");
		modeloTabla.addColumn("Total");

		//		detalleProductos = new ArrayList<DetalleFacturaCliente>();
		if(detalleProductos == null) {
			tablaProductos = new JTable(actualizarTabla(modeloTabla,new ArrayList<DetalleFacturaProveedor>()));
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
		


		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panel.setBackground(new Color(164, 255, 255));
		panel.setBounds(50, 23, 862, 341);
		panelAgregar.add(panel);

	}

	
	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Salir
	 */	
	private void btnSalirActionPerformed(ActionEvent evt, JPanel panelAgregar) {
		try {
			panelBuscar = new BuscarFacturaProveedor();
			panelBuscar.setSize(960, 440);
			panelBuscar.setLocation(0, 0);

			panelAgregar.removeAll();
			panelAgregar.add(panelBuscar, BorderLayout.CENTER);
			panelAgregar.revalidate();
			panelAgregar.repaint();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Error.");
		}
	}
	
	
	/*
	 * METODO PARA ACTUALIZAR LOS REGISTROS DE LA TABLA
	 */
	private DefaultTableModel actualizarTabla(DefaultTableModel modeloTabla,List<DetalleFacturaProveedor> detalleProductos) {
		// Limpiar la tabla
		int rowCount = modeloTabla.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			modeloTabla.removeRow(i); // eliminar cada fila una por una
		}

		// Agregar cada producto a la tabla
		for (DetalleFacturaProveedor lista : detalleProductos) {
			Object[] fila = new Object[3];
			fila[0] = lista.getProducto().getNombre();
			fila[1] = lista.getCantidad();
			fila[2] = lista.getPrecio();
			modeloTabla.addRow(fila);
		}

		return modeloTabla;
	}
}
