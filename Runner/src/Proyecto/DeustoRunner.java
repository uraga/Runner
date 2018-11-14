package Proyecto;

import java.io.Serializable;

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
	
	public void mover() {
		
	}
	
	public void saltar() {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
