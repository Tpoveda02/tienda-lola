package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import controlador.CategoriaControlador;
import modelo.Categoria;

public class AgregarCategoria extends JPanel {

	//Objetod e la clase Controlador
	private CategoriaControlador categoriaControlador;
	//Objeto de la clase BuscarClientes
	private BuscarCategoria panelBuscar;

	//-------------------Variables-----------------
	private List<Categoria> listaCategorias;
	private JLabel lblRegistroCategoria;
	private JLabel lblIdCategoria;
	private JLabel lblDescripcion; 
	private JLabel lblNombreCategoria; 
	private JLabel lblNewLabel;
	private JTextField txtIdCategoria; 
	private JTextField txtDescripcion; 
	private JTextField txtNombreCategoria; 
	private DefaultTableModel modeloTabla;
	private JButton btnGuardar; 
	private JButton btnSalir; 


	/*
	 * METODO CONSTRUCTOR
	 */
	public AgregarCategoria() {
		categoriaControlador = new CategoriaControlador();
		inicializarComponentes();
		listaCategorias = new ArrayList<Categoria>();
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
		lblRegistroCategoria = new JLabel("Registro de Categoría");
		lblRegistroCategoria.setForeground(new Color(135, 134, 133));
		lblRegistroCategoria.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRegistroCategoria.setBounds(124, 34, 150, 20);
		panelAgregar.add(lblRegistroCategoria);

		lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setBounds(393, 81, 120, 20);
		lblDescripcion.setForeground(new Color(135, 134, 133));
		panelAgregar.add(lblDescripcion);

		lblNombreCategoria = new JLabel("Nombre:");
		lblNombreCategoria.setBounds(150, 81, 80, 20);
		lblNombreCategoria.setForeground(new Color(135, 134, 133));
		panelAgregar.add(lblNombreCategoria);


		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AgregarCategoria.class.getResource("/media/ImagenCategoria.png")));
		lblNewLabel.setBounds(640, 100, 200, 190);
		panelAgregar.add(lblNewLabel);


		// TextFields

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(393, 104, 200, 20);
		txtDescripcion.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelAgregar.add(txtDescripcion);

		txtNombreCategoria = new JTextField();
		txtNombreCategoria.setBounds(150, 104, 200, 20);
		txtNombreCategoria.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelAgregar.add(txtNombreCategoria);

		// Botones
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(485, 160, 100, 30);
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
		btnSalir.setBounds(360, 160, 100, 30);
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
			String descripcion = txtDescripcion.getText();
			String nombre = txtNombreCategoria.getText();
			// Agregar la categoria a la base de datos
			String mensaje = "";
			mensaje = categoriaControlador.crearCategoria(null, nombre, descripcion, true);
			JOptionPane.showMessageDialog(this,mensaje);
			
			//Volver al panel anterior
			if(mensaje.equals("Categoría creada.")) {
				irPanelBuscar(evt, panelAgregar);				
			}

			
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "El ID Categoria debe ser un número entero.");
		}
	}

	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Salir
	 */	
	private void btnSalirActionPerformed(ActionEvent evt, JPanel panelAgregar) {
		try {
			irPanelBuscar(evt, panelAgregar);
			
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "El ID Categoria debe ser un número entero.");
		}
	}
	
	/*
	 * METODO PARA REALIZAR LA ACCIÓN VOLVER AL PANEL DE LA CLASE BuscarClientes
	 */	
	private void irPanelBuscar(ActionEvent evt, JPanel panelAgregar) {
		try {
			panelBuscar = new BuscarCategoria();
			panelBuscar.setSize(960, 440);
			panelBuscar.setLocation(0, 0);

			panelAgregar.removeAll();
			panelAgregar.add(panelBuscar, BorderLayout.CENTER);
			panelAgregar.revalidate();
			panelAgregar.repaint();

			// Actualizar la tabla
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "El ID Categoria debe ser un número entero.");
		}
	}

}
