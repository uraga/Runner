package objetosJuego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Proyecto.VentanasYEventos.*;

/**
 * Clase para la visualizacion de nubes de fondo
 * @author JON URAGA, YERAY BELLANCO
 */

public class Nubes {
	
	//ATRIBUTOS
	private List<ImagenNube> listaNubes; //lista de nubes
	private BufferedImage nube; //nube
	private Personaje personaje; //personaje principal
	
	//CONSTRUCTOR
	public Nubes(int ancho, Personaje personaje) {
		this.personaje = personaje;
		nube = Img.getResouceImage("utils/nube.png");
		listaNubes = new ArrayList<ImagenNube>(); //ArrayList de nubes
		
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
	
	/**
	 * Metodo de actualizacion de nubes
	 * @author JON URAGA, YERAY BELLANCO
	 */
	public void actualizar(){
		Iterator<ImagenNube> itr = listaNubes.iterator();
		ImagenNube elemento = itr.next();
		elemento.posX -= personaje.getVelX()/8;
		while(itr.hasNext()) {
			ImagenNube element = itr.next();
			element.posX -= personaje.getVelX()/8;
		}
		if(elemento.posX < -nube.getWidth()) {
			listaNubes.remove(elemento);
			elemento.posX = VentanaJuego.ANCHO_PANTALLA;
			listaNubes.add(elemento);
		}
	}
	
	/**
	 * Metodo para dibujar las nubes
	 * @param g
	 * @author JON URAGA, YERAY BELLANCO
	 */
	public void dibujar(Graphics g) {
		for(ImagenNube imgLand : listaNubes) {
			g.drawImage(nube, (int) imgLand.posX, imgLand.posY, null);
		}
	}
	
	/**
	 * Clase interna de la imagen de la nube
	 * @author JON URAGA, YERAY BELLANCO
	 */
	private class ImagenNube {
		float posX;
		int posY;
	}
}
