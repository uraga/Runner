package objetosJuego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Proyecto.VentanasYEventos.*;

public class GestorObstaculos {
	
	private BufferedImage cactus1;
	private BufferedImage cactus2;
	private Random r;
	
	private List<Obstaculo> obstaculos;
	private Personaje personaje;
	
	public GestorObstaculos(Personaje personaje) {
		r = new Random();
		cactus1 = Img.getResouceImage("data/cactus1.png");
		cactus2 = Img.getResouceImage("data/cactus2.png");
		obstaculos = new ArrayList<Obstaculo>();
		this.personaje = personaje;
		obstaculos.add(crearObstaculo());
	}
	
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
	
	public void dibujar(Graphics g) {
		for(Obstaculo e : obstaculos) {
			e.draw(g);
		}
	}
	
	private Obstaculo crearObstaculo() {
		// if (enemyType = getRandom)
		int type = r.nextInt(2);
		if(type == 0) {
			return new Cactus(personaje, 800, cactus1.getWidth() - 10, cactus1.getHeight() - 10, cactus1);
		} else {
			return new Cactus(personaje, 800, cactus2.getWidth() - 10, cactus2.getHeight() - 10, cactus2);
		}
	}
	
	public boolean choca() {
		for(Obstaculo e : obstaculos) {
			if (personaje.getBound().intersects(e.getBound())) {
				return true;
			}
		}
		return false;
	}
	
	public void reiniciar() {
		obstaculos.clear();
		obstaculos.add(crearObstaculo());
	}
	
}
