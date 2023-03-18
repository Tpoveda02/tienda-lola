package vista;

import controlador.Controlador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectFood extends JFrame{

	private JPanel contentPane;
	private Controlador conect;
	private Login login;

	/**
	 * Launch the application.
	 */
	public SelectFood() {
		conect = new Controlador();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 861, 490);
		getContentPane().setLayout(null);
		initialize();
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 861, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("PLAZOLETA DE COMIDAS");
		lblNewLabel.setForeground(new Color(255, 127, 80));
		lblNewLabel.setBounds(29, 11, 289, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(384, 11, 90, 23);
		contentPane.add(lblNewLabel_1);

		JPanel panel = new JPanel();
		panel.setBounds(172, 139, 476, 261);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(0, 0, 477, 261);
		panel.add(lblNewLabel_3);

		JButton btnDelete = new JButton("ELIMINAR");
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setBounds(614, 61, 187, 49);
		contentPane.add(btnDelete);

		JButton btnUpdate = new JButton("EDITAR");
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setBounds(427, 61, 187, 49);

		contentPane.add(btnUpdate);

		JButton btnSearch = new JButton("BUSCAR");
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSearch.setBounds(240, 61, 187, 49);
		contentPane.add(btnSearch);

		JButton btnInsert = new JButton("INSERTAR");

		btnInsert.setForeground(new Color(0, 0, 0));
		btnInsert.setBackground(Color.WHITE);
		btnInsert.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnInsert.setBounds(53, 61, 187, 49);

		contentPane.add(btnInsert);
		
		JButton btnExit = new JButton("Salir");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(Color.RED);

		btnExit.setBounds(702, 388, 99, 32);
		contentPane.add(btnExit);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setBounds(0, 0, 845, 451);
		contentPane.add(lblNewLabel_2);
	}

}
