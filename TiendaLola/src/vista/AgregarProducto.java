package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.desktop.SystemEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controlador.CategoriaControlador;
import controlador.Controlador;
import controlador.ProductoControlador;
import modelo.Categoria;
import modelo.Cliente;
import modelo.Producto;
import com.toedter.calendar.JDateChooser;

public class AgregarProducto extends JPanel {

	//Objetod e la clase Controlador
		private ProductoControlador productoControlador;
		//Objeto de la clase BuscarClientes
		private BuscarProducto panelBuscar;
		//Objeto de la clase CategoriaControlador
		private CategoriaControlador categoriaControl;
		
		
		//-------------------Variables-----------------
		private List<Producto> listaProductos;
		private JLabel lblIdProducto;
		private JLabel lblRegistroProducto;
		private JLabel lblNombreProducto;
		private JLabel lblTipoContenido; 
		private JLabel lblContenidoNeto; 
		private JLabel lblEmpaqueGeneral; 
		private JLabel lblEmpaque;
		private JLabel lblPrecioProveedor; 
		private JLabel lblPorcentajeIva; 
		private JLabel lblPrecioSinIva; 
		private JLabel lblFechaVencimiento; 
		private JLabel lblCantidad; 
		private JLabel lblRecomendaciones;
		private JLabel lblDescripcion;
		private JTextField txtNombreProducto; 
		private JTextField txtIdProducto; 
		private JComboBox txtTipoContenido;
		private JTextField txtContenidoNeto; 
		private JTextField txtEmpaqueGeneral; 
		private JTextField txtEmpaque;
		private JTextField txtPrecioProveedor; 
		private JTextField txtPorcentajeIva;
		private JTextField txtPrecioSinIva; 
		private JTextField txtFechaVencimiento; 
		private JTextField txtCantidad;
		private JComboBox txtCategoria;
		private JTextField txtRecomendaciones;
		private JTextField txtDescripcion;
		private JDateChooser dateFechaVencimiento;
		private DefaultTableModel modeloTabla;
		private JButton btnGuardar; 
		private JButton btnSalir; 


		
		
