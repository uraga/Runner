package Proyecto.VentanasYEventos;



import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa la animacion en el juego
 * @author JON URAGA, YERAY BELLANCO
 */


public class Animacion {

	//ATRIBUTOS
	private List<BufferedImage> lista;
	private long tiempoDelta;
	private int frameActual = 0;
	private long tiempoPrevio;

	//CONSTRUCTOR
	public Animacion(int tiempoDelta) {
		this.tiempoDelta = tiempoDelta;
		lista = new ArrayList<BufferedImage>();
		tiempoPrevio = 0;
	}

	/**
	 * Metodo de actualiza los frames
	 * @author JON URAGA, YERAY BELLANCO
	 */
	
	public void updateFrame() {
		if (System.currentTimeMillis() - tiempoPrevio >= tiempoDelta) {
			frameActual++;
			if (frameActual >= lista.size()) {
				frameActual = 0;
			}
			tiempoPrevio = System.currentTimeMillis();
		}
	}
	
	/**
	 * Metodo que añade los frames
	 * @param imagen
	 * @author JON URAGA, YERAY BELLANCO
	 */

	public void addFrame(BufferedImage imagen) {
		lista.add(imagen);
	}
	
	/**
	 * Metodo que obtiene los frames 
	 * @return
	 * @author JON URAGA, YERAY BELLANCO
	 */
	
	public BufferedImage getFrame() {
		return lista.get(frameActual);
	}

}
