package Proyecto.ClasesBasicas;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import Proyecto.VentanasYEventos.Img;

public class Suelo {
	
	public static final int SUELO_POS_Y = 103;
	
	private List<ImagenSuelo> listaSuelos;
	private BufferedImage suelo1;
	private BufferedImage suelo2;
	private BufferedImage suelo3;
	
	private Personaje personaje;
	
	public Suelo(int ancho, Personaje personaje) {
		this.personaje = personaje;
		suelo1 = Img.getImagen("data/land1.png");
		suelo2 = Img.getImagen("data/land2.png");
		suelo3 = Img.getImagen("data/land3.png");
		int numeroImagen = ancho / suelo1.getWidth() + 2;
		listaSuelos = new ArrayList<ImagenSuelo>();
		for(int i=0; i < numeroImagen; i++) {
			ImagenSuelo imagenSuelo = new ImagenSuelo();
			imagenSuelo.posX = i * suelo1.getWidth();
			setImagenSuelo(imagenSuelo);
			listaSuelos.add(imagenSuelo);
		}
		
	}
	
	public void actualizarSuelo() {
		Iterator<ImagenSuelo> itr = listaSuelos.iterator();
		ImagenSuelo primeraImg = itr.next();
		primeraImg.posX -= personaje.getVelocidadX();
		float posicionAnterior = primeraImg.posX;
		while(itr.hasNext()) {
			ImagenSuelo imagen = itr.next(); 
			imagen.posX = posicionAnterior + suelo1.getWidth();
			posicionAnterior = imagen.posX;
		}
		if(primeraImg.posX < -suelo1.getWidth()) {
			listaSuelos.remove(primeraImg);
			primeraImg.posX = posicionAnterior + suelo1.getWidth();
			setImagenSuelo(primeraImg);
			listaSuelos.add(primeraImg);
		}
	}
	
	private void setImagenSuelo(ImagenSuelo imagenSuelo) {
		int tipoSuelo = getTipoSuelo();
		if(tipoSuelo == 1){
			imagenSuelo.imagen = suelo1;
		} else if(tipoSuelo == 2) {
			imagenSuelo.imagen = suelo2;
		} else {
			imagenSuelo.imagen = suelo3;
		}
	}

	private int getTipoSuelo() {
		Random r = new Random();
		int tipo = r.nextInt();
		if(tipo == 1) {
			return 1;
		}else if(tipo == 9) {
			return 3;
		}else {
			return 2;
		}
		
	}
	
	public void dibujar(Graphics g) {
		for (ImagenSuelo imagenSuelo : listaSuelos) {
			g.drawImage(imagenSuelo.imagen, (int) imagenSuelo.posX, SUELO_POS_Y, null);
		}
	}

	private class ImagenSuelo{
		float posX;
		BufferedImage imagen;
	}

}
