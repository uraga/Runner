package Proyecto.VentanasYEventos;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import objetosJuego.*;




/**
 * Clase que implementa los graficos y la interfaz del teclado (salto y agacharse)
 * @author JON URAGA, YERAY BELLANCO
 *
 */
public class PanelJuego extends JPanel implements Runnable, KeyListener {

	//CONSTANTES
	private static final int INICIANDO_JUEGO = 0;
	private static final int JUGANDO = 1;
	private static final int JUEGO_TERMINADO = 2;
		
	//ATRIBUTOS
	private Suelo suelo; //suelo del juego
	private Personaje personaje; //personaje principal del juego - DINO
	private GestorObstaculos gestorObs; //Gestor de los obstaculos del juego
	private Nubes nubes; //nubes de fondo
	private Thread t;  //Hilo
	private boolean teclaPulsada;  //registro de la tecla pulsada por parte del usuario
	private int estadoJuego = INICIANDO_JUEGO; //Comienzo del juego
	private BufferedImage btnReiniciar; //Reinicio de partida
	private BufferedImage btnGameOver;  //Fin de partida

	//CONSTRUCTOR
	public PanelJuego() {
		personaje = new Personaje(); //Creamos dino
		suelo = new Suelo(VentanaJuego.ANCHO_PANTALLA, personaje); //Creamos suelo
		personaje.setVelX(4); //Velocidad
		btnReiniciar = Img.getResouceImage("utils/botonReplay.png"); //Boton
		btnGameOver = Img.getResouceImage("utils/gameOver.png");//Boton
		gestorObs = new GestorObstaculos(personaje); //Obstaculos
		nubes = new Nubes(VentanaJuego.ANCHO_PANTALLA, personaje); //Nubes de fondo
	}

	/**
	 * Hilo que da comienzo al juego
	 * @author JON URAGA, YERAY BELLANCO
	 */
	public void inicioJuego() {
		t = new Thread(this);
		t.start();
	}

	/**
	 * Metodo para actualizar los graficos del juego
	 * @author JON URAGA, YERAY BELLANCO
	 */
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

	/**
	 * Metodo que construye los graficos del juego
	 * @author JON URAGA, YERAY BELLANCO
	 */
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
			gestorObs.draw(g);
			personaje.dibujar(g);
			g.setColor(Color.BLACK);
			g.drawString("Puntuacion " + personaje.puntuacion, 500, 20);
			if (estadoJuego == JUEGO_TERMINADO) {
				g.drawImage(btnGameOver, 200, 30, null);
				g.drawImage(btnReiniciar, 283, 50, null);
				
			}
			break;
		}
	}

	/**
	 * Metodo run del hilo
	 * @author JON URAGA, YERAY BELLANCO
	 */
	@Override
	public void run() {
		//Atributos
		int fps = 100;
		long msPorFrame = 1000 * 1000000 / fps;
		long tiempoFinal = 0;
		long tiempoTranscurrido;	
		int msSleep;
		int nanoSleep;
		long terminarProceso;
		long retardo = 0;

		while (true) {
			actualizarJuego();
			repaint();
			terminarProceso = System.nanoTime();
			tiempoTranscurrido = (tiempoFinal + msPorFrame - System.nanoTime());
			msSleep = (int) (tiempoTranscurrido / 1000000);
			nanoSleep = (int) (tiempoTranscurrido % 1000000);
			if (msSleep <= 0) {
				tiempoFinal = System.nanoTime();
				continue;
			}
			try {
				Thread.sleep(msSleep, nanoSleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tiempoFinal = System.nanoTime();
		}
	}

	/**
	 * Metodo que registra cuando el usuario da al espacio para hacer saltar el personaje.
	 * @author JON URAGA, YERAY BELLANCO
	 */
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

	/**
	 * Metodo que registra cuando el usuario da a la flecha de abajo para agachar el personaje.
	 * @author JON URAGA, YERAY BELLANCO
	 */
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

	/**
	 * Reinicia el juego, da comienzo a una nueva partida
	 * @author JON URAGA, YERAY BELLANCO
	 */
	private void reiniciarJuego() {
		personaje.puntuacion = 0; 	
		gestorObs.reiniciar();
		personaje.muerto(false);
		personaje.reiniciar();
	}

}
