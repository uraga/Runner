package Proyecto.VentanasYEventos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Proyecto.ClasesBasicas.GestorObstaculos;
import Proyecto.ClasesBasicas.Personaje;
import Proyecto.ClasesBasicas.Suelo;

public class PanelJuego extends JPanel implements Runnable, KeyListener{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int INICIO_JUEGO = 0;
	private static final int JUGANDO_JUEGO = 1;
	private static final int JUEGO_TERMINADO = 2;
	
	private Suelo suelo;
	private Personaje personaje;
	private GestorObstaculos gestorObs;
	private boolean teclaPulsada;
	private int estadoJuego = INICIO_JUEGO;
	private int i = 0;
	private Thread hilo;
	
	private BufferedImage btnReiniciarImg; 
	private BufferedImage btnFinImg;
	
	public PanelJuego() {
		personaje = new Personaje();
		suelo = new Suelo(VentanaJuego.WIDTH, personaje);
		personaje.setVelocidadX(4);
		btnReiniciarImg = Img.getImagen("data/btn_reiniciar.png");
		btnFinImg = Img.getImagen("data/texto_fin.png");
		gestorObs = new GestorObstaculos(personaje);
	}
	
	public void inicioJuego() {
		hilo = new Thread(this);
		hilo.start();
	}
	
	public void actualizarJuego() {
		if(estadoJuego == JUGANDO_JUEGO) {
			//nubes.actualizarNubes();
			suelo.actualizarSuelo();
			personaje.mover();
			gestorObs.actualizar();
			if(gestorObs.choca()) {
				personaje.activarSonidoChoque();
				estadoJuego = JUEGO_TERMINADO;
				personaje.muerto(true);
			}
		} 
	}
	
	public void dibujar(Graphics g) {
		g.setColor(Color.decode("#f7f7f7"));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		switch(estadoJuego) {
		case INICIO_JUEGO:
			personaje.dibujar(g);
			break;
		case JUGANDO_JUEGO:
		case JUEGO_TERMINADO:
			suelo.dibujar(g);
			gestorObs.dibujar(g);
			personaje.dibujar(g);
			g.setColor(Color.BLACK);
			g.drawString("P" + personaje.puntuacion, 500, 20);
			if(estadoJuego == JUEGO_TERMINADO) {
				g.drawImage(btnFinImg, 200, 30, null);
				g.drawImage(btnReiniciarImg, 283, 50, null);
			}
			break;
		}
	}
	
	@Override
	public void run() {
		int fps = 100;
		long msPorImg = 1000 * 1000000 / fps;
		long tiempoAnterior = 0;
		long transcurrido;
		int msSleep;
		int nanoSleep;
		
		while(true) {
			actualizarJuego();
			repaint();
			transcurrido = Math.abs(tiempoAnterior + msPorImg - System.nanoTime());
			msSleep = (int) Math.abs(transcurrido / 1000000);
			nanoSleep = (int) (transcurrido % 1000000);
			if(msSleep <= 0) {
				tiempoAnterior = System.nanoTime();
			}
			try {
				Thread.sleep(msSleep, nanoSleep);;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tiempoAnterior = System.nanoTime();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	private void reiniciarJuego() {
		gestorObs.reiniciar();
		personaje.muerto(false);
		personaje.reiniciar();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(!teclaPulsada) {
			teclaPulsada = true;
			switch (estadoJuego) {
			case INICIO_JUEGO:
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					estadoJuego = JUGANDO_JUEGO;
				}
				break;
			case JUGANDO_JUEGO:
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					personaje.saltar();
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					personaje.agachado(true);
				}
				break;
			case JUEGO_TERMINADO:
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					estadoJuego = JUGANDO_JUEGO;
					reiniciarJuego();
				}
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		teclaPulsada = false;
		if(estadoJuego == JUGANDO_JUEGO) {
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				personaje.agachado(false);
			}
		}
	}

}
