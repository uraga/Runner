package Proyecto.VentanasYEventos;

import javax.swing.JFrame;

public class VentanaJuego extends JFrame {
	
	public static final int ANCHO_PANTALLA = 600;
	
	private PanelJuego panelJuego;
	
	public VentanaJuego() {
		super("Juego Runner");
		setSize(ANCHO_PANTALLA, 175);
		setLocation(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		panelJuego = new PanelJuego();
		addKeyListener(panelJuego);
		add(panelJuego);
	}
	
	public void inicioJuego() {
		setVisible(true);
		panelJuego.inicioJuego();
	}
	
	public static void main(String args[]) {
		(new VentanaJuego()).inicioJuego();
	}
}
