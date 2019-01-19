package Proyecto.VentanasYEventos;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Proyecto.Datos.BD;
import Proyecto.Datos.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

/**
 * CLASE PENDIENTE GESTION BD CON USUARIO
 * @author JON URAGA, YERAY BELLANCO
 *
 */
public class VentanaUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTextField intrUsuario;
	public static JPasswordField intrContrasena;
	public static String userIntroducido = "";
	public static String passIntroducido = "";

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
		intrUsuario.setBounds(170, 50, 144, 30);
		contentPane.add(intrUsuario);
		lblContrasena.setBounds(50, 120, 100, 30);
		contentPane.add(lblContrasena);
		intrContrasena.setBounds(170, 120, 144, 30);
		contentPane.add(intrContrasena);
		btnEntrar.setBounds(101, 200, 70, 40);
		contentPane.add(btnEntrar);
		btnRegistrarse.setBounds(182, 200, 106, 40);
		contentPane.add(btnRegistrarse);
		btnAtras.setBounds(300, 200, 70, 40);
		contentPane.add(btnAtras);
		
		//Eventos
		
		btnEntrar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userIntroducido = intrUsuario.getText();
				passIntroducido =  new String( intrContrasena.getPassword() );
				if (!userIntroducido.equals("") && passIntroducido.equals("")) {
					Connection con = BD.conexionBD("/Users/yerayb/git/Runner/src/Proyecto/Datos/RUNNERBD2.db");
					Statement stat = null;
					try {
						stat = con.createStatement();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					Usuario user = new Usuario( userIntroducido, passIntroducido );
					if ( BD.login(stat, user ) == true ) {
						VentanaMenu vMenu = new VentanaMenu();
						vMenu.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog( null, "Usuario o contraseña incorrectos" );
					}
				} else {
					JOptionPane.showMessageDialog( null, "Debes rellenar todos los campos" );
				}
				
			}
		});
		
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