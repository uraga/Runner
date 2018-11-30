package Proyecto.VentanasYEventos;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Proyecto.ClasesBasicas.DeustoRunner;

public class EventoTeclado implements KeyListener {

	public void keyTyped(KeyEvent e) {
		System.out.println("h");
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			DeustoRunner.saltar();
		}
	}

	public void keyPressed(KeyEvent e) {
		
    }

	public void keyReleased(KeyEvent e) {
	}
	
	
	

}
