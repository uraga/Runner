package Proyecto.ClasesBasicas;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;


import Proyecto.VentanasYEventos.Animacion;
import Proyecto.VentanasYEventos.Img;

public class Personaje {
	
	private static final int POS_Y = 80;
	private static final float GRAVEDAD = 0.4f;
	private static final int CORRIENDO = 0;
	private static final int SALTANDO = 1;
	private static final int AGACHADO = 2;
	private static final int MUERTO = 3;
	
	private float posY;
	private float posX;
	private float velocidadX;
	private float velocidadY;
	private Rectangle rectanguloChoque;
	public int puntuacion = 0;
	private int estado = CORRIENDO;
	
	private Animacion corriendoAnim;
	private BufferedImage saltando;
	private Animacion agachadoAnim;
	private BufferedImage muerto;
	
	private AudioClip sonidoSalto;
	private AudioClip sonidoChoque;
	private AudioClip sonidoSubeVelocidad;
	
	public Personaje() {
		posX = 50;
		posY = POS_Y;
		rectanguloChoque = new Rectangle();
		corriendoAnim = new Animacion(90);
		corriendoAnim.anyadirImagen(Img.getImagen("data/main-character1.png"));
		corriendoAnim.anyadirImagen(Img.getImagen("data/main-character2.png"));
		saltando = Img.getImagen("data/main-character3.png");
		agachadoAnim = new Animacion(90);
		agachadoAnim.anyadirImagen(Img.getImagen("data/main-character5.png"));
		agachadoAnim.anyadirImagen(Img.getImagen("data/main-character6.png"));
		muerto = Img.getImagen("data/main-character4.png");
		
		try {
			sonidoSalto =  Applet.newAudioClip(new URL("file","","data/jump.wav"));
			sonidoChoque =  Applet.newAudioClip(new URL("file","","data/dead.wav"));
			sonidoSubeVelocidad =  Applet.newAudioClip(new URL("file","","data/scoreup.wav"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
	
	public float getVelocidadX() {
		return velocidadX;
	}
	
	public void setVelocidadX(int velocidadX) {
		this.velocidadX = velocidadX;
	}
	
	public void dibujar(Graphics g) {
		switch (estado) {
			case CORRIENDO:
				g.drawImage(corriendoAnim.getImagen(), (int) posX, (int) posY, null);
				break;
			case SALTANDO:
				g.drawImage(saltando, (int) posX, (int) posY, null);
				break;
			case AGACHADO:
				g.drawImage(agachadoAnim.getImagen(), (int) posX, (int) (posY + 20), null);
				break;
			case MUERTO:
				g.drawImage(muerto, (int) posX, (int) posY, null);
				break;
		}
	}
	
	public void mover() {
		corriendoAnim.actualizarImagen();
		agachadoAnim.actualizarImagen();
		if (posY >= POS_Y) {
			posY = POS_Y;
			if(estado != AGACHADO) {
				estado = CORRIENDO;
			}
		} else {
			velocidadY += GRAVEDAD;
			posY += velocidadY;
		}
	}
	
	public void saltar() {
		if(posY >= POS_Y) {
			if(sonidoSalto != null) {
				sonidoSalto.play();
			}
			velocidadY = -7.5f;
			posY += velocidadY;
			estado = SALTANDO;
		}
	}
	
	public void agachado(boolean estaAgachado) {
		if (estado == SALTANDO) {
			return;
		}
		if(estaAgachado) {
			estado = AGACHADO;
		} else {
			estado = CORRIENDO;
		}
	}
	
	public Rectangle getBorde() {
		rectanguloChoque = new Rectangle();
		if(estado == AGACHADO) {
			rectanguloChoque.x = (int) posX + 5;
			rectanguloChoque.y = (int) posY + 20;
			rectanguloChoque.width = agachadoAnim.getImagen().getWidth() - 10;
			rectanguloChoque.height = agachadoAnim.getImagen().getHeight();
		} else {
			rectanguloChoque.x = (int) posX + 5;
			rectanguloChoque.y = (int) posY;
			rectanguloChoque.width = corriendoAnim.getImagen().getWidth() - 10;
			rectanguloChoque.height = corriendoAnim.getImagen().getHeight();
		}
		return rectanguloChoque;
	}
	
	public void muerto(boolean estaMuerto) {
		if(estaMuerto) {
			estado = MUERTO;
		} else {
			estado = CORRIENDO;
		}
	}
	
	public void reiniciar() {
		posY = POS_Y;
	}
	
	public void activarSonidoChoque() {
		sonidoChoque.play();
	}
	
	public void incPuntuacion() {
		puntuacion += 20;
		if(puntuacion % 100 == 0) {
			sonidoSubeVelocidad.play();
		}
	}
	
}
