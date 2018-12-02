package Proyecto.ClasesBasicas;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Proyecto.VentanasYEventos.Img;

public class GestorObstaculos {
	
	private BufferedImage cactus1;
	private BufferedImage cactus2;
	private Random r;
	private List<Obstaculo> obstaculos;
	private Personaje personaje;
	
	public GestorObstaculos(Personaje personaje) {
		r = new Random();
		cactus1 = Img.getImagen("data/cactus1.png");
		cactus2 = Img.getImagen("data/cactus2.png");
		obstaculos = new ArrayList<Obstaculo>();
		this.personaje = personaje;
		obstaculos.add(crearObstaculo());
	}
	
	public void actualizar() {
		for(Obstaculo o : obstaculos) {
			o.actualizar();
		}
		Obstaculo obstaculo = obstaculos.get(0);
		if(obstaculo.salePorIzquierda()) {
			personaje.incPuntuacion();
			obstaculos.clear();
			obstaculos.add(crearObstaculo());
		}
	}
	
	public void dibujar(Graphics g) {
		for(Obstaculo o : obstaculos) {
			o.dibujar(g);
		}
	}
	
	private Obstaculo crearObstaculo() {
		int tipo = r.nextInt(2);
		if(tipo == 0) {
			return new Cactus(personaje, 800, cactus1.getWidth() - 10, cactus1.getHeight() - 10, cactus1);
		}else {
			return new Cactus(personaje, 800, cactus2.getWidth() - 10, cactus2.getHeight() - 10, cactus2);
		}
	}
	
	public boolean choca() {
		for(Obstaculo o : obstaculos) {
			if(personaje.getBorde().intersects(o.getBorde())) {
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