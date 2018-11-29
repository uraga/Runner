package Proyecto.VentanasYEventos;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Proyecto.ClasesBasicas.DeustoRunner;

public class EventoTeclado implements KeyListener {

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		DeustoRunner.saltar();
	}

	public void keyReleased(KeyEvent e) {
	}
	
	
	

}
