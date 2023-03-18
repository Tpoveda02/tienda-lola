package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

/**
 * Clase que permite mostrar el juego al usuario.
 * @author Sergio Salas
 */

public class Login extends JFrame{
    //Objeto de la clase Connection
    private Controlador conect;
    private SelectFood selectFood;
    private JPanel contentPane;
    private JTextField txtUsername;
    private JPasswordField passwordField;


    public Login() {
        conect  = new Controlador();
        setBounds(100, 100, 771, 439);
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


        JLabel lblLogin = new JLabel("LOGIN");
        lblLogin.setForeground(Color.WHITE);
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblLogin.setBounds(444, 53, 168, 49);
        contentPane.add(lblLogin);

        JButton btnNewButton = new JButton("Log in");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean flag;
                selectFood = new SelectFood();
                try {
                    flag = conect.login(txtUsername.getText(), passwordField.getText());
                    System.out.println(flag);
                    if(flag) {
                            selectFood.setVisible(true);
                            dispose();
                    }

                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
        btnNewButton.setBackground(Color.GREEN);
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBounds(482, 331, 97, 31);

        contentPane.add(btnNewButton);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtUsername.setForeground(Color.LIGHT_GRAY);
        txtUsername.setBounds(425, 146, 211, 39);
        contentPane.add(txtUsername);
        txtUsername.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        passwordField.setToolTipText("Password");
        passwordField.setBounds(425, 258, 211, 39);
        contentPane.add(passwordField);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblPassword.setBounds(426, 228, 88, 19);
        contentPane.add(lblPassword);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setForeground(Color.WHITE);
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblUsername.setBounds(425, 116, 88, 19);
        contentPane.add(lblUsername);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(0, 0, 755, 400);
        contentPane.add(lblNewLabel);
    }


}
