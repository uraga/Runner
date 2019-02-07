package Proyecto.VentanasYEventos;

import java.awt.Color;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Proyecto.Datos.BD;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

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
		setTitle( "Ventana de login" );
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
		
		//Eventos
		
		btnEntrar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userIntroducido = intrUsuario.getText();
				passIntroducido =  new String( intrContrasena.getPassword() );
				Connection conn = BD.conexionBD( "RUNNERBD2.db" );
				Statement stat = BD.usarCrearTablasBD( conn );
				try {
					String sentSQL = "select * from usuario where cod_usuario= '" + userIntroducido + "' and password= '" + passIntroducido + "';";
					ResultSet rs = stat.executeQuery(sentSQL);
					if ( rs.next() ) {
						JOptionPane.showMessageDialog( null, "Bienvenid@" + userIntroducido, " ", JOptionPane.INFORMATION_MESSAGE);						
						VentanaMenu ventanaMenu = new VentanaMenu();
						ventanaMenu.setVisible( true );
						dispose();
					} else {
						JOptionPane.showMessageDialog( null, "Usuario o contraseña incorrectos", " ", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
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
		
	}
}