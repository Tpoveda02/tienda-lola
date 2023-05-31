package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controlador.Controlador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.SpringLayout;
import java.awt.Component;
import javax.swing.border.MatteBorder;

public class Clientes extends JFrame {
	//Objeto de la clase Controlador
	private Controlador controlador;
	//Objeto de la clase Login
	private Login login;
	//Objeto de la clase BuscarClientes 
	private BuscarClientes panelBuscarCliente;
	//Objeto de la calse BuscarCategoria
	private BuscarCategoria panelBuscarCategoria;
	//Objeto de la calse BuscarProveedor
	private BuscarProveedor panelBuscarProveedor;
	//Objeto de la calse BuscarProducto
	private BuscarProducto panelBuscarProducto;

	//--------------Variables--------------
	private JPanel contentPane;
	private JPanel panelCrud;
	private JLabel logoTienda;
	private JLabel lblNombreApp;
	private JLabel lblCorreoTienda;
	private JLabel lblTelefonoTienda;
	private JLabel lblDireccionTienda;
	private JPanel panelMenu;
	private JButton btnCategorias;
	private JButton btnProductos;
	private JButton btnCliente;
	private JButton btnProveedor;
	private JButton btnFacProveedor;
	private JButton btnFacCliente;
	private JButton btnCerrarSesion;

