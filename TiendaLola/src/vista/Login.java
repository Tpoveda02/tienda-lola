package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import javax.swing.border.LineBorder;
import java.awt.Insets;
import java.awt.Component;

/**
 * Clase que permite mostrar el juego al usuario.
 * @author Sergio Salas
 */

public class Login extends JFrame{
	//Objeto de la clase Controlador
	private Controlador controlador;
	//Objeto de la clase Clientes
	private Inicio inicio;
	//Objeto de la clase BuscarClientes
	private BuscarClientes buscar;
	//-------------Variables------------
	private JPanel contentPane;
	private JPanel panelFondo;
	private JPanel panelBarra;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	private JLabel lblPassword;
	private JLabel lblLogin;
	private JLabel lblUsername;
	private JLabel logo;
	private JLabel logoTienda;
	private JLabel lblLolaSoft;
	private JButton btnIniciarSesion;

	
	//-----------Constructor-----------
	public Login() {
		controlador  = new Controlador();
		setBounds(100, 100, 975, 617);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		initializarComponentes();
	}

	/*
     * METODO PARA INICIALIZAR Y DAR ESTILO A TODOS LOS COMPONETES VISUALES DE LA VISTA DEL LOGIN
     */
	public void initializarComponentes() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelBarra = new JPanel();
		panelBarra.setBounds(0, 70, 959, 31);
		panelBarra.setBackground(new Color(182, 220, 255));
		contentPane.add(panelBarra);

		//LABELS
		lblLogin = new JLabel("INICIO DE SESIÓN");
		lblLogin.setBounds(330, 127, 317, 49);
		lblLogin.setForeground(new Color(135, 134, 133));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblLogin);
		
		lblPassword = new JLabel("Contraseña");
		lblPassword.setBounds(299, 378, 176, 19);
		lblPassword.setForeground(new Color(135, 134, 133));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblPassword);

		lblUsername = new JLabel("Usuario");
		lblUsername.setBounds(299, 298, 175, 19);
		lblUsername.setForeground(new Color(135, 134, 133));
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblUsername);

		logo = new JLabel("");
		logo.setBounds(443, 187, 97, 100);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setVerticalAlignment(SwingConstants.TOP);
		logo.setIcon(new ImageIcon(Login.class.getResource("/media/LogoUsuario3.png")));
		contentPane.add(logo);
		
		logoTienda = new JLabel("");
		logoTienda.setBounds(42, 11, 55, 59);
		logoTienda.setIcon(new ImageIcon(Login.class.getResource("/media/LogoTienda.png")));
		contentPane.add(logoTienda);

		lblLolaSoft = new JLabel("LOLA SOFT");
		lblLolaSoft.setBounds(117, 21, 173, 33);
		lblLolaSoft.setForeground(new Color(135, 134, 133));
		lblLolaSoft.setFont(new Font("Tahoma", Font.BOLD, 23));
		contentPane.add(lblLolaSoft);

		//CAJONES DE TEXTO
		txtUsername = new JTextField();
		txtUsername.setBounds(299, 321, 390, 39);
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUsername.setForeground(new Color(0, 0, 0));
		txtUsername.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		contentPane.add(txtUsername);


		passwordField = new JPasswordField();
		passwordField.setBounds(299, 401, 390, 39);
		passwordField.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setToolTipText("Password");
		contentPane.add(passwordField);

		//BOTONES
		btnIniciarSesion = new JButton("Iniciar sesión");
		btnIniciarSesion.setBounds(387, 474, 212, 31);
		btnIniciarSesion.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnIniciarSesionActionPerformed(evt);
			}
		});
		btnIniciarSesion.setBackground(new Color(192, 192, 192));
		btnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnIniciarSesion.setForeground(new Color(255, 255, 255));

		contentPane.add(btnIniciarSesion);
		
		panelFondo = new JPanel();
		panelFondo.setBounds(198, 118, 592, 401);
		panelFondo.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelFondo.setBackground(new Color(164, 255, 255));
		contentPane.add(panelFondo);

	}
	
	
	/*
     * METODO PARA REALIZAR LA ACCIÓN DEL BOTÓN IniciarSesion
     */
	private void btnIniciarSesionActionPerformed(ActionEvent evt) {
		boolean flag;
		inicio = new Inicio();
		buscar = new BuscarClientes();
		try {
			flag = controlador.login(txtUsername.getText(), passwordField.getText());
			System.out.println(flag);
			if(flag) {
				inicio.setVisible(true);
				dispose();
			}else {
				JOptionPane.showMessageDialog(null, "Las credenciales son erroneas.", "Acceso invalido", JOptionPane.WARNING_MESSAGE);
			}

		} catch (Exception ex) {
			System.out.println("Conexión perdida con la base de datos.");
			ex.printStackTrace();
		}
	}
}
