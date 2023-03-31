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

	private JPanel contentPane;
	private Controlador controlador;
	private Login login;

	/**
	 * Launch the application.
	 */
	public Clientes() {
		controlador  = new Controlador();
		login = new Login();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 975, 617);
		getContentPane().setLayout(null);
		initialize();
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 127, 960, 440);
        panel.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
        panel.setBackground(new Color(164, 255, 255));
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel logoTienda = new JLabel("");
        logoTienda.setBounds(42, 11, 55, 59);
        logoTienda.setIcon(new ImageIcon(Login.class.getResource("/media/LogoTienda.png")));
        contentPane.add(logoTienda);
        
        JLabel lblNewLabel = new JLabel("LOLA SOFT");
        lblNewLabel.setBounds(117, 21, 173, 33);
        lblNewLabel.setForeground(new Color(135, 134, 133));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
        contentPane.add(lblNewLabel);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 70, 960, 46);
        panel_1.setBackground(new Color(182, 220, 255));
        contentPane.add(panel_1);
        panel_1.setLayout(null);
        
        JButton btnCategorias = new JButton("Categorias");
        btnCategorias.setBorder(new MatteBorder(0, 1, 0, 1, (Color) Color.WHITE));
        btnCategorias.setForeground(new Color(135, 134, 133));
        btnCategorias.setBackground(new Color(182, 220, 225));
        btnCategorias.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCategorias.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnCategorias.setBounds(800, 0, 160, 46);
        panel_1.add(btnCategorias);
        
        JButton btnProductos = new JButton("Productos");
        btnProductos.setBorder(new MatteBorder(0, 1, 0, 1, (Color) Color.WHITE));
        btnProductos.setForeground(new Color(135, 134, 133));
        btnProductos.setBackground(new Color(182, 220, 225));
        btnProductos.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnProductos.setBounds(640, 0, 160, 46);
        panel_1.add(btnProductos);
        
        JButton btnCliente = new JButton("Cliente");
        btnCliente.setBorder(new MatteBorder(0, 1, 0, 1, (Color) Color.WHITE));
        btnCliente.setForeground(new Color(255, 255, 255));
        btnCliente.setBackground(new Color(182, 220, 225));
        btnCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCliente.setBounds(480, 0, 160, 46);
        panel_1.add(btnCliente);
        
        JButton btnProveedor = new JButton("Proveedor");
        btnProveedor.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnProveedor.setBorder(new MatteBorder(0, 1, 0, 1, (Color) Color.WHITE));
        btnProveedor.setForeground(new Color(135, 134, 133));
        btnProveedor.setBackground(new Color(182, 220, 225));
        btnProveedor.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnProveedor.setBounds(320, 0, 160, 46);
        panel_1.add(btnProveedor);
        
        JButton btnFacProveedor = new JButton("Facturas proveedor");
        btnFacProveedor.setBorder(new MatteBorder(0, 1, 0, 1, (Color) Color.WHITE));
        btnFacProveedor.setForeground(new Color(135, 134, 133));
        btnFacProveedor.setBackground(new Color(182, 220, 225));
        btnFacProveedor.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnFacProveedor.setBounds(160, 0, 160, 46);
        panel_1.add(btnFacProveedor);
        
        JButton btnFacCliente = new JButton("Facturas cliente");
        btnFacCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnFacCliente.setBorder(new MatteBorder(0, 1, 0, 1, (Color) Color.WHITE));
        btnFacCliente.setForeground(new Color(135, 134, 133));
        btnFacCliente.setBackground(new Color(182, 220, 225));
        btnFacCliente.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnFacCliente.setBounds(0, 0, 160, 46);
        panel_1.add(btnFacCliente);

        
        BuscarClientes panelBuscar = new BuscarClientes();
        panelBuscar.setSize(960, 440);
        panelBuscar.setLocation(0, 0);

		panel.removeAll();
		panel.add(panelBuscar, BorderLayout.CENTER);
		
		JLabel lblCorreoTienda = new JLabel("tiendaLola@tienda.com");
		lblCorreoTienda.setForeground(new Color(135,134,133));
		lblCorreoTienda.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCorreoTienda.setBounds(751, 11, 112, 14);
		contentPane.add(lblCorreoTienda);
		
		JLabel lblTelefonoTienda = new JLabel("302-245-4562");
		lblTelefonoTienda.setForeground(new Color(135,134,133));
		lblTelefonoTienda.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTelefonoTienda.setBounds(794, 28, 68, 14);
		contentPane.add(lblTelefonoTienda);
		
		JLabel lblDireccionTienda = new JLabel("Cra 12 #34-21");
		lblDireccionTienda.setForeground(new Color(135,134,133));
		lblDireccionTienda.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDireccionTienda.setBounds(791, 44, 98, 14);
		contentPane.add(lblDireccionTienda);
		
		JButton btnCerrarSesion = new JButton("");
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
		panel.revalidate();
		panel.repaint();
//		panelBuscar.inicializarComponentes();
		
	}
	
	private void btnCerrarSesionActionPerformed(ActionEvent evt) {
		try {
			login.setVisible(true);
        	dispose();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Error al cerrar sesión.");
		}
	}
}
