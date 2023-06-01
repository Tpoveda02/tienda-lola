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
import controlador.ProductoControlador;
import modelo.Categoria;
import modelo.DetalleFacturaProveedor;
import modelo.Producto;

public class EscogerProductoProveedor extends JPanel {

	//Objeto de la clase Controlador
	private ProductoControlador productoControlador;
	//Objeto de la clase AgregarFacturaCliente
	private AgregarFacturaProveedor panelAgregarFactura;
	//Objeto de la clase CategoriaControlador
	private CategoriaControlador categoriaControl;

	//-------------------Variables-----------------
	private List<Producto> listaProductos;
	private JLabel lblAgregarProducto;
	private JLabel lblListaProductos;
	private JLabel lblIdProducto;
	private JLabel lblNombre; 
	private JLabel lblCategoria; 
	private JTextField txtIdProducto; 
	private JTextField txtNombre; 
	private JComboBox txtCategoria; 
	private JTable tablaProductos;
	private JScrollPane scrollPane;
	private DefaultTableModel modeloTabla;
	private JTable tablaProductosSelec;
	private JScrollPane scrollPaneSelec;
	private DefaultTableModel modeloTablaSelec;
	private JButton btnGuardar; 
	private JButton btnSalir; 
	private JButton btnBuscar;
	private JButton btnEliminar;
	private JButton btnAgregar;

	private int idProducto;
	private String nombre;

	/*
	 * METODO CONSTRUCTOR
	 */
	public EscogerProductoProveedor() {
		productoControlador = new ProductoControlador();
		panelAgregarFactura = new AgregarFacturaProveedor();
		listaProductos = new ArrayList<Producto>();
		panelAgregarFactura.detalleProductos = new ArrayList<DetalleFacturaProveedor>();
		categoriaControl = new CategoriaControlador();
		inicializarComponentes();
	}