		/*
		 * METODO CONSTRUCTOR
		 */
	public AgregarProducto() {
		productoControlador = new ProductoControlador();
		inicializarComponentes();
		listaProductos = new ArrayList<Producto>();
		categoriaControl = new CategoriaControlador();
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
		lblRegistroProducto = new JLabel("Registro del Producto");
		lblRegistroProducto.setForeground(new Color(135, 134, 133));
		lblRegistroProducto.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRegistroProducto.setBounds(124, 34, 150, 20);
		panelAgregar.add(lblRegistroProducto);

		lblTipoContenido = new JLabel("Tipo de Contenido:");
		lblTipoContenido.setBounds(630, 81, 120, 20);
		lblTipoContenido.setForeground(new Color(135, 134, 133));
		panelAgregar.add(lblTipoContenido);
		
		lblIdProducto = new JLabel("ID Producto:");
		lblIdProducto.setBounds(150, 81, 120, 20);
		lblIdProducto.setForeground(new Color(135, 134, 133));
		panelAgregar.add(lblIdProducto);

		lblContenidoNeto = new JLabel("Contenido Neto:");
		lblContenidoNeto.setBounds(150, 135, 120, 20);
		lblContenidoNeto.setForeground(new Color(135, 134, 133));
		panelAgregar.add(lblContenidoNeto);

		lblEmpaqueGeneral = new JLabel("Empaque General:");
		lblEmpaqueGeneral.setBounds(393, 135, 120, 20);
		lblEmpaqueGeneral.setForeground(new Color(135, 134, 133));
		panelAgregar.add(lblEmpaqueGeneral);

		lblEmpaque = new JLabel("Empaque:");
		lblEmpaque.setBounds(630, 135, 120, 20);
		lblEmpaque.setForeground(new Color(135, 134, 133));
		panelAgregar.add(lblEmpaque);

		lblPrecioProveedor = new JLabel("Precio Proveedor:");
		lblPrecioProveedor.setBounds(150, 191, 120, 20);
		lblPrecioProveedor.setForeground(new Color(135, 134, 133));
		panelAgregar.add(lblPrecioProveedor);

		lblPorcentajeIva = new JLabel("Porcentaje IVA:");
		lblPorcentajeIva.setBounds(393, 191, 105, 20);
		lblPorcentajeIva.setForeground(new Color(135, 134, 133));
		panelAgregar.add(lblPorcentajeIva);

		lblPrecioSinIva = new JLabel("Precio sin IVA:");
		lblPrecioSinIva.setBounds(630, 191, 120, 20);
		lblPrecioSinIva.setForeground(new Color(135, 134, 133));
		panelAgregar.add(lblPrecioSinIva);
		
		lblFechaVencimiento = new JLabel("Fecha Vencimiento:");
		lblFechaVencimiento.setBounds(150, 243, 166, 20);
		lblFechaVencimiento.setForeground(new Color(135, 134, 133));
		panelAgregar.add(lblFechaVencimiento);
		
		lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(393, 243, 80, 20);
		lblCantidad.setForeground(new Color(135, 134, 133));
		panelAgregar.add(lblCantidad);

		lblRecomendaciones = new JLabel("Recomendaciones:");
		lblRecomendaciones.setBounds(500, 295, 120, 20);
		lblRecomendaciones.setForeground(new Color(135, 134, 133));
		panelAgregar.add(lblRecomendaciones);
		
		lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setBounds(150, 295, 120, 20);
		lblDescripcion.setForeground(new Color(135, 134, 133));
		panelAgregar.add(lblDescripcion);
		
		lblNombreProducto = new JLabel("Nombre:");
		lblNombreProducto.setBounds(393, 81, 80, 20);
		lblNombreProducto.setForeground(new Color(135, 134, 133));
		panelAgregar.add(lblNombreProducto);


		// TextFields
		txtNombreProducto = new JTextField();
		txtNombreProducto.setBounds(393, 104, 200, 20);
		txtNombreProducto.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelAgregar.add(txtNombreProducto);

		txtIdProducto = new JTextField();
		txtIdProducto.setBounds(150, 104, 200, 20);
		txtIdProducto.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelAgregar.add(txtIdProducto);

		txtTipoContenido = new JComboBox();
		txtTipoContenido.setModel(new DefaultComboBoxModel(new String[] {"", "gr", "ml"}));
		txtTipoContenido.setBounds(630, 104, 200, 20);
		txtTipoContenido.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelAgregar.add(txtTipoContenido);

		txtContenidoNeto = new JTextField();
		txtContenidoNeto.setBounds(150, 158, 200, 20);
		txtContenidoNeto.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelAgregar.add(txtContenidoNeto);

		txtEmpaqueGeneral = new JTextField();
		txtEmpaqueGeneral.setBounds(393, 158, 200, 20);
		txtEmpaqueGeneral.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelAgregar.add(txtEmpaqueGeneral);

		txtEmpaque = new JTextField();
		txtEmpaque.setBounds(630, 158, 200, 20);
		txtEmpaque.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelAgregar.add(txtEmpaque);

		txtPrecioProveedor = new JTextField();
		txtPrecioProveedor.setBounds(150, 212, 200, 20);
		txtPrecioProveedor.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelAgregar.add(txtPrecioProveedor);

		txtPorcentajeIva = new JTextField();
		txtPorcentajeIva.setBounds(393, 212, 200, 20);
		txtPorcentajeIva.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelAgregar.add(txtPorcentajeIva);

		txtPrecioSinIva = new JTextField();
		txtPrecioSinIva.setBounds(630, 212, 200, 20);
		txtPrecioSinIva.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelAgregar.add(txtPrecioSinIva);
		
//		txtFechaVencimiento = new JTextField();
//		txtFechaVencimiento.setBounds(150, 264, 200, 20);
//		txtFechaVencimiento.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
//		panelAgregar.add(txtFechaVencimiento);
		
		dateFechaVencimiento = new JDateChooser();
		dateFechaVencimiento.setBounds(150, 264, 200, 20);
		dateFechaVencimiento.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelAgregar.add(dateFechaVencimiento);
		
		txtCategoria = new JComboBox();
		txtCategoria.setModel(new DefaultComboBoxModel(obtenerCategorias()));
		txtCategoria.setBounds(630, 264, 200, 20);
		txtCategoria.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelAgregar.add(txtCategoria);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(393, 264, 200, 20);
		txtCantidad.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelAgregar.add(txtCantidad);

		txtRecomendaciones = new JTextField();
		txtRecomendaciones.setBounds(500, 316, 330, 45);
		txtRecomendaciones.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelAgregar.add(txtRecomendaciones);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(150, 316, 335, 45);
		txtDescripcion.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelAgregar.add(txtDescripcion);

		// Botones
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(485, 372, 100, 30);
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
		btnSalir.setBounds(360, 372, 100, 30);
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
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panel.setBackground(new Color(164, 255, 255));
		panel.setBounds(82, 23, 792, 389);
		panelAgregar.add(panel);
		
		

	}

	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Guardar
	 */	
	private void btnGuardarActionPerformed(ActionEvent evt, JPanel panelAgregar) {
		try {
			int idProducto = Integer.parseInt(txtIdProducto.getText());
			String nombre = txtNombreProducto.getText();
			String tipoContenidoNeto = (String) txtTipoContenido.getSelectedItem();
			Integer contenidoNeto = new Integer(txtContenidoNeto.getText());
			String empaqueGeneral = txtEmpaqueGeneral.getText();
			String empaque = txtEmpaque.getText();
			String descripcion = txtDescripcion.getText();
			String recomendaciones = txtRecomendaciones.getText();
			Integer precioProveedor = new Integer(txtPrecioProveedor.getText());
			Integer porcentajeIva = new Integer(txtPorcentajeIva.getText());
			Integer precioSinIva = new Integer(txtPrecioSinIva.getText());
			Date fechaVencimiento = new Date(dateFechaVencimiento.getDate().getTime());
			int cantidad = Integer.parseInt(txtCantidad.getText());
			
			Categoria categoria = categoriaControl.buscarCategorias(null, (String)txtCategoria.getSelectedItem(), "",null).get(0);
			
			// Agregar el producto a la base de datos
			String mensaje = "";
			
			mensaje = productoControlador.crearProducto(idProducto, nombre, tipoContenidoNeto, contenidoNeto, new Integer(0), 
					empaqueGeneral, empaque, descripcion, recomendaciones, precioProveedor, porcentajeIva, precioSinIva, 
					new Integer(0), fechaVencimiento, cantidad, categoria,true);
			JOptionPane.showMessageDialog(this,mensaje);
			
			//Volver al panel anterior
			if(mensaje.equals("Producto creado.")) {
				irPanelBuscar(evt, panelAgregar);
			}

			
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "El ID producto debe ser un número entero.");
		}
	}

	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Salir
	 */	
	private void btnSalirActionPerformed(ActionEvent evt, JPanel panelAgregar) {
		try {
			irPanelBuscar(evt, panelAgregar);
			
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "El ID producto debe ser un número entero.");
		}
	}
	
	/*
	 * METODO PARA REALIZAR LA ACCIÓN VOLVER AL PANEL DE LA CLASE BuscarClientes
	 */	
	private void irPanelBuscar(ActionEvent evt, JPanel panelAgregar) {
		try {
			panelBuscar = new BuscarProducto();
			panelBuscar.setSize(960, 440);
			panelBuscar.setLocation(0, 0);

			panelAgregar.removeAll();
			panelAgregar.add(panelBuscar, BorderLayout.CENTER);
			panelAgregar.revalidate();
			panelAgregar.repaint();

			// Actualizar la tabla
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "El ID producto debe ser un número entero.");
		}
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
}
