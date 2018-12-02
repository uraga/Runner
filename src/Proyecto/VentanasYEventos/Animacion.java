package Proyecto.VentanasYEventos;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animacion {
	
	private List<BufferedImage> lista;
	private long tiempoActual;
	private int imagenActual = 0;
	private long tiempoAnterior;
	
	public Animacion(int tiempoActual) {
		this.tiempoActual = tiempoActual;
		lista = new ArrayList<BufferedImage>();
		tiempoAnterior = 0;
	}
	
	public void actualizarImagen() {
		if (System.currentTimeMillis() - tiempoAnterior >= tiempoActual) {
			imagenActual++;
			if (imagenActual >= lista.size()) {
				imagenActual = 0;
			}
			tiempoAnterior = System.currentTimeMillis();
		}
	}
	
	public void anyadirImagen(BufferedImage imagen) {
		lista.add(imagen);
	}
	
	public BufferedImage getImagen() {
		return lista.get(imagenActual);
	}

}
