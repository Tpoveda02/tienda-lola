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

import controlador.ClienteControlador;
import controlador.Controlador;
import modelo.Cliente;

public class ActualizarCliente extends JPanel {
	
	//Objeto de la clase Controlador
	private ClienteControlador clienteControlador;
	//Objeto de la clase BuscarClientes
	private BuscarClientes panelBuscar;
	
	//--------------------Variables--------------------
	private List<Cliente> listaClientes;
	private JLabel lblRegistroCliente;
	private JLabel lblIdCliente;
	private JLabel lblTipoIdentificacion; 
	private JLabel lblPrimerNombre; 
	private JLabel lblSegundoNombre; 
	private JLabel lblPrimerApellido;
	private JLabel lblSegundoApellido; 
	private JLabel lblDireccion; 
	private JLabel lblTelefono; 
	private JLabel lblCorreoElectronico;
	private JLabel lblNewLabel;
	private JTextField txtIdCliente; 
	private JComboBox txtTipoIdentificacion;
	private JTextField txtPrimerNombre; 
	private JTextField txtSegundoNombre; 
	private JTextField txtPrimerApellido;
	private JTextField txtSegundoApellido; 
	private JTextField txtDireccion;
	private JTextField txtTelefono; 
	private JTextField txtCorreoElectronico;
	private DefaultTableModel modeloTabla;
	private JButton btnActualizar; 
	private JButton btnSalir; 