	/*
	 * METODO PARA INICIALIZAR Y DAR ESTILO A TODOS LOS COMPONETES VISUALES DE LA VISTA DE LA CLASE EscogerProductoCliente
	 */
	private void inicializarComponentes() {
		setLayout(null);
		JPanel panelAgregar = new JPanel();
		panelAgregar.setBounds(0, 0, 960, 440);
		add(panelAgregar);
		panelAgregar.setLayout(null);


		// Labels
		lblAgregarProducto = new JLabel("Agregar Producto");
		lblAgregarProducto.setForeground(new Color(135, 134, 133));
		lblAgregarProducto.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAgregarProducto.setBounds(60, 34, 150, 20);
		panelAgregar.add(lblAgregarProducto);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(70, 120, 120, 20);
		lblNombre.setForeground(new Color(135, 134, 133));
		panelAgregar.add(lblNombre);

		lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(300, 65, 120, 20);
		lblCategoria.setForeground(new Color(135, 134, 133));
		panelAgregar.add(lblCategoria);

		lblIdProducto = new JLabel("ID Producto:");
		lblIdProducto.setBounds(70, 65, 80, 20);
		lblIdProducto.setForeground(new Color(135, 134, 133));
		panelAgregar.add(lblIdProducto);


		// TextFields
		txtIdProducto = new JTextField();
		txtIdProducto.setBounds(70, 90, 200, 20);
		txtIdProducto.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelAgregar.add(txtIdProducto);

		txtNombre = new JTextField();
		txtNombre.setBounds(70, 145, 200, 20);
		txtNombre.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelAgregar.add(txtNombre);

		txtCategoria = new JComboBox();
		txtCategoria.setModel(new DefaultComboBoxModel(obtenerCategorias()));
		txtCategoria.setBounds(300, 90, 200, 20);
		txtCategoria.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelAgregar.add(txtCategoria);


		//-------------TABLA----------------
		modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("Identificador");
		modeloTabla.addColumn("Nombre");
		modeloTabla.addColumn("Categoría");

		tablaProductos = new JTable(actualizarTabla(modeloTabla,productoControlador.listarProductos()));
		scrollPane = new JScrollPane(tablaProductos);
		scrollPane.setBorder(new LineBorder(Color.WHITE, 0, true));
		scrollPane.setBounds(60, 180, 460, 160);
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

		tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tabla_MouseClicked(evt);
			}
		});

		// Botones
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(800, 390, 100, 30);
		btnGuardar.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		btnGuardar.setBackground(new Color(164, 255, 255));
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGuardar.setForeground(new Color(135, 134, 133));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnGuardarActionPerformed(evt, panelAgregar);
			}
		});
		panelAgregar.add(btnGuardar);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(680, 390, 100, 30);
		btnSalir.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		btnSalir.setBackground(new Color(164, 255, 255));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSalir.setForeground(new Color(135, 134, 133));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnSalirActionPerformed(evt, panelAgregar);
			}
		});
		panelAgregar.add(btnSalir);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnBuscar.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		btnBuscar.setBackground(Color.LIGHT_GRAY);
		btnBuscar.setBounds(400, 135, 100, 30);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnBuscarActionPerformed(evt);
			}
		});
		panelAgregar.add(btnBuscar);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAgregar.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		btnAgregar.setBackground(Color.LIGHT_GRAY);
		btnAgregar.setBounds(400, 360, 100, 30);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnAgregarrActionPerformed(evt, panelAgregar);
			}
		});
		panelAgregar.add(btnAgregar);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panel.setBackground(new Color(164, 255, 255));
		panel.setBounds(40, 23, 500, 389);
		panelAgregar.add(panel);



		// ---------------------------------- Panel de Productos --------------------------------------------------
		// Labels
		lblListaProductos = new JLabel("Lista de Productos");
		lblListaProductos.setForeground(new Color(135, 134, 133));
		lblListaProductos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblListaProductos.setBounds(585, 34, 216, 20);
		panelAgregar.add(lblListaProductos);

		// Tabla
		modeloTablaSelec = new DefaultTableModel();
		modeloTablaSelec.addColumn("Nombre");
		modeloTablaSelec.addColumn("Cantidad");
		modeloTablaSelec.addColumn("Total");

		//		tablaProductos = new JTable(actualizarTablaAgregar(modeloTabla,panelAgregarFactura.detalleProductos));

		if(panelAgregarFactura.detalleProductos == null) {
			tablaProductosSelec = new JTable(actualizarTablaAgregar(modeloTablaSelec,new ArrayList<DetalleFacturaProveedor>()));
		}else {
			tablaProductosSelec = new JTable(actualizarTablaAgregar(modeloTablaSelec,panelAgregarFactura.detalleProductos));

		}


		scrollPaneSelec = new JScrollPane(tablaProductosSelec);
		scrollPaneSelec.setBorder(new LineBorder(Color.WHITE, 0, true));
		scrollPaneSelec.setBounds(580, 70, 310, 240);
		//Color de la tabla
		tablaProductosSelec.getTableHeader().setBackground(new Color(182, 220, 255));
		tablaProductosSelec.getTableHeader().setForeground(new Color(135, 134, 133));
		tablaProductosSelec.setForeground(new Color(133, 111, 69));
		//Accion de quitar o poner lines en la tabla 
		tablaProductosSelec.setShowVerticalLines(false);
		tablaProductosSelec.setShowHorizontalLines(true);
		//Dar color a las lineas de la tabla
		tablaProductosSelec.setGridColor(new Color(182,220,255));
		//Instancia del objeto del borde de la tabla
		Border bordep = BorderFactory.createLineBorder(new Color(182,220,255));
		//Asigna el borde instanciado a la tabla
		tablaProductosSelec.setBorder(bordep);
		tablaProductosSelec.getTableHeader().setBorder(bordep);
		//Otras asignaciones de estetica de la tabla
		tablaProductosSelec.setRowHeight(30); // Establece la altura de las filas
		tablaProductosSelec.setIntercellSpacing(new Dimension(0, 0)); // Establece el espaciado entre celdas

		panelAgregar.add(scrollPaneSelec);

		//		actualizarTabla(modeloTabla,categoriaControlador.listarCategorias());
		tablaProductosSelec.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tablaAgregar_MouseClicked(evt);
			}
		});


		//-----------------------BOTONES--------------------------------
		// Botones
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(685, 330, 100, 30);
		btnEliminar.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		btnEliminar.setBackground(new Color(192, 192, 192));
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if(nombre.equals("")) {
					JOptionPane.showMessageDialog(null,"Seleccione un producto");
				}else {
					btnEliminarProductoActionPerformed(evt, panelAgregarFactura.detalleProductos);
					actualizarTablaAgregar(modeloTablaSelec,panelAgregarFactura.detalleProductos);
				}

			}
		});
		panelAgregar.add(btnEliminar);

		JPanel panelProductos = new JPanel();
		panelProductos.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelProductos.setBackground(new Color(164, 255, 255));
		panelProductos.setBounds(570, 23, 330, 350);
		panelAgregar.add(panelProductos);


	}
	
	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Guardar
	 */	
	private void btnGuardarActionPerformed(ActionEvent evt, JPanel panelAgregar) {
		try {
			irPanelAgregarFactura(evt, panelAgregar);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Campos invalidos.");
		}
	}

	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Agregar
	 */	
	private void btnAgregarrActionPerformed(ActionEvent evt, JPanel panelAgregar) {
		try {
			Producto producto = productoControlador.buscarProductos(idProducto,"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", null).get(0);
			int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Digite la cantidad del producto."));
			Double precio = cantidad * producto.getPrecioProveedor();
			panelAgregarFactura.detalleProductos.add(new DetalleFacturaProveedor(null, null, cantidad, precio, producto));
			actualizarTablaAgregar(modeloTablaSelec, panelAgregarFactura.detalleProductos);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Campos invalidos.");
		}
	}
	
	
	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Buscar
	 */
	private void btnBuscarActionPerformed(ActionEvent evt) {
		try {
			Integer idProductob = null;
			if(!txtIdProducto.getText().equals(""))
				idProductob = Integer.parseInt(txtIdProducto.getText());
			String nombreb = txtNombre.getText();
			
			Categoria categoria = categoriaControl.buscarCategorias(null, (String)txtCategoria.getSelectedItem(), "",null).get(0);

			// Buscar el cliente en la base de datos
			List<Producto> productos = productoControlador.buscarProductos(idProductob,nombreb,"","","","","","","","","","","","","",
					categoria.getIdCategoria().toString(), null);
			if (productos != null) {
				actualizarTabla(modeloTabla,productos);
			} else {
				JOptionPane.showMessageDialog(this, "Producto no encontrado.");
			}


		} catch (NumberFormatException ex) {
			System.err.println(ex.getMessage());
			JOptionPane.showMessageDialog(this, "Campos invalidos");
		}

	}
	
	
	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Eliminar
	 */
	private void btnEliminarProductoActionPerformed(ActionEvent evt, List<DetalleFacturaProveedor> detalleProductos) {
		try {
			// Eliminar el registro de la lista
			for (int i = 0; i < detalleProductos.size(); i++) {
				if(detalleProductos.get(i).getProducto().getNombre().equals(nombre)) {
					detalleProductos.remove(i);
				}

			}
			actualizarTablaAgregar(modeloTablaSelec,detalleProductos);

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Error.");
		}
	}
	
	
	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Salir
	 */	
	private void btnSalirActionPerformed(ActionEvent evt, JPanel panelAgregar) {
		try {
			panelAgregarFactura.detalleProductos = new ArrayList<>();
			irPanelAgregarFactura(evt, panelAgregar);

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Error.");
		}
	}
	
	
	/*
	 * METODO PARA REALIZAR LA ACCIÓN VOLVER AL PANEL DE LA CLASE AgregarFacturaProveedor
	 */	
	private void irPanelAgregarFactura(ActionEvent evt, JPanel panelAgregar) {
		try {
			panelAgregarFactura = new AgregarFacturaProveedor();
			panelAgregarFactura.setSize(960, 440);
			panelAgregarFactura.setLocation(0, 0);

			panelAgregar.removeAll();
			panelAgregar.add(panelAgregarFactura, BorderLayout.CENTER);
			panelAgregar.revalidate();
			panelAgregar.repaint();

			// Actualizar la tabla
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Campos invalidos.");
		}
	}

	
	/*
	 * METODO PARA ACTUALIZAR LOS REGISTROS DE LA TABLA
	 */
	private DefaultTableModel actualizarTabla(DefaultTableModel modeloTabla,List<Producto> listaProductos) {
		// Limpiar la tabla
		int rowCount = modeloTabla.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			modeloTabla.removeRow(i); // eliminar cada fila una por una
		}

		// Agregar cada cliente a la tabla
		for (Producto producto : listaProductos) {
			Object[] fila = new Object[3];
			fila[0] = producto.getIdProducto();
			fila[1] = producto.getNombre();		
			fila[2] = producto.getCategoria().getNombre();
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

		idProducto = Integer.parseInt(modeloTabla.getValueAt(i, 0).toString());

	}
	
	
	private String[] obtenerCategorias() {
		categoriaControl = new CategoriaControlador();
		List<Categoria> listaCategorias = categoriaControl.buscarCategorias(null,"","",true);
		String[] listaNombresCat = new String[listaCategorias.size()+1];
		listaNombresCat[0] = "";
		for(int i = 0; i<listaCategorias.size(); i++) {
			listaNombresCat[i+1] = listaCategorias.get(i).getNombre();
		}
		return listaNombresCat;
	}
	
	
	/*
	 * METODO PARA ACTUALIZAR LOS REGISTROS DE LA TABLA
	 */
	private DefaultTableModel actualizarTablaAgregar(DefaultTableModel modeloTabla,List<DetalleFacturaProveedor> detalleProductos) {
		// Limpiar la tabla
		int rowCount = modeloTabla.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			modeloTabla.removeRow(i); // eliminar cada fila una por una
		}

		// Agregar cada cliente a la tabla
		for (DetalleFacturaProveedor lista : detalleProductos) {
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
	private void tablaAgregar_MouseClicked(java.awt.event.MouseEvent evt) {
		int i = tablaProductosSelec.getSelectedRow();

		TableModel modeloTabla = tablaProductosSelec.getModel();

		nombre = modeloTabla.getValueAt(i, 0).toString();

		//		idProducto = Integer.parseInt(modeloTabla.getValueAt(i, 0).toString());

	}

}
