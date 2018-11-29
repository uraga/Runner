package Proyecto.ClasesBasicas;

import java.awt.Point;
import Proyecto.VentanasYEventos.VentanaGrafica;


abstract public class ObjetoPantalla {
	protected VentanaGrafica ventana;
	protected int ancho;
	protected int alto;
	protected double posX;
	protected double posY;
	protected long tiempoMovimiento;
	
	
	public ObjetoPantalla(int posX, int posY, int ancho, int alto, VentanaGrafica ventana) {
		this.ventana = ventana;
		this.ancho = ancho;
		this.alto = alto;
		this.posX = posX;
		this.posY = posY;
		this.tiempoMovimiento = System.currentTimeMillis();
	}
	
	public double getX() {
		return posX;
	}
	
	public double getY() {
		return posY;
	}
	
	public int getAncho() {
		return ancho;
	}
	
	public int getAlto() {
		return alto;
	}
	
	public void setPosicion( Point p ) {
		posX = p.getX();
		posY = p.getY();
	}
	
	abstract public void quitar();
	
	abstract public void mover();
	
	public boolean chocaCon( ObjetoPantalla o2 ) {
		boolean choca = !(getX() > o2.getX()+o2.getAncho() ||
				getX() + getAncho() < o2.getX() ||
				getY() > o2.getY()+o2.getAlto() ||
				getY() + getAlto() < o2.getY());
		return choca;
	}
	
	

}
