package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
import modelo.Categoria;

public class ActualizarCategoria extends JPanel {

	//Objeto de la clase Controlador
	private CategoriaControlador categoriaControlador;
	//Objeto de la clase BuscarClientes
	private BuscarCategoria panelBuscar;

	//--------------------Variables--------------------
	private List<Categoria> listaCategorias;
	private JLabel lblDatosCategoria;
	private JLabel lblIdCategoria;
	private JLabel lblDescripcion; 
	private JLabel lblNombreCategoria; 
	private JLabel lblNewLabel;
	private JTextField txtIdCategoria; 
	private JTextField txtDescripcion; 
	private JTextField txtNombreCategoria; 
	private DefaultTableModel modeloTabla;
	private JButton btnActualizar; 
	private JButton btnSalir; 

	/*
	 * METODO CONSTRUCTOR
	 */
	public ActualizarCategoria() {
		categoriaControlador = new CategoriaControlador();
		panelBuscar = new BuscarCategoria();
		inicializarComponentes();
		listaCategorias = new ArrayList<Categoria>();
	}

	/*
	 * METODO PARA INICIALIZAR Y DAR ESTILO A TODOS LOS COMPONETES VISUALES DE LA VISTA DE LA CLASE BuscarClientes
	 */
	private void inicializarComponentes() {
		setLayout(null);
		JPanel panelActualizar = new JPanel();
		panelActualizar.setBounds(0, 0, 960, 440);
		add(panelActualizar);
		panelActualizar.setLayout(null);


		// Labels
		lblDatosCategoria = new JLabel("Datos de la Categoría");
		lblDatosCategoria.setForeground(new Color(135, 134, 133));
		lblDatosCategoria.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDatosCategoria.setBounds(124, 34, 150, 20);
		panelActualizar.add(lblDatosCategoria);

		lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setBounds(393, 81, 120, 20);
		lblDescripcion.setForeground(new Color(135, 134, 133));
		panelActualizar.add(lblDescripcion);

		lblNombreCategoria = new JLabel("Nombre:");
		lblNombreCategoria.setBounds(150, 81, 80, 20);
		lblNombreCategoria.setForeground(new Color(135, 134, 133));
		panelActualizar.add(lblNombreCategoria);


		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ActualizarCategoria.class.getResource("/media/ImagenCategoria.png")));
		lblNewLabel.setBounds(640, 100, 200, 190);
		panelActualizar.add(lblNewLabel);


		// TextFields

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(393, 104, 200, 20);
		txtDescripcion.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtDescripcion.setText(panelBuscar.descripcion);
		panelActualizar.add(txtDescripcion);

		txtNombreCategoria = new JTextField();
		txtNombreCategoria.setBounds(150, 104, 200, 20);
		txtNombreCategoria.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtNombreCategoria.setText(panelBuscar.nombreCategoria);
		panelActualizar.add(txtNombreCategoria);


		// Botones
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(485, 160, 100, 30);
		btnActualizar.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		btnActualizar.setBackground(new Color(192, 192, 192));
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnActualizar.setForeground(new Color(255, 255, 255));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnActualizarActionPerformed(evt, panelActualizar);
			}
		});
		panelActualizar.add(btnActualizar);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(360, 160, 100, 30);
		btnSalir.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		btnSalir.setBackground(new Color(192, 192, 192));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnSalirActionPerformed(evt, panelActualizar);
			}
		});
		panelActualizar.add(btnSalir);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panel.setBackground(new Color(164, 255, 255));
		panel.setBounds(82, 23, 792, 389);
		panelActualizar.add(panel);

	}

	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Actualizar
	 */	
	private void btnActualizarActionPerformed(ActionEvent evt, JPanel panelActualizar) {
		try {
			int idCategoria = panelBuscar.idCategoria;
			String descripcion = txtDescripcion.getText();
			String nombre = txtNombreCategoria.getText();

			String mensaje = "";
			mensaje = categoriaControlador.modificarCategoria(idCategoria, nombre, descripcion, true);
			JOptionPane.showMessageDialog(this,mensaje);
			limpiarCampos();
			if(mensaje.equals("Categoria actualizada.")) {
				irPanelBuscar(evt, panelActualizar);
				limpiarCampos();
			}



		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Campos invalidos.");
		}
	}

	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Salir
	 */	
	private void btnSalirActionPerformed(ActionEvent evt, JPanel panelActualizar) {
		try {
			irPanelBuscar(evt, panelActualizar);
			limpiarCampos();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Campos invalidos.");
		}
	}

	/*
	 * METODO PARA REALIZAR LA ACCIÓN VOLVER AL PANEL DE LA CLASE BuscarClientes
	 */	
	private void irPanelBuscar(ActionEvent evt, JPanel panelActualizar) {
		try {
			panelBuscar = new BuscarCategoria(); 
			panelBuscar.setSize(960, 440);
			panelBuscar.setLocation(0, 0);

			panelActualizar.removeAll();
			panelActualizar.add(panelBuscar, BorderLayout.CENTER);
			panelActualizar.revalidate();
			panelActualizar.repaint();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Campos invalidos.");
		}
	}

	/*
	 * METODO PARA REALIZAR LA ACCIÓN DE LIMPIAR LOS VALORES QUE ESTAN SELECCIONADOS DE LA TABLA
	 */	
	public void limpiarCampos(){
		panelBuscar.idCategoria = 0;
		panelBuscar.nombreCategoria = "";
		panelBuscar.descripcion = "";
	}

}
