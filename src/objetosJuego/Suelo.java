package objetosJuego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import Proyecto.VentanasYEventos.*;

/**
 * Clase que establece el juego del suelo
 * @author JON URAGA, YERAY BELLANCO
 *
 */

public class Suelo {
	
	//ATRIBUTOS
	public static final int SUELO_POSY = 103;	//Posicion vertical
	private List<ImagenSuelo> listaSuelo; //lista del suelo
	private BufferedImage suelo1;
	private BufferedImage suelo2;
	private BufferedImage suelo3;
	private Personaje personaje;
	
	//CONSTRUCTOR
	public Suelo(int ancho, Personaje personaje) {
		this.personaje = personaje;
		suelo1 = Img.getResouceImage("utils/suelo1.png");
		suelo2 = Img.getResouceImage("utils/suelo2.png");
		suelo3 = Img.getResouceImage("utils/suelo3.png");
		int numeroImagenSuelo = ancho / suelo1.getWidth() + 2;
		listaSuelo = new ArrayList<ImagenSuelo>();
		for(int i = 0; i < numeroImagenSuelo; i++) {
			ImagenSuelo imagenSuelo = new ImagenSuelo();
			imagenSuelo.posX = i * suelo1.getWidth();
			setImagenSuelo(imagenSuelo);
			listaSuelo.add(imagenSuelo);
		}
	}
	
	/**
	 * Metodo que actualiza el suelo en funcion avanza la partida
	 * @author JON URAGA, YERAY BELLANCO
	 */
	public void actualizar(){
		Iterator<ImagenSuelo> itr = listaSuelo.iterator();
		ImagenSuelo elemento = itr.next();
		elemento.posX -= personaje.getVelX();
		float posXAnterior = elemento.posX;
		while(itr.hasNext()) {
			ImagenSuelo element = itr.next();
			element.posX = posXAnterior + suelo1.getWidth();
			posXAnterior = element.posX;
		}
		if(elemento.posX < -suelo1.getWidth()) {
			listaSuelo.remove(elemento);
			elemento.posX = posXAnterior + suelo1.getWidth();
			setImagenSuelo(elemento);
			listaSuelo.add(elemento);
		}
	}
	
	/**
	 * Metodo que establece los tres tipo de suelo
	 * @author JON URAGA, YERAY BELLANCO
	 */
	private void setImagenSuelo(ImagenSuelo ImagenSuelo) {
		int tipoSuelo = getTipoSuelo();
		if(tipoSuelo == 1) {
			ImagenSuelo.imagen = suelo1;
		} else if(tipoSuelo == 3) {
			ImagenSuelo.imagen = suelo3;
		} else {
			ImagenSuelo.imagen = suelo2;
		}
	}
	
	/**
	 * Metodo que dibuja el suelo 
	 * @author JON URAGA, YERAY BELLANCO
	 */
	public void dibujar(Graphics g) {
		for(ImagenSuelo imagenSuelo : listaSuelo) {
			g.drawImage(imagenSuelo.imagen, (int) imagenSuelo.posX, SUELO_POSY, null);
		}
	}
	
	/**
	 * Metodo que elije entre los tres tipo de suelo
	 * @author JON URAGA, YERAY BELLANCO
	 */
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
	
	//CLASE INTERNA IMAGEN DEL SUELO (POS,IMAGEN)
	private class ImagenSuelo {
		float posX;
		BufferedImage imagen;
	}
	
}
