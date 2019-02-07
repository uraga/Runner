package objetosJuego;



import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Proyecto.VentanasYEventos.*;

/**
 * Clase padre de creacion de obstaculos
 * @author JON URAGA, YERAY BELLANCO
 *
 */

public class GestorObstaculos {
	
	//ATRIBUTOS
	private BufferedImage cactus1;
	private BufferedImage cactus2;
	private Random r;	
	private List<Obstaculo> obstaculos; //lista de obstaculos
	private Personaje personaje; //personaje principal
	
	//CONSTRUCTOR
	public GestorObstaculos(Personaje personaje) {
		r = new Random();
		cactus1 = Img.getResouceImage("utils/cactus1.png");
		cactus2 = Img.getResouceImage("utils/cactus2.png");
		obstaculos = new ArrayList<Obstaculo>(); //ArrayList de obstaculos
		this.personaje = personaje;
		obstaculos.add(crearObstaculo()); //añade obstaculo al arrayList
	}
	
	/**
	 * Metodo que actualiza los obstaculos
	 * @author JON URAGA, YERAY BELLANCO
	 */
	public void actualizar() {
		for(Obstaculo e : obstaculos) {
			e.actualizar();
		}
		Obstaculo obstaculo = obstaculos.get(0);
		if(obstaculo.seSalePorIzquierda()) {
			personaje.sumarPuntos();
			obstaculos.clear();
			obstaculos.add(crearObstaculo());
		}
	}
	
	/**
	 * Metodo para dibujar los obstaculos
	 * @param g
	 * @author JON URAGA, YERAY BELLANCO
	 */
	public void draw(Graphics g) {
		for(Obstaculo e : obstaculos) {
			e.draw(g);
		}
	}
	
	/**
	 * Metodo que crea obstaculo
	 * @author JON URAGA, YERAY BELLANCO
	 */
	private Obstaculo crearObstaculo() {
		int tipo = r.nextInt(2);
		if(tipo == 0) {
			return new Cactus(personaje, 800, cactus1.getWidth() - 10, cactus1.getHeight() - 10, cactus1);
		} else {
			return new Cactus(personaje, 800, cactus2.getWidth() - 10, cactus2.getHeight() - 10, cactus2);
		}
	}
	
	/**
	 * Metodo para el choque del personaje con el obstaculo
	 * @author JON URAGA, YERAY BELLANCO
	 */
	public boolean choca() {
		for(Obstaculo e : obstaculos) {
			if (personaje.getBound().intersects(e.getBound())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Borra y crea nuevos obstaculos
	 * @author JON URAGA, YERAY BELLANCO
	 */
	public void reiniciar() {
		obstaculos.clear();
		obstaculos.add(crearObstaculo());
	}
	
}
