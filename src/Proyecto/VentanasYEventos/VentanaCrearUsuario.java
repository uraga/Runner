package Proyecto.VentanasYEventos;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import Proyecto.Datos.BD;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
/**
 * CLASE PENDIENTE GESTION BD CON USUARIO
 * @author JON URAGA, YERAY BELLANCO
 *
 */
public class VentanaCrearUsuario extends JFrame {

	//Atributos
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMail;
	private JPasswordField txtConfCont;
	private JPasswordField txtContrasena;
	private JTextField txtUsuario;
	private JTextField txtNombre;
	private JTextField txtApellido;
	
	
	
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

	//CONSTRUCTOR
	public VentanaCrearUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 665, 412);
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
		txtContrasena = new JPasswordField();
		JLabel lblConfCont = new JLabel("Confirmar contraseña:");
		txtConfCont = new JPasswordField();
		JButton btnEntrar = new JButton("Entrar");
		JButton btnAtras = new JButton("Atras");
		
		//Posiciones en ventana
		lblNombre.setBounds(103, 75, 79, 16);
		contentPane.add(lblNombre);
		lblApellido.setBounds(103, 128, 79, 16);
		contentPane.add(lblApellido);
		lblUsuario.setBounds(103, 180, 79, 16);
		contentPane.add(lblUsuario);
		lblMail.setBounds(103, 235, 61, 16);
		contentPane.add(lblMail);
		lblContrasena.setBounds(103, 288, 79, 16);
		contentPane.add(lblContrasena);
		lblConfCont.setBounds(103, 348, 140, 16);
		contentPane.add(lblConfCont);
		txtNombre.setBounds(193, 70, 173, 26);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		txtApellido.setBounds(194, 123, 173, 26);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		txtUsuario.setBounds(194, 175, 172, 26);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		txtMail.setBounds(193, 230, 173, 26);
		contentPane.add(txtMail);
		txtMail.setColumns(10);
		txtContrasena.setBounds(194, 283, 172, 26);
		contentPane.add(txtContrasena);
		txtContrasena.setColumns(10);
		txtConfCont.setBounds(255, 343, 162, 26);
		contentPane.add(txtConfCont);
		txtConfCont.setColumns(10);
		btnEntrar.setBounds(468, 134, 117, 62);
		contentPane.add(btnEntrar);
		btnAtras.setBounds(468, 204, 117, 62);
		contentPane.add(btnAtras);
		
		
		//Eventos
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaUsuario ventanaUsuario = new VentanaUsuario();
				ventanaUsuario.setVisible(true);
				dispose();
			}
		});
		
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String contra = new String( txtContrasena.getPassword() );
				String confCont = new String( txtConfCont.getPassword() );
				if ( txtApellido.getText().isEmpty() || contra.isEmpty() || confCont.isEmpty() || txtNombre.getText().isEmpty() || txtUsuario.getText().isEmpty() || txtMail.getText().isEmpty() ) {
					JOptionPane.showMessageDialog( null, "Debes rellenar todos los campos", "Error", JOptionPane.WARNING_MESSAGE );
				} else if( !comprobarMail( txtMail.getText() ) ) {
					JOptionPane.showMessageDialog( null, "Formato de email incorrecto", "Error", JOptionPane.WARNING_MESSAGE );
				} else if ( !contra.equals( confCont ) ) {
					JOptionPane.showMessageDialog( null, "Contraseña mal confirmada", "Error", JOptionPane.WARNING_MESSAGE );
				} else {
					// TODO Conectar a base de datos y meter los datos del usuario en la tabla usuario
					Connection con = BD.conexionBD("RUNNERBD2.db");
					Statement stat = BD.usarCrearTablasBD(con);
					try {
						String sentSQL = "insert into usuario values( '" + txtUsuario.getText() + "', '" + txtNombre.getText() + "', '" + txtApellido.getText() + "', '" + txtMail.getText() + "', 1,686686686, 0, '" + contra + "'" + ");";
						stat.executeUpdate(sentSQL);
						BD.log( Level.INFO, "BD añadida", null );
						JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");
					} catch (SQLException e2) {
						e2.printStackTrace();
						BD.log( Level.SEVERE, "Error en insert a BD", e2 );
					}
					BD.cerrarBD(con, stat);
				}
			}
		});
		
	}
	
	
	//Código de control de datos 
	/** Comprueba si el formato del email es válido
	 * @param email email a comprobar
	 * @return True si es correcto, False de lo contrario
	 */
	public boolean comprobarMail(String email) {
		boolean resultado = false;
		Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		 Matcher mather = pattern.matcher(email);
		 if (mather.find() == true) {
	            resultado = true;
	        } 
		 return resultado;
    }
	
	
	
}
