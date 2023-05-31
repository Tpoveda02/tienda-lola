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
import controlador.ProductoControlador;
import modelo.Cliente;
import modelo.Producto;

public class BuscarProducto extends JPanel {

	//Objeto de la clase Controlador
	private ProductoControlador productoControlador;
	//Objeto de la clase PanelAgregar
	private AgregarProducto panelAgregar;
	//Objeto de la clase panelActualizar
	private ActualizarProducto panelActualizar;

	//--------------------Variables-------------------
	private List<Producto> listaProductos;
	private JLabel lblIdProducto;
	private JLabel lblCategoria; 
	private JLabel lblFechaCaducidad; 
	private JLabel lblPrecio; 
	private JLabel lblCantidad;
	private JTextField txtIdProducto; 
	private JTextField txtCategoria;
	private JTextField txtFechaCaducidad; 
	private JTextField txtPrecio; 
	private JTextField txtCantidad;
	private JTable tablaProductos;
	private JScrollPane scrollPane;
	private DefaultTableModel modeloTabla;
	private JButton btnAgregar; 
	private JButton btnActualizar; 
	private JButton btnInactivar;
	private JButton btnActivar;
	private JButton btnLimpiar; 
	private JButton btnBuscar;

	public static int idProducto = 0;		//Validar que no ha seleccionado ningun cliente
	public static String nombre;
	public static String tipoContenidoNeto;
	public static String contenidoNeto;
	public static String valorContenido;
	public static String precioProveedor;
	public static String precioVenta;
	public static String fechaVencimiento;
	public static String cantidad;
	public static String categoria;


