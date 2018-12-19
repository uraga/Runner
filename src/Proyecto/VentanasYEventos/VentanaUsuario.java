package Proyecto.VentanasYEventos;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTextField intrUsuario;
	public static JPasswordField intrContrasena;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaUsuario frame = new VentanaUsuario();
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
	public VentanaUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Elementos de la ventana 
		intrUsuario = new JTextField();
		intrContrasena = new JPasswordField();
		JLabel lblUsuario = new JLabel("Usuario:");
		JLabel lblContrasena= new JLabel("Contraseña:");
		JButton btnEntrar = new JButton("Entrar");
		JButton btnRegistrarse = new JButton("Registrarse");
		JButton btnAtras = new JButton("Atras");
		
		//Posiciones en ventana
		lblUsuario.setBounds(50, 50, 100, 30);
		contentPane.add(lblUsuario);
		intrUsuario.setBounds(170, 50, 100, 30);
		contentPane.add(intrUsuario);
		lblContrasena.setBounds(50, 120, 100, 30);
		contentPane.add(lblContrasena);
		intrContrasena.setBounds(170, 120, 100, 30);
		contentPane.add(intrContrasena);
		btnEntrar.setBounds(100, 200, 70, 40);
		contentPane.add(btnEntrar);
		btnRegistrarse.setBounds(200, 200, 70, 40);
		contentPane.add(btnRegistrarse);
		btnAtras.setBounds(300, 200, 70, 40);
		contentPane.add(btnAtras);
		
		//Eventos
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCrearUsuario vcu = new VentanaCrearUsuario();
				vcu.setVisible(true);
				dispose();
			}
		});
	
		
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenu vMenu = new VentanaMenu();
				vMenu.setVisible(true);
				dispose();
			}
				
		});
		
	}
}
