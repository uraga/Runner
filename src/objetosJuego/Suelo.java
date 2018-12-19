package objetosJuego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import Proyecto.VentanasYEventos.*;

public class Suelo {
	
	public static final int LAND_POSY = 103;
	
	private List<ImagenSuelo> listaSuelo;
	private BufferedImage suelo1;
	private BufferedImage suelo2;
	private BufferedImage suelo3;
	
	private Personaje personaje;
	
	public Suelo(int ancho, Personaje personaje) {
		this.personaje = personaje;
		suelo1 = Img.getResouceImage("data/land1.png");
		suelo2 = Img.getResouceImage("data/land2.png");
		suelo3 = Img.getResouceImage("data/land3.png");
		int numeroImagenSuelo = ancho / suelo1.getWidth() + 2;
		listaSuelo = new ArrayList<ImagenSuelo>();
		for(int i = 0; i < numeroImagenSuelo; i++) {
			ImagenSuelo imagenSuelo = new ImagenSuelo();
			imagenSuelo.posX = i * suelo1.getWidth();
			setImagenSuelo(imagenSuelo);
			listaSuelo.add(imagenSuelo);
		}
	}
	
	public void actualizar(){
		Iterator<ImagenSuelo> itr = listaSuelo.iterator();
		ImagenSuelo firstElement = itr.next();
		firstElement.posX -= personaje.getVelX();
		float previousPosX = firstElement.posX;
		while(itr.hasNext()) {
			ImagenSuelo element = itr.next();
			element.posX = previousPosX + suelo1.getWidth();
			previousPosX = element.posX;
		}
		if(firstElement.posX < -suelo1.getWidth()) {
			listaSuelo.remove(firstElement);
			firstElement.posX = previousPosX + suelo1.getWidth();
			setImagenSuelo(firstElement);
			listaSuelo.add(firstElement);
		}
	}
	
	private void setImagenSuelo(ImagenSuelo imagenSuelo) {
		int typeLand = getTipoSuelo();
		if(typeLand == 1) {
			imagenSuelo.imagen = suelo1;
		} else if(typeLand == 3) {
			imagenSuelo.imagen = suelo3;
		} else {
			imagenSuelo.imagen = suelo2;
		}
	}
	
	public void dibujar(Graphics g) {
		for(ImagenSuelo imagenSuelo : listaSuelo) {
			g.drawImage(imagenSuelo.imagen, (int) imagenSuelo.posX, LAND_POSY, null);
		}
	}
	
	private int getTipoSuelo() {
		Random r = new Random();
		int tipo = r.nextInt(10);
		if(tipo == 1) {
			return 1;
		} else if(tipo == 9) {
			return 3;
		} else {
			return 2;
		}
	}
	
	private class ImagenSuelo {
		float posX;
		BufferedImage imagen;
	}
	
}