	/*
	 * METODO CONSTRUCTOR
	 */
	public BuscarProducto() {
		productoControlador = new ProductoControlador();
		inicializarComponentes();
		listaProductos = new ArrayList<Producto>();
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
		lblIdProducto = new JLabel("Identificador:");
		lblIdProducto.setBounds(20, 0, 80, 20);
		lblIdProducto.setForeground(new Color(135, 134, 133));
		panelBuscar.add(lblIdProducto);

		lblCategoria = new JLabel("Categoría:");
		lblCategoria.setBounds(285, 0, 120, 20);
		lblCategoria.setForeground(new Color(135, 134, 133));
		panelBuscar.add(lblCategoria);

		lblFechaCaducidad = new JLabel("Fecha Caducidad:");
		lblFechaCaducidad.setBounds(565, 0, 120, 20);
		lblFechaCaducidad.setForeground(new Color(135, 134, 133));
		panelBuscar.add(lblFechaCaducidad);

		lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(20, 49, 120, 20);
		lblPrecio.setForeground(new Color(135, 134, 133));
		panelBuscar.add(lblPrecio);

		lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(285, 49, 120, 20);
		lblCantidad.setForeground(new Color(135, 134, 133));
		panelBuscar.add(lblCantidad);


		// TextFields
		txtIdProducto = new JTextField();
		txtIdProducto.setBounds(20, 23, 209, 20);
		txtIdProducto.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtIdProducto.setText("");
		panelBuscar.add(txtIdProducto);


		txtCategoria = new JTextField();
		txtCategoria.setBounds(285, 23, 209, 20);
		txtCategoria.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtIdProducto.setText("");
		panelBuscar.add(txtCategoria);

		txtFechaCaducidad = new JTextField();
		txtFechaCaducidad.setBounds(565, 23, 209, 20);
		txtFechaCaducidad.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtFechaCaducidad.setText("");
		panelBuscar.add(txtFechaCaducidad);

		txtPrecio = new JTextField();
		txtPrecio.setBounds(20, 70, 209, 20);
		txtPrecio.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtPrecio.setText("");
		panelBuscar.add(txtPrecio);

		txtCantidad = new JTextField();
		txtCantidad.setBounds(285, 70, 209, 20);
		txtCantidad.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtCantidad.setText("");
		panelBuscar.add(txtCantidad);


		// Tabla
		modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("Identificador");
		modeloTabla.addColumn("Fecha caducidad");
		modeloTabla.addColumn("Nombre");
		modeloTabla.addColumn("Tipo contenido");
		modeloTabla.addColumn("Contenido");
		modeloTabla.addColumn("Valor contenido");
		modeloTabla.addColumn("Categoría");
		modeloTabla.addColumn("Precio Proveedor");
		modeloTabla.addColumn("Precio");
		modeloTabla.addColumn("Cantidad");

		tablaProductos = new JTable(actualizarTabla(modeloTabla,productoControlador.listarProductos()));
		scrollPane = new JScrollPane(tablaProductos);
		scrollPane.setBorder(new LineBorder(Color.WHITE, 0, true));
		scrollPane.setBounds(10, 106, 940, 262);
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

		panelBuscar.add(scrollPane);

		tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
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
				if(idProducto != 0) {
					btnActualizarActionPerformed(evt, panelBuscar);
					actualizarTabla(modeloTabla,productoControlador.listarProductos());
				}else {
					JOptionPane.showMessageDialog(null,"Seleccione un Producto");
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
				if(idProducto != 0) {
					btnInactivarActionPerformed(evt);
					actualizarTabla(modeloTabla,productoControlador.listarProductos());
				}else {
					JOptionPane.showMessageDialog(null,"Seleccione un Producto");
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
				if(idProducto != 0) {
					btnActivarActionPerformed(evt);
					actualizarTabla(modeloTabla,productoControlador.listarProductos());
				}else {
					JOptionPane.showMessageDialog(null,"Seleccione un Producto");
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
				actualizarTabla(modeloTabla,productoControlador.listarProductos());
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
			panelAgregar = new AgregarProducto();
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
			panelActualizar = new ActualizarProducto();
			panelActualizar.setSize(960, 440);
			panelActualizar.setLocation(0, 0);

			panelBuscar.removeAll();
			panelBuscar.add(panelActualizar, BorderLayout.CENTER);
			panelBuscar.revalidate();
			panelBuscar.repaint();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "El ID Producto debe ser un número entero.");
		}
	}

	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Inactivar
	 */
	private void btnInactivarActionPerformed(ActionEvent evt) {
		try {
			// Eliminar el producto de la base de datos
			JOptionPane.showMessageDialog(this,productoControlador.eliminarProducto(idProducto));
			actualizarTabla(modeloTabla,productoControlador.listarProductos());

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "El ID Producto debe ser un número entero.");
		}
	}
	
	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Activar
	 */
	private void btnActivarActionPerformed(ActionEvent evt) {
		try {
			// Eliminar el producto de la base de datos
//			JOptionPane.showMessageDialog(this,productoControlador.eliminarProducto(idProducto));
//			actualizarTabla(modeloTabla,productoControlador.listarProductos());

		} catch (NumberFormatException ex) {
//			JOptionPane.showMessageDialog(this, "El ID Producto debe ser un número entero.");
		}
	}

	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Limpiar
	 */
	private void btnLimpiarActionPerformed(ActionEvent evt) {
		txtIdProducto.setText("");
		txtCategoria.setText("");
		txtFechaCaducidad.setText("");
		txtPrecio.setText("");
		txtCantidad.setText("");
	}

	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Buscar
	 */
	private void btnBuscarActionPerformed(ActionEvent evt) {
		try {
			Integer idProductob = null;
			if(!txtIdProducto.getText().equals(""))
				idProductob = Integer.parseInt(txtIdProducto.getText());
			String categoriab = txtCategoria.getText();
			String fechaCaducidadb = txtFechaCaducidad.getText();
			String preciob = txtPrecio.getText();
			String cantidadb = txtCantidad.getText();

			// Buscar el cliente en la base de datos
			List<Producto> productos = productoControlador.buscarProductos(idProductob,"","","","","","","","","","","",preciob,fechaCaducidadb,cantidadb,categoriab);
			if (productos != null) {
				actualizarTabla(modeloTabla,productos);
			} else {
				JOptionPane.showMessageDialog(this, "Producto no encontrado.");
			}


		} catch (NumberFormatException ex) {
			System.err.println(ex.getMessage());
			JOptionPane.showMessageDialog(this, "El ID Producto debe ser un número entero.");
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
			Object[] fila = new Object[9];
			fila[0] = producto.getIdProducto();
			fila[1] = producto.getFechaVencimiento();
			fila[2] = producto.getNombre();
			fila[3] = producto.getTipoContenidoNeto();
			fila[4] = producto.getContenidoNeto();
			fila[5] = producto.getValorContenido();
			fila[6] = producto.getCategoria();
			fila[7] = producto.getPrecioProveedor();
			fila[8] = producto.getPrecioVenta();
			fila[9] = producto.getCantidad();			
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
		fechaVencimiento = modeloTabla.getValueAt(i, 1).toString();
		nombre = modeloTabla.getValueAt(i, 2).toString();
		tipoContenidoNeto = modeloTabla.getValueAt(i, 3).toString();
		contenidoNeto = modeloTabla.getValueAt(i, 4).toString();
		valorContenido = modeloTabla.getValueAt(i, 5).toString();
		categoria = modeloTabla.getValueAt(i, 6).toString();
		precioProveedor = modeloTabla.getValueAt(i, 7).toString();
		precioVenta = modeloTabla.getValueAt(i, 8).toString();
		cantidad = modeloTabla.getValueAt(i, 9).toString();

	}
}