	/*
	 * METODO CONSTRUCTOR
	 */
	public Clientes() {
		controlador  = new Controlador();
		login = new Login();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 975, 617);
		getContentPane().setLayout(null);
		initializarComponentes();
	}

	/*
	 * METODO PARA INICIALIZAR Y DAR ESTILO A TODOS LOS COMPONETES VISUALES DE LA VISTA DE LA CLASE CLIENTES
	 */
	public void initializarComponentes() {		
		//PANELES
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelCrud = new JPanel();
		panelCrud.setBounds(0, 127, 960, 440);
		panelCrud.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelCrud.setBackground(new Color(164, 255, 255));
		contentPane.add(panelCrud);
		panelCrud.setLayout(null);

		panelMenu = new JPanel();
		panelMenu.setBounds(0, 70, 960, 46);
		panelMenu.setBackground(new Color(182, 220, 255));
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);

		// Labels
		logoTienda = new JLabel("");
		logoTienda.setBounds(42, 11, 55, 59);
		logoTienda.setIcon(new ImageIcon(Login.class.getResource("/media/LogoTienda.png")));
		contentPane.add(logoTienda);

		lblNombreApp = new JLabel("LOLA SOFT");
		lblNombreApp.setBounds(117, 21, 173, 33);
		lblNombreApp.setForeground(new Color(135, 134, 133));
		lblNombreApp.setFont(new Font("Tahoma", Font.BOLD, 23));
		contentPane.add(lblNombreApp);

		lblCorreoTienda = new JLabel("tiendaLola@tienda.com");
		lblCorreoTienda.setForeground(new Color(135,134,133));
		lblCorreoTienda.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCorreoTienda.setBounds(751, 11, 112, 14);
		contentPane.add(lblCorreoTienda);

		lblTelefonoTienda = new JLabel("302-245-4562");
		lblTelefonoTienda.setForeground(new Color(135,134,133));
		lblTelefonoTienda.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTelefonoTienda.setBounds(794, 28, 68, 14);
		contentPane.add(lblTelefonoTienda);

		lblDireccionTienda = new JLabel("Cra 12 #34-21");
		lblDireccionTienda.setForeground(new Color(135,134,133));
		lblDireccionTienda.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDireccionTienda.setBounds(791, 44, 98, 14);
		contentPane.add(lblDireccionTienda);

		//BOTONES
		btnCategorias = new JButton("Categorías");
		btnCategorias.setBorder(new MatteBorder(0, 1, 0, 1, (Color) Color.WHITE));
		btnCategorias.setForeground(new Color(135, 134, 133));
		btnCategorias.setBackground(new Color(182, 220, 225));
		btnCategorias.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnCategoriasActionPerformed(evt);
			}
		});
		btnCategorias.setBounds(800, 0, 160, 46);
		panelMenu.add(btnCategorias);

		btnProductos = new JButton("Productos");
		btnProductos.setBorder(new MatteBorder(0, 1, 0, 1, (Color) Color.WHITE));
		btnProductos.setForeground(new Color(135, 134, 133));
		btnProductos.setBackground(new Color(182, 220, 225));
		btnProductos.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnProductosActionPerformed(evt);
			}
		});
		btnProductos.setBounds(640, 0, 160, 46);
		panelMenu.add(btnProductos);

		btnCliente = new JButton("Clientes");
		btnCliente.setBorder(new MatteBorder(0, 1, 0, 1, (Color) Color.WHITE));
		btnCliente.setForeground(new Color(255, 255, 255));
		btnCliente.setBackground(new Color(182, 220, 225));
		btnCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnClientesActionPerformed(evt);
			}
		});
		btnCliente.setBounds(480, 0, 160, 46);
		panelMenu.add(btnCliente);

		btnProveedor = new JButton("Proveedores");
		btnProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnProveedoresActionPerformed(evt);
			}
		});
		btnProveedor.setBorder(new MatteBorder(0, 1, 0, 1, (Color) Color.WHITE));
		btnProveedor.setForeground(new Color(135, 134, 133));
		btnProveedor.setBackground(new Color(182, 220, 225));
		btnProveedor.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnProveedor.setBounds(320, 0, 160, 46);
		panelMenu.add(btnProveedor);

		btnFacProveedor = new JButton("Facturas proveedor");
		btnFacProveedor.setBorder(new MatteBorder(0, 1, 0, 1, (Color) Color.WHITE));
		btnFacProveedor.setForeground(new Color(135, 134, 133));
		btnFacProveedor.setBackground(new Color(182, 220, 225));
		btnFacProveedor.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnFacProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnFacProveedorActionPerformed(evt);
			}
		});
		btnFacProveedor.setBounds(160, 0, 160, 46);
		panelMenu.add(btnFacProveedor);

		btnFacCliente = new JButton("Facturas cliente");
		btnFacCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnFacCliente.setBorder(new MatteBorder(0, 1, 0, 1, (Color) Color.WHITE));
		btnFacCliente.setForeground(new Color(135, 134, 133));
		btnFacCliente.setBackground(new Color(182, 220, 225));
		btnFacCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnFacClienteActionPerformed(evt);
			}
		});
		btnFacCliente.setBounds(0, 0, 160, 46);
		panelMenu.add(btnFacCliente);

		//Acción para llamar la clase BuscarClientes dentro del panelCrud
		panelBuscarCliente = new BuscarClientes();
		panelBuscarCliente.setSize(960, 440);
		panelBuscarCliente.setLocation(0, 0);

		panelCrud.removeAll();
		panelCrud.add(panelBuscarCliente, BorderLayout.CENTER);

		btnCerrarSesion = new JButton("");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnCerrarSesionActionPerformed(evt);
			}
		});
		btnCerrarSesion.setBorder(null);
		btnCerrarSesion.setIcon(new ImageIcon(Clientes.class.getResource("/media/CerrarSesion.png")));
		btnCerrarSesion.setBounds(873, 11, 50, 45);
		btnCerrarSesion.setBackground(null);
		contentPane.add(btnCerrarSesion);
		panelCrud.revalidate();
		panelCrud.repaint();

	}

	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN CerrarSesion
	 */
	private void btnCerrarSesionActionPerformed(ActionEvent evt) {
		try {
			login.setVisible(true);
			dispose();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Error al cerrar sesión.");
		}
	}

	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Categorias
	 */
	private void btnCategoriasActionPerformed(ActionEvent evt) {
		try {
			btnCategorias.setForeground(new Color(255, 255, 255));
			btnFacCliente.setForeground(new Color(135, 134, 133));
			btnFacProveedor.setForeground(new Color(135, 134, 133));
			btnProductos.setForeground(new Color(135, 134, 133));
			btnCliente.setForeground(new Color(135, 134, 133));
			btnProveedor.setForeground(new Color(135, 134, 133));
			//Acción para llamar la clase BuscarCategoria dentro del panelCrud
			panelBuscarCategoria = new BuscarCategoria();
			panelBuscarCategoria.setSize(960, 440);
			panelBuscarCategoria.setLocation(0, 0);

			panelCrud.removeAll();
			panelCrud.add(panelBuscarCategoria, BorderLayout.CENTER);
			panelCrud.revalidate();
			panelCrud.repaint();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Acción incorrecta...");
		}
	}

	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Proveedores
	 */
	private void btnProveedoresActionPerformed(ActionEvent evt) {
		try {
			btnCategorias.setForeground(new Color(135, 134, 133));
			btnFacCliente.setForeground(new Color(135, 134, 133));
			btnFacProveedor.setForeground(new Color(135, 134, 133));
			btnProductos.setForeground(new Color(135, 134, 133));
			btnCliente.setForeground(new Color(135, 134, 133));
			btnProveedor.setForeground(new Color(255, 255, 255));
			//Acción para llamar la clase BuscarProveedor dentro del panelCrud
			panelBuscarProveedor = new BuscarProveedor();
			panelBuscarProveedor.setSize(960, 440);
			panelBuscarProveedor.setLocation(0, 0);

			panelCrud.removeAll();
			panelCrud.add(panelBuscarProveedor, BorderLayout.CENTER);
			panelCrud.revalidate();
			panelCrud.repaint();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Acción incorrecta...");
		}
	}
	
	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Clientes
	 */
	private void btnClientesActionPerformed(ActionEvent evt) {
		try {
			btnCategorias.setForeground(new Color(135, 134, 133));
			btnFacCliente.setForeground(new Color(135, 134, 133));
			btnFacProveedor.setForeground(new Color(135, 134, 133));
			btnProductos.setForeground(new Color(135, 134, 133));
			btnCliente.setForeground(new Color(255, 255, 255));
			btnProveedor.setForeground(new Color(135, 134, 133));
			
			
//			//Acción para llamar la clase BuscarClientes dentro del panelCrud
			panelBuscarCliente = new BuscarClientes();
			panelBuscarCliente.setSize(960, 440);
			panelBuscarCliente.setLocation(0, 0);
			panelCrud.removeAll();
			
			panelCrud.add(panelBuscarCliente, BorderLayout.CENTER);
			panelCrud.revalidate();
			panelCrud.repaint();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Acción incorrecta...");
		}
	}
	
	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Productos
	 */
	private void btnProductosActionPerformed(ActionEvent evt) {
		try {
			btnCategorias.setForeground(new Color(135, 134, 133));
			btnFacCliente.setForeground(new Color(135, 134, 133));
			btnFacProveedor.setForeground(new Color(135, 134, 133));
			btnProductos.setForeground(new Color(255, 255, 255));
			btnCliente.setForeground(new Color(135, 134, 133));
			btnProveedor.setForeground(new Color(135, 134, 133));
			//Acción para llamar la clase BuscarProducto dentro del panelCrud
			panelBuscarProducto = new BuscarProducto();
			panelBuscarProducto.setSize(960, 440);
			panelBuscarProducto.setLocation(0, 0);

			panelCrud.removeAll();
			panelCrud.add(panelBuscarProducto, BorderLayout.CENTER);
			panelCrud.revalidate();
			panelCrud.repaint();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Acción incorrecta...");
		}
	}
	
	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Facturas Cliente
	 */
	private void btnFacClienteActionPerformed(ActionEvent evt) {
		try {
			btnCategorias.setForeground(new Color(135, 134, 133));
			btnFacCliente.setForeground(new Color(255, 255, 255));
			btnFacProveedor.setForeground(new Color(135, 134, 133));
			btnProductos.setForeground(new Color(135, 134, 133));
			btnCliente.setForeground(new Color(135, 134, 133));
			btnProveedor.setForeground(new Color(135, 134, 133));
//			//Acción para llamar la clase BuscarProveedor dentro del panelCrud
//			panelBuscarProveedor = new BuscarProveedor();
//			panelBuscarProveedor.setSize(960, 440);
//			panelBuscarProveedor.setLocation(0, 0);
//
//			panelCrud.removeAll();
//			panelCrud.add(panelBuscarProveedor, BorderLayout.CENTER);
//			panelCrud.revalidate();
//			panelCrud.repaint();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Acción incorrecta...");
		}
	}
	
	/*
	 * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN Facturas Proveedor
	 */
	private void btnFacProveedorActionPerformed(ActionEvent evt) {
		try {
			btnCategorias.setForeground(new Color(135, 134, 133));
			btnFacCliente.setForeground(new Color(135, 134, 133));
			btnFacProveedor.setForeground(new Color(255, 255, 255));
			btnProductos.setForeground(new Color(135, 134, 133));
			btnCliente.setForeground(new Color(135, 134, 133));
			btnProveedor.setForeground(new Color(135, 134, 133));
//			//Acción para llamar la clase BuscarProveedor dentro del panelCrud
//			panelBuscarProveedor = new BuscarProveedor();
//			panelBuscarProveedor.setSize(960, 440);
//			panelBuscarProveedor.setLocation(0, 0);
//
			panelCrud.removeAll();
//			panelCrud.add(panelBuscarProveedor, BorderLayout.CENTER);
			panelCrud.revalidate();
			panelCrud.repaint();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Acción incorrecta...");
		}
	}
}

