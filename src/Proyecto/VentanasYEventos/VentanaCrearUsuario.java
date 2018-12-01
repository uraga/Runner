package Proyecto.VentanasYEventos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaCrearUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtMail;
	private JPasswordField txtConfCont;
	private JPasswordField txtContrasena;
	private JTextField txtUsuario;
	private JTextField txtNombre;
	private JTextField txtApellido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCrearUsuario frame = new VentanaCrearUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaCrearUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Elementos de la ventana
		JLabel lblNombre = new JLabel("Nombre:");
		txtNombre = new JTextField();
		JLabel lblApellido = new JLabel("Apellido:");
		txtApellido = new JTextField();
		JLabel lblUsuario = new JLabel("Usuario:");
		txtUsuario = new JTextField();
		JLabel lblMail = new JLabel("Email:");
		txtMail = new JTextField();
		JLabel lblContrasena = new JLabel("Contraseña:");
		JLabel lblConfCont = new JLabel("Confirmar contraseña:");
		JButton btnEntrar = new JButton("Entrar");
		JButton btnAtras = new JButton("Atras");
		
		//Posiciones en ventana
//		lblNombre.setBounds(x, y, width, height);
//		contentPane.add(lblNombre);
//		txtNombre.setBounds(x, y, width, height);
//		contentPane.add(txtNombre);
//		lblApellido.setBounds(x, y, width, height);
//		contentPane.add(lblApellido);
//		txtApellido.setBounds(x, y, width, height);
//		contentPane.add(txtApellido);
//		lblUsuario.setBounds(x, y, width, height);
//		contentPane.add(lblUsuario);
//		txtUsuario.setBounds(x, y, width, height);
//		contentPane.add(txtUsuario);
//		lblMail.setBounds(x, y, width, height);
//		contentPane.add(lblMail);
//		txtMail.setBounds(x, y, width, height);
//		contentPane.add(txtMail);
//		lblContrasena.setBounds(x, y, width, height);
//		contentPane.add(lblContrasena);
//		txtContrasena.setBounds(x, y, width, height);
//		contentPane.add(txtContrasena);
//		lblConfCont.setBounds(x, y, width, height);
//		contentPane.add(lblConfCont);
//		txtConfCont.setBounds(x, y, width, height);
//		contentPane.add(txtConfCont);
//		btnEntrar.setBounds(x, y, width, height);
//		contentPane.add(btnEntrar);
//		btnAtras.setBounds(x, y, width, height);
//		contentPane.add(btnAtras);
		
		
		
		//Eventos
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);}
			});
		
		
		
		
		
		
		
	}

}
