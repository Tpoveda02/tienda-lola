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

import controlador.Controlador;
import modelo.Cliente;

public class ActualizarCliente extends JPanel {
	
	private Controlador controlador;
	private BuscarClientes panelBuscar;
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

	/**
	 * Create the panel.
	 */
	public ActualizarCliente() {
		
		controlador = new Controlador();
		panelBuscar = new BuscarClientes();
		inicializarComponentes();
		listaClientes = new ArrayList<Cliente>();

	}
	
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
		// Agregamos un FocusListener para que el texto informativo se borre automáticamente
		//		ListenerJtext(txtIdCliente, "ID Cliente");
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
		// Agregamos un FocusListener para que el texto informativo se borre automáticamente
		//		ListenerJtext(txtPrimerNombre, "Primer Nombre");
		txtPrimerNombre.setBounds(150, 158, 200, 20);
		txtPrimerNombre.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtPrimerNombre.setText(panelBuscar.primerNombre);
		panelActualizar.add(txtPrimerNombre);

		txtSegundoNombre = new JTextField();
		// Agregamos un FocusListener para que el texto informativo se borre automáticamente
		//		ListenerJtext(txtSegundoNombre, "Segundo Nombre");
		txtSegundoNombre.setBounds(393, 158, 200, 20);
		txtSegundoNombre.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtSegundoNombre.setText(panelBuscar.segundoNombre);
		panelActualizar.add(txtSegundoNombre);

		txtPrimerApellido = new JTextField();
		// Agregamos un FocusListener para que el texto informativo se borre automáticamente
		//		ListenerJtext(txtPrimerApellido, "Primer Apellido");
		txtPrimerApellido.setBounds(150, 212, 200, 20);
		txtPrimerApellido.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtPrimerApellido.setText(panelBuscar.primerApellido);
		panelActualizar.add(txtPrimerApellido);

		txtSegundoApellido = new JTextField();
		// Agregamos un FocusListener para que el texto informativo se borre automáticamente
		//		ListenerJtext(txtSegundoApellido, "Segundo Apellido");
		txtSegundoApellido.setBounds(393, 212, 200, 20);
		txtSegundoApellido.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtSegundoApellido.setText(panelBuscar.segundoApellido);
		panelActualizar.add(txtSegundoApellido);

		txtDireccion = new JTextField();
		// Agregamos un FocusListener para que el texto informativo se borre automáticamente
		//		ListenerJtext(txtDireccion, "Dirección");
		txtDireccion.setBounds(150, 264, 200, 20);
		txtDireccion.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtDireccion.setText(panelBuscar.direccion);
		panelActualizar.add(txtDireccion);

		txtTelefono = new JTextField();
		// Agregamos un FocusListener para que el texto informativo se borre automáticamente
		//		ListenerJtext(txtTelefono, "Teléfono");
		txtTelefono.setBounds(393, 264, 200, 20);
		txtTelefono.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtTelefono.setText(panelBuscar.telefono);
		panelActualizar.add(txtTelefono);

		txtCorreoElectronico = new JTextField();
		// Agregamos un FocusListener para que el texto informativo se borre automáticamente
		//		ListenerJtext(txtCorreoElectronico, "Correo Electrónico");
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
			
			// Actualizar el cliente a la base de datos
			String mensaje = "";
			mensaje = controlador.modificarCliente(idCliente, tipoIdentificacion, primerNombre, segundoNombre,
					primerApellido, segundoApellido, direccion, telefono, correoElectronico);
			JOptionPane.showMessageDialog(this,mensaje);
			
			if(mensaje.equals("Cliente actualizado.")) {
				irPanelBuscar(evt, panelActualizar);
			}

			
			
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "El ID Cliente debe ser un número entero.");
		}
	}

	private void btnSalirActionPerformed(ActionEvent evt, JPanel panelActualizar) {
		try {
			irPanelBuscar(evt, panelActualizar);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "El ID Cliente debe ser un número entero.");
		}
	}
	
	private void irPanelBuscar(ActionEvent evt, JPanel panelActualizar) {
		try {
			panelBuscar.setSize(960, 440);
			panelBuscar.setLocation(0, 0);

			panelActualizar.removeAll();
			panelActualizar.add(panelBuscar, BorderLayout.CENTER);
			panelActualizar.revalidate();
			panelActualizar.repaint();

			// Actualizar la tabla
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "El ID Cliente debe ser un número entero.");
		}
	}

}
