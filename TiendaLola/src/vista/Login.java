package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    //Objeto de la clase Connection
    private Controlador controlador;
    private Clientes clientes;
    private BuscarClientes buscar;
    private NoAcceso noAcceso;
    private JPanel contentPane;
    private JTextField txtUsername;
    private JPasswordField passwordField;


    public Login() {
        controlador  = new Controlador();
        setBounds(100, 100, 975, 617);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        //		Login frame = new Login();
        //		frame.setVisible(true);
        initialize();
    }

    /**
     * Create the frame.
     */
    public void initialize() {

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblLogin = new JLabel("INICIO DE SESIÓN");
        lblLogin.setBounds(330, 127, 317, 49);
        lblLogin.setForeground(new Color(135, 134, 133));
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
        contentPane.add(lblLogin);

        JButton btnNewButton = new JButton("Iniciar sesión");
        btnNewButton.setBounds(387, 474, 212, 31);
        btnNewButton.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean flag;
                clientes = new Clientes();
                noAcceso = new NoAcceso();
                buscar = new BuscarClientes();
                try {
                    flag = controlador.login(txtUsername.getText(), passwordField.getText());
                    System.out.println(flag);
                    if(flag) {
                    	clientes.setVisible(true);
                        dispose();
                    }else {
                    	//noAcceso.setVisible(true);
                    	clientes.setVisible(true);
                    	dispose();
                    }

                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
        btnNewButton.setBackground(new Color(192, 192, 192));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton.setForeground(new Color(255, 255, 255));

        contentPane.add(btnNewButton);

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

        JLabel lblPassword = new JLabel("Contraseña");
        lblPassword.setBounds(299, 378, 176, 19);
        lblPassword.setForeground(new Color(135, 134, 133));
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
        contentPane.add(lblPassword);

        JLabel lblUsername = new JLabel("Usuario");
        lblUsername.setBounds(299, 298, 175, 19);
        lblUsername.setForeground(new Color(135, 134, 133));
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
        contentPane.add(lblUsername);
        
        JLabel logo = new JLabel("");
        logo.setBounds(443, 187, 97, 100);
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setVerticalAlignment(SwingConstants.TOP);
        logo.setIcon(new ImageIcon(Login.class.getResource("/media/LogoUsuario3.png")));
        contentPane.add(logo);
        
        JPanel panel = new JPanel();
        panel.setBounds(198, 118, 592, 401);
        panel.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
        panel.setBackground(new Color(164, 255, 255));
        contentPane.add(panel);
        
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
        panel_1.setBounds(0, 70, 959, 31);
        panel_1.setBackground(new Color(182, 220, 255));
        contentPane.add(panel_1);
        
        
    }
}
