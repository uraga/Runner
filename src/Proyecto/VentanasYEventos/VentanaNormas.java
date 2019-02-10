package Proyecto.VentanasYEventos;


import javax.swing.JFrame;

import javax.swing.JTextPane;

/**
 * Clase que contiene las normas de la ruleta
 * 
 * @author Yeray Bellanco
 *
 */
public class VentanaNormas extends JFrame {
	
	
	private static final long serialVersionUID = 1L;

	public VentanaNormas() {
		setTitle("NORMAS - RUNNER\n");
		getContentPane().setLayout(null);
		
		JTextPane txtpnNormasDeLa = new JTextPane();
		txtpnNormasDeLa.setText("Hola bienvenido, estas apunto de jugar a este runner por favor lee antentamente las normas basicas del juego.\n\nEste juego esta basado en el dinosaurio de Google Chrome el cual consiste en ir avanzando e ir saltando los obstaculos que van apareciendo,para ello pulse el boton espacio.\n\nEn caso de choque nuestro personaje Dino muere y se acaba la partida.Cada obstaculo superado supondra un incremento de 20 puntos en la puntuacion,hasta el fin de la partida.");
		txtpnNormasDeLa.setEditable(false);
		txtpnNormasDeLa.setBounds(0, 0, 485, 323);
		getContentPane().add(txtpnNormasDeLa);
	}

	// main
	public static void main(String[] args) {
		VentanaNormas objeto = new VentanaNormas();

		// Ventana visible
		objeto.setVisible(true);
		objeto.setSize(485, 345);

		// tamaño no ajustable
		objeto.setResizable(false);

		// centramos
		objeto.setLocation(600, 300);

	}
}
