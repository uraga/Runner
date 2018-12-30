package objetosJuego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
/**
 * clase hija para la creacion de obstaculos tipo cactus
 * @author JON URAGA, YERAY BELLANCO
 *
 */
public class Cactus extends Obstaculo {
	
	//ATRIBUTOS
	public static final int POS_Y = 125; //Posicion vertical
	private int posX; //Posicion horizontal
	private int ancho; 
	private int alto;	
	private BufferedImage imagen;
	private Personaje personaje;	
	private Rectangle rectanguloChoque;
	
	//CONSTRUCTOR
	public Cactus(Personaje mainCharacter, int posX, int width, int height, BufferedImage image) {
		this.posX = posX;
		this.ancho = width;
		this.alto = height;
		this.imagen = image;
		this.personaje = mainCharacter;
		rectanguloChoque = new Rectangle();
	}
	
	/**
	 * Metodo que actualiza el obstaculo
	 * @author JON URAGA, YERAY BELLANCO
	 */
	public void actualizar() {
		posX -= personaje.getVelX();
	}
	
	/**
	 * Metodo que construye el obstaculo
	 * @author JON URAGA, YERAY BELLANCO
	 */
	public void draw(Graphics g) {
		g.drawImage(imagen, posX, POS_Y - imagen.getHeight(), null);
		g.setColor(Color.red);

	}
	
	/**
	 * Metodo que hace el calculo del choque con rectangulos
	 * @author JON URAGA, YERAY BELLANCO
	 */
	
	public Rectangle getBound() {
		rectanguloChoque = new Rectangle();
		rectanguloChoque.x = (int) posX + (imagen.getWidth() - ancho)/2;
		rectanguloChoque.y = POS_Y - imagen.getHeight() + (imagen.getHeight() - alto)/2;
		rectanguloChoque.width = ancho;
		rectanguloChoque.height = alto;
		return rectanguloChoque;
	}

	/**
	 * Metodo para que se empieze por la izquierda (de izq a derecha)
	 * @author JON URAGA, YERAY BELLANCO
	 */
	@Override
	public boolean seSalePorIzquierda() {
		if(posX < -imagen.getWidth()) {
			return true;
		}
		return false;
	}
	
}
