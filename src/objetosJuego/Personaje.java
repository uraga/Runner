package objetosJuego;



import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import Proyecto.VentanasYEventos.*;


/**
 * Clase para el personaje principal DINO
 * @author JON URAGA, YERAY BELLANCO
 */

public class Personaje {

	//CONSTANTES
	public static final int SUELO_POS_Y = 80; //Posicion vertical del suelo
	public static final float GRAVEDAD = 0.4f;
	private static final int CORRIENDO = 0;
	private static final int SALTANDO = 1;
	private static final int AGACHANDO = 2;
	private static final int MUERTO = 3;
	
	//ATRIBUTOS
	private float posY; //Posicion vertical del personaje
	private float posX; //Posicion horizontal del personaje
	private float velX; //Velocidad x del personaje
	private float velY; //Velocidad y del personaje
	private Rectangle rectangulo;
	public int puntuacion = 0; //Puntuacion inicial	
	private int estado = CORRIENDO;	
	// ---ANIMACION--- //
	private Animacion corriendoAnim;
	private BufferedImage saltando;
	private Animacion agachadoAnim;
	private BufferedImage muertoImg;	
	// ---SONIDO--- //
	private AudioClip sonidoSalto;
	private AudioClip sonidoMuerto;
	private AudioClip sonidoPuntos;
	
	//CONSTRUCTOR
	public Personaje() {
		posX = 50;
		posY = SUELO_POS_Y;
		rectangulo = new Rectangle();
		corriendoAnim = new Animacion(90);
		corriendoAnim.addFrame(Img.getResouceImage("utils/dino1.png"));
		corriendoAnim.addFrame(Img.getResouceImage("utils/dino2.png"));
		saltando = Img.getResouceImage("utils/dino3.png");
		agachadoAnim = new Animacion(90);
		agachadoAnim.addFrame(Img.getResouceImage("utils/dino5.png"));
		agachadoAnim.addFrame(Img.getResouceImage("utils/dino6.png"));
		muertoImg = Img.getResouceImage("utils/dino4.png");
		
		try {
			sonidoSalto =  Applet.newAudioClip(new URL("file","","utils/salto.wav"));
			sonidoMuerto =  Applet.newAudioClip(new URL("file","","utils/sonidoMuerte.wav"));
			sonidoPuntos =  Applet.newAudioClip(new URL("file","","utils/sumarPuntos.wav"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	//GET Y SET
	public float getVelX() {
		return velX;
	}

	public void setVelX(int speedX) {
		this.velX = speedX;
	}
	
	/**
	 * Metodo que construye el personaje
	 * @param g
	 * @author JON URAGA, YERAY BELLANCO
	 */
	public void dibujar(Graphics g) {
		switch(estado) {
			case CORRIENDO:
				g.drawImage(corriendoAnim.getFrame(), (int) posX, (int) posY, null);
				break;
			case SALTANDO:
				g.drawImage(saltando, (int) posX, (int) posY, null);
				break;
			case AGACHANDO:
				g.drawImage(agachadoAnim.getFrame(), (int) posX, (int) (posY + 20), null);
				break;
			case MUERTO:
				g.drawImage(muertoImg, (int) posX, (int) posY, null);
				break;
		}

		
	}
	
	/**
	 * Metodo que actualiza el personaje segun va avanzando
	 * @author JON URAGA, YERAY BELLANCO
	 */
	public void actualizar() {
		corriendoAnim.updateFrame();
		agachadoAnim.updateFrame();
		if(posY >= SUELO_POS_Y) {
			posY = SUELO_POS_Y;
			if(estado != AGACHANDO) {
				estado = CORRIENDO;
			}
		} else {
			velY += GRAVEDAD;
			posY += velY;
		}
	}
	
	/**
	 * Metodo de salto del personaje
	 * @author JON URAGA, YERAY BELLANCO
	 */
	public void saltar() {
		if(posY >= SUELO_POS_Y) {
			if(sonidoSalto != null) {
				sonidoSalto.play();
			}
			velY = -7.5f;
			posY += velY;
			estado = SALTANDO;
		}
	}
	
	/**
	 * Metodo de agacharse del personaje
	 * @author JON URAGA, YERAY BELLANCO
	 */
	public void agacharse(boolean agacharse) {
		if(estado == SALTANDO) {
			return;
		}
		if(agacharse) {
			estado = AGACHANDO;
		} else {
			estado = CORRIENDO;
		}
	}
	
	///RECTANGULO///
	
	public Rectangle getBound() {
		rectangulo = new Rectangle();
		if(estado == AGACHANDO) {
			rectangulo.x = (int) posX + 5;
			rectangulo.y = (int) posY + 20;
			rectangulo.width = agachadoAnim.getFrame().getWidth() - 10;
			rectangulo.height = agachadoAnim.getFrame().getHeight();
		} else {
			rectangulo.x = (int) posX + 5;
			rectangulo.y = (int) posY;
			rectangulo.width = corriendoAnim.getFrame().getWidth() - 10;
			rectangulo.height = corriendoAnim.getFrame().getHeight();
		}
		return rectangulo;
	}
	
	///MUERTE //////
	public void muerto(boolean muere) {
		if(muere) {
			estado = MUERTO;
		} else {
			estado = CORRIENDO;
		}
	}
	
	public void reiniciar() {
		posY = SUELO_POS_Y;
	}
	
	///SONIDO///
	
	/**
	 * Sonido al morir
	 * @author JON URAGA, YERAY BELLANCO
	 */
	public void sonidoMuere() {
		sonidoMuerto.play();
	}
	
	/**
	 * Sonido al sumar puntos
	 * @author JON URAGA, YERAY BELLANCO
	 */
	public void sumarPuntos() {
		puntuacion += 20;
		if(puntuacion % 100 == 0) {
			sonidoPuntos.play();
		}
	}
	
}
