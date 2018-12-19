package objetosJuego;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import Proyecto.VentanasYEventos.*;

public class Personaje {

	public static final int SUELO_POS_Y = 80;
	public static final float GRAVEDAD = 0.4f;
	
	private static final int CORRIENDO = 0;
	private static final int SALTANDO = 1;
	private static final int AGACHANDO = 2;
	private static final int MUERTO = 3;
	
	private float posY;
	private float posX;
	private float velX;
	private float velY;
	private Rectangle rectangulo;
	
	public int puntuacion = 0;
	
	private int estado = CORRIENDO;
	
	private Animacion corriendoAnim;
	private BufferedImage saltando;
	private Animacion agachadoAnim;
	private BufferedImage muertoImg;
	
	private AudioClip sonidoSalto;
	private AudioClip sonidoMuerto;
	private AudioClip sonidoPuntos;
	
	public Personaje() {
		posX = 50;
		posY = SUELO_POS_Y;
		rectangulo = new Rectangle();
		corriendoAnim = new Animacion(90);
		corriendoAnim.addFrame(Img.getResouceImage("data/main-character1.png"));
		corriendoAnim.addFrame(Img.getResouceImage("data/main-character2.png"));
		saltando = Img.getResouceImage("data/main-character3.png");
		agachadoAnim = new Animacion(90);
		agachadoAnim.addFrame(Img.getResouceImage("data/main-character5.png"));
		agachadoAnim.addFrame(Img.getResouceImage("data/main-character6.png"));
		muertoImg = Img.getResouceImage("data/main-character4.png");
		
		try {
			sonidoSalto =  Applet.newAudioClip(new URL("file","","data/jump.wav"));
			sonidoMuerto =  Applet.newAudioClip(new URL("file","","data/dead.wav"));
			sonidoPuntos =  Applet.newAudioClip(new URL("file","","data/scoreup.wav"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}
	
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
//		Rectangle bound = getBound();
//		g.setColor(Color.RED);
//		g.drawRect(bound.x, bound.y, bound.width, bound.height);
	}
	
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
	
	public void agacharse(boolean isDown) {
		if(estado == SALTANDO) {
			return;
		}
		if(isDown) {
			estado = AGACHANDO;
		} else {
			estado = CORRIENDO;
		}
	}
	
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
	
	public void muerto(boolean isDeath) {
		if(isDeath) {
			estado = MUERTO;
		} else {
			estado = CORRIENDO;
		}
	}
	
	public void reiniciar() {
		posY = SUELO_POS_Y;
	}
	
	public void sonidoMuere() {
		sonidoMuerto.play();
	}
	
	public void sumarPuntos() {
		puntuacion += 20;
		if(puntuacion % 100 == 0) {
			sonidoPuntos.play();
		}
	}
	
}
