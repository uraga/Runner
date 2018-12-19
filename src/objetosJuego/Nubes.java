package objetosJuego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Proyecto.VentanasYEventos.*;


public class Nubes {
	private List<ImagenNube> listaNubes;
	private BufferedImage nube;
	
	private Personaje personaje;
	
	public Nubes(int ancho, Personaje personaje) {
		this.personaje = personaje;
		nube = Img.getResouceImage("data/cloud.png");
		listaNubes = new ArrayList<ImagenNube>();
		
		ImagenNube imagenNube = new ImagenNube();
		imagenNube.posX = 0;
		imagenNube.posY = 30;
		listaNubes.add(imagenNube);
		
		imagenNube = new ImagenNube();
		imagenNube.posX = 150;
		imagenNube.posY = 40;
		listaNubes.add(imagenNube);
		
		imagenNube = new ImagenNube();
		imagenNube.posX = 300;
		imagenNube.posY = 50;
		listaNubes.add(imagenNube);
		
		imagenNube = new ImagenNube();
		imagenNube.posX = 450;
		imagenNube.posY = 20;
		listaNubes.add(imagenNube);
		
		imagenNube = new ImagenNube();
		imagenNube.posX = 600;
		imagenNube.posY = 60;
		listaNubes.add(imagenNube);
	}
	
	public void actualizar(){
		Iterator<ImagenNube> itr = listaNubes.iterator();
		ImagenNube firstElement = itr.next();
		firstElement.posX -= personaje.getVelX()/8;
		while(itr.hasNext()) {
			ImagenNube element = itr.next();
			element.posX -= personaje.getVelX()/8;
		}
		if(firstElement.posX < -nube.getWidth()) {
			listaNubes.remove(firstElement);
			firstElement.posX = VentanaJuego.ANCHO_PANTALLA;
			listaNubes.add(firstElement);
		}
	}
	
	public void dibujar(Graphics g) {
		for(ImagenNube imgLand : listaNubes) {
			g.drawImage(nube, (int) imgLand.posX, imgLand.posY, null);
		}
	}
	
	private class ImagenNube {
		float posX;
		int posY;
	}
}
