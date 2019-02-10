package Proyecto.VentanasYEventos;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;



public class VentanaInicio extends JFrame {
	

	private static final long serialVersionUID = 1L;
	// ---SONIDO--- //
		private AudioClip sonidoFondo;
		

	// Constructor
	public VentanaInicio() {

		// titulo
		setTitle("INICIO - RUNNER");
		getContentPane().setLayout(null);

		// Texto seleccione el juego
		JLabel lblSeleccioneElJuego = new JLabel("BIENVENIDO A RUNNER");
		lblSeleccioneElJuego.setBackground(new Color(0, 0, 0));
		lblSeleccioneElJuego.setForeground(new Color(255, 165, 0));
		lblSeleccioneElJuego.setFont(new Font("Comic Sans MS", Font.PLAIN, 27));
		lblSeleccioneElJuego.setBounds(113, 53, 516, 177);
		getContentPane().add(lblSeleccioneElJuego);
		
		//Checkbox aceptar terminos
		JCheckBox normas = new JCheckBox("He leido las reglas y acepto todos los terminos");
		normas.setForeground(Color.RED);
		normas.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		normas.setBounds(0, 295, 267, 23);
		getContentPane().add(normas);
		
		//Boton de lectura de las normas del Casino
		JButton btnNormas = new JButton("Normas");
		btnNormas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaNormas vNormas = new VentanaNormas();
				vNormas.setVisible(true);
				vNormas.setSize(485, 345);

				// tamaño no ajustable
				vNormas.setResizable(false);

				// centramos
				vNormas.setLocation(600, 300);
			}
		});
		btnNormas.setBounds(0, 232, 63, 63);
		getContentPane().add(btnNormas);

		
		JButton btnJugar = new JButton("JUGAR");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(normas.isSelected()) {
					VentanaUsuario user = new VentanaUsuario ();
					user.setVisible(true); // visible
					user.setSize(450, 300); // tamaño
					user.setResizable(false); // tamaño no ajustable
					user.setLocation(400, 200); // centramos
					sonidoFondo.stop();
					dispose();		
				}
				else {
					JOptionPane.showMessageDialog(null, "Error, debe aceptar los terminos");
				}
				
			}
		});
		btnJugar.setForeground(Color.BLACK);
		btnJugar.setBackground(Color.BLACK);
		btnJugar.setBounds(320, 293, 145, 29);
		getContentPane().add(btnJugar);

		//sonido

		try {
			sonidoFondo =  Applet.newAudioClip(new URL("file","","utils/fondo.wav"));
			sonidoFondo.play();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	
		
		
		

		// Cerramos actividad de la ventana, al pulsar X
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	// main
	public static void main(String[] args) {
		VentanaInicio objeto = new VentanaInicio();

		// Ventana visible
		objeto.setVisible(true);
		objeto.setSize(485, 345);

		// tamaño no ajustable
		objeto.setResizable(false);

		// centramos
		objeto.setLocation(600, 300);

	}


	
}
