package Proyecto.VentanasYEventos;

import javax.swing.JFrame;

/**
 * VENTANA PRINCIPAL DEL JUEGO
 * @author JON URAGA, YERAY BELLANCO
 *
 */

public class VentanaJuego extends JFrame {
	
	//CONSTANTES
	private static final long serialVersionUID = 1L;
	public static final int ANCHO_PANTALLA = 600;
	
	//ATRIBUTOS
	private PanelJuego panelJuego;
	
	//CONSTRUCTOR
	public VentanaJuego() {
		super("Juego Runner"); //Titulo 
		setSize(ANCHO_PANTALLA, 175); //Tamaño
		setLocation(400, 200); //Posicion
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		//PANEL
		panelJuego = new PanelJuego();
		addKeyListener(panelJuego);
		add(panelJuego);
	}
	
	/**
	 * Metodo para dar comienzo al juego, dentro de el panel de juego
	 * @author JON URAGA, YERAY BELLANCO
	 */
	public void inicioJuego() {
		setVisible(true);
		panelJuego.inicioJuego();
	}
	
	//LLAMADA AL INICIO DE JUEGO
	public static void main(String args[]) {
		(new VentanaJuego()).inicioJuego();
	}
}
