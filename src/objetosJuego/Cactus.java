package objetosJuego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Cactus extends Obstaculo {
	
	public static final int POS_Y = 125;
	
	private int posX;
	private int ancho;
	private int alto;
	
	private BufferedImage imagen;
	private Personaje personaje;
	
	private Rectangle rectanguloChoque;
	
	public Cactus(Personaje mainCharacter, int posX, int width, int height, BufferedImage image) {
		this.posX = posX;
		this.ancho = width;
		this.alto = height;
		this.imagen = image;
		this.personaje = mainCharacter;
		rectanguloChoque = new Rectangle();
	}
	
	public void actualizar() {
		posX -= personaje.getVelX();
	}
	
	public void draw(Graphics g) {
		g.drawImage(imagen, posX, POS_Y - imagen.getHeight(), null);
		g.setColor(Color.red);

	}
	
	public Rectangle getBound() {
		rectanguloChoque = new Rectangle();
		rectanguloChoque.x = (int) posX + (imagen.getWidth() - ancho)/2;
		rectanguloChoque.y = POS_Y - imagen.getHeight() + (imagen.getHeight() - alto)/2;
		rectanguloChoque.width = ancho;
		rectanguloChoque.height = alto;
		return rectanguloChoque;
	}

	@Override
	public boolean seSalePorIzquierda() {
		if(posX < -imagen.getWidth()) {
			return true;
		}
		return false;
	}
	
}
