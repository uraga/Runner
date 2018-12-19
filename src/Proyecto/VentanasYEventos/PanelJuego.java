package Proyecto.VentanasYEventos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import objetosJuego.GestorObstaculos;
import objetosJuego.Nubes;
import objetosJuego.Personaje;
import objetosJuego.Suelo;


public class PanelJuego extends JPanel implements Runnable, KeyListener {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int INICIANDO_JUEGO = 0;
	private static final int JUGANDO = 1;
	private static final int JUEGO_TERMINADO = 2;
	
	private Suelo suelo;
	private Personaje personaje;
	private GestorObstaculos gestorObs;
	private Nubes nubes;
	private Thread t;

	private boolean teclaPulsada;

	private int estadoJuego = INICIANDO_JUEGO;

	private BufferedImage btnReiniciar;
	private BufferedImage btnGameOver;

	public PanelJuego() {
		personaje = new Personaje();
		suelo = new Suelo(VentanaJuego.ANCHO_PANTALLA, personaje);
		personaje.setVelX(4);
		btnReiniciar = Img.getResouceImage("data/replay_button.png");
		btnGameOver = Img.getResouceImage("data/gameover_text.png");
		gestorObs = new GestorObstaculos(personaje);
		nubes = new Nubes(VentanaJuego.ANCHO_PANTALLA, personaje);
	}

	public void inicioJuego() {
		t = new Thread(this);
		t.start();
	}

	public void actualizarJuego() {
		if (estadoJuego == JUGANDO) {
			nubes.actualizar();
			suelo.actualizar();
			personaje.actualizar();
			gestorObs.actualizar();
			if (gestorObs.choca()) {
				personaje.sonidoMuere();
				estadoJuego = JUEGO_TERMINADO;
				personaje.muerto(true);
			}
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.decode("#f7f7f7"));
		g.fillRect(0, 0, getWidth(), getHeight());

		switch (estadoJuego) {
		case INICIANDO_JUEGO:
			personaje.dibujar(g);
			break;
		case JUGANDO:
		case JUEGO_TERMINADO:
			nubes.dibujar(g);
			suelo.dibujar(g);
			gestorObs.dibujar(g);
			personaje.dibujar(g);
			g.setColor(Color.BLACK);
			g.drawString("HI " + personaje.puntuacion, 500, 20);
			if (estadoJuego == JUEGO_TERMINADO) {
				g.drawImage(btnGameOver, 200, 30, null);
				g.drawImage(btnReiniciar, 283, 50, null);
			}
			break;
		}
	}

	@Override
	public void run() {

		int fps = 100;
		long msPerFrame = 1000 * 1000000 / fps;
		long lastTime = 0;
		long elapsed;
		
		int msSleep;
		int nanoSleep;

		long endProcessGame;
		long lag = 0;

		while (true) {
			actualizarJuego();
			repaint();
			endProcessGame = System.nanoTime();
			elapsed = Math.abs(lastTime + msPerFrame - System.nanoTime());
			msSleep = (int) Math.abs(elapsed / 1000000);
			nanoSleep = (int) (elapsed % 1000000);
			if (msSleep <= 0) {
				lastTime = System.nanoTime();
				continue;
			}
			try {
				Thread.sleep(msSleep, nanoSleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lastTime = System.nanoTime();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!teclaPulsada) {
			teclaPulsada = true;
			switch (estadoJuego) {
			case INICIANDO_JUEGO:
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					estadoJuego = JUGANDO;
				}
				break;
			case JUGANDO:
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					personaje.saltar();
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					personaje.agacharse(true);
				}
				break;
			case JUEGO_TERMINADO:
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					estadoJuego = JUGANDO;
					reiniciarJuego();
				}
				break;

			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		teclaPulsada = false;
		if (estadoJuego == JUGANDO) {
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				personaje.agacharse(false);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	private void reiniciarJuego() {
		gestorObs.reiniciar();
		personaje.muerto(false);
		personaje.reiniciar();
	}

}