	/*
	 * METODO CONSTRUCTOR
	 */
	public ActualizarCliente() {

		clienteControlador = new ClienteControlador();
		panelBuscar = new BuscarClientes();
		inicializarComponentes();
		listaClientes = new ArrayList<Cliente>();

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
		lblRegistroCliente = new JLabel("Registro del Cliente");
		lblRegistroCliente.setForeground(new Color(135, 134, 133));
		lblRegistroCliente.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRegistroCliente.setBounds(124, 34, 150, 20);
		panelActualizar.add(lblRegistroCliente);

		lblTipoIdentificacion = new JLabel("Tipo Identificación:");
		lblTipoIdentificacion.setBounds(150, 81, 120, 20);
		lblTipoIdentificacion.setForeground(new Color(135, 134, 133));
		panelActualizar.add(lblTipoIdentificacion);

		lblPrimerNombre = new JLabel("Primer Nombre:");
		lblPrimerNombre.setBounds(150, 135, 120, 20);
		lblPrimerNombre.setForeground(new Color(135, 134, 133));
		panelActualizar.add(lblPrimerNombre);

		lblSegundoNombre = new JLabel("Segundo Nombre:");
		lblSegundoNombre.setBounds(393, 135, 120, 20);
		lblSegundoNombre.setForeground(new Color(135, 134, 133));
		panelActualizar.add(lblSegundoNombre);

		lblPrimerApellido = new JLabel("Primer Apellido:");
		lblPrimerApellido.setBounds(150, 189, 120, 20);
		lblPrimerApellido.setForeground(new Color(135, 134, 133));
		panelActualizar.add(lblPrimerApellido);

		lblSegundoApellido = new JLabel("Segundo Apellido:");
		lblSegundoApellido.setBounds(392, 191, 120, 20);
		lblSegundoApellido.setForeground(new Color(135, 134, 133));
		panelActualizar.add(lblSegundoApellido);

		lblDireccion = new JLabel("Dirección:");
		lblDireccion.setBounds(150, 243, 80, 20);
		lblDireccion.setForeground(new Color(135, 134, 133));
		panelActualizar.add(lblDireccion);

		lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setBounds(393, 243, 80, 20);
		lblTelefono.setForeground(new Color(135, 134, 133));
		panelActualizar.add(lblTelefono);

		lblCorreoElectronico = new JLabel("Correo Electrónico:");
		lblCorreoElectronico.setBounds(150, 295, 120, 20);
		lblCorreoElectronico.setForeground(new Color(135, 134, 133));
		panelActualizar.add(lblCorreoElectronico);
		
		lblIdCliente = new JLabel("ID Cliente:");
		lblIdCliente.setBounds(393, 81, 80, 20);
		lblIdCliente.setForeground(new Color(135, 134, 133));
		panelActualizar.add(lblIdCliente);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AgregarCliente.class.getResource("/media/ImagenCliente.png")));
		lblNewLabel.setBounds(640, 120, 186, 209);
		panelActualizar.add(lblNewLabel);


		// TextFields
		txtIdCliente = new JTextField();
		txtIdCliente.setBounds(393, 104, 200, 20);
		txtIdCliente.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtIdCliente.setText(panelBuscar.idCliente + "");
		panelActualizar.add(txtIdCliente);


		txtTipoIdentificacion = new JComboBox();
		txtTipoIdentificacion.setModel(new DefaultComboBoxModel(new String[] {"", "CC", "NIT", "DNI", "PASAPORTE"}));
		txtTipoIdentificacion.setBounds(150, 104, 200, 20);
		txtTipoIdentificacion.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtTipoIdentificacion.setSelectedItem(panelBuscar.tipoIdentificacion);
		panelActualizar.add(txtTipoIdentificacion);

		txtPrimerNombre = new JTextField();
		txtPrimerNombre.setBounds(150, 158, 200, 20);
		txtPrimerNombre.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtPrimerNombre.setText(panelBuscar.primerNombre);
		panelActualizar.add(txtPrimerNombre);

		txtSegundoNombre = new JTextField();
		txtSegundoNombre.setBounds(393, 158, 200, 20);
		txtSegundoNombre.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtSegundoNombre.setText(panelBuscar.segundoNombre);
		panelActualizar.add(txtSegundoNombre);

		txtPrimerApellido = new JTextField();
		txtPrimerApellido.setBounds(150, 212, 200, 20);
		txtPrimerApellido.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtPrimerApellido.setText(panelBuscar.primerApellido);
		panelActualizar.add(txtPrimerApellido);

		txtSegundoApellido = new JTextField();
		txtSegundoApellido.setBounds(393, 212, 200, 20);
		txtSegundoApellido.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtSegundoApellido.setText(panelBuscar.segundoApellido);
		panelActualizar.add(txtSegundoApellido);

		txtDireccion = new JTextField();
		txtDireccion.setBounds(150, 264, 200, 20);
		txtDireccion.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtDireccion.setText(panelBuscar.direccion);
		panelActualizar.add(txtDireccion);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(393, 264, 200, 20);
		txtTelefono.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtTelefono.setText(panelBuscar.telefono);
		panelActualizar.add(txtTelefono);

		txtCorreoElectronico = new JTextField();
		txtCorreoElectronico.setBounds(150, 316, 443, 20);
		txtCorreoElectronico.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtCorreoElectronico.setText(panelBuscar.correoElectronico);
		panelActualizar.add(txtCorreoElectronico);


		// Botones
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(485, 372, 100, 30);
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
		btnSalir.setBounds(360, 372, 100, 30);
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
			int idCliente = Integer.parseInt(txtIdCliente.getText());
			String tipoIdentificacion = (String) txtTipoIdentificacion.getSelectedItem();
			String primerNombre = txtPrimerNombre.getText();
			String segundoNombre = txtSegundoNombre.getText();
			String primerApellido = txtPrimerApellido.getText();
			String segundoApellido = txtSegundoApellido.getText();
			String direccion = txtDireccion.getText();
			String telefono = txtTelefono.getText();
			String correoElectronico = txtCorreoElectronico.getText();
			
			String mensaje = "";
			mensaje = clienteControlador.modificarCliente(idCliente, tipoIdentificacion, primerNombre, segundoNombre,
					primerApellido, segundoApellido, direccion, telefono, correoElectronico, true);
			JOptionPane.showMessageDialog(this,mensaje);
			limpiarCampos();
			if(mensaje.equals("Cliente actualizado.")) {
				irPanelBuscar(evt, panelActualizar);
				limpiarCampos();
			}

			
			
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Campos invalidos");
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
			JOptionPane.showMessageDialog(this, "Campos invalidos");
		}
	}
	
	/*
	 * METODO PARA REALIZAR LA ACCIÓN VOLVER AL PANEL DE LA CLASE BuscarClientes
	 */	
	private void irPanelBuscar(ActionEvent evt, JPanel panelActualizar) {
		try {
			panelBuscar = new BuscarClientes();
			panelBuscar.setSize(960, 440);
			panelBuscar.setLocation(0, 0);
			panelActualizar.removeAll();
			panelActualizar.add(panelBuscar, BorderLayout.CENTER);
			panelActualizar.revalidate();
			panelActualizar.repaint();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Campos invalidos");
		}
	}
	
	/*
	 * METODO PARA REALIZAR LA ACCIÓN DE LIMPIAR LOS VALORES QUE ESTAN SELECCIONADOS DE LA TABLA
	 */	
	public void limpiarCampos(){
		panelBuscar.idCliente = 0;
		panelBuscar.tipoIdentificacion = "";
		panelBuscar.primerNombre = "";
		panelBuscar.segundoNombre = "";
		panelBuscar.primerApellido = "";
		panelBuscar.segundoApellido = "";
		panelBuscar.telefono = "";
		panelBuscar.direccion = "";
		panelBuscar.correoElectronico = "";
	}

}
