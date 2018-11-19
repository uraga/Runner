package Proyecto.ClasesBasicas;

import java.io.Serializable;


import javafx.scene.effect.Light.Point;

public class DeustoRunner implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int altura;
	protected int anchura;
	protected double posX;
	protected double posY;
	protected long tiempoMovimiento;
	public static final double VEL_SALTO = 300;
	protected double velHaciaArriba = 0D;
	protected boolean estoyMuerto = false;
	
	
	public DeustoRunner(int altura, int anchura, double posX, double posY, long tiempoMovimiento) {
		this.altura = altura;
		this.anchura = anchura;
		this.posX = posX;
		this.posY = posY;
		this.tiempoMovimiento = System.currentTimeMillis();
	}


	public int getAltura() {
		return altura;
	}


	public int getAnchura() {
		return anchura;
	}


	public double getPosX() {
		return posX;
	}


	public double getPosY() {
		return posY;
	}
	
	public void setPosicion( double posX, double posY ) {
		this.posX = posX;
		this.posY = posY;
	}
		
	
	public void mover() {
		
	}
	
	public void quitar() {
		
	}
	
	public void saltar() {
		velHaciaArriba = VEL_SALTO;
	}
	
	private static void controlDeJugador (EventoVentana ev, DeustoRunner dr) {
		if (ev != null) {
			if (ev instanceof TeclaPulsada) {
				dr.saltar();
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
